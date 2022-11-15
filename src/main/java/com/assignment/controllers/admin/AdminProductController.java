package com.assignment.controllers.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.entities.Category;
import com.assignment.entities.Product;
import com.assignment.repositories.AccountRepository;
import com.assignment.repositories.CategoryRepository;
import com.assignment.repositories.ProductRepository;
import com.assignment.services.ParamService;
import com.assignment.services.BgJobService;
import com.assignment.services.ExcelExportService;
import com.assignment.services.ExcelImportService;
import com.assignment.services.S3Service;
import com.assignment.services.SessionService;

@Controller
@RequestMapping("admin/product")
public class AdminProductController {

    @Autowired
    JobScheduler jobScheduler;
    
    @Autowired
    S3Service s3Service;
    
    @Autowired
    SessionService session;
    
    @Autowired
    ParamService paramService;
    
    @Autowired
    ExcelExportService pExport;
    
    @Autowired
    ExcelImportService pImport;
    
    @Autowired
    BgJobService bgJobService;
    
    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    AccountRepository accountRepo;

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        List<Category> list = categoryRepo.findAll();
        return list;
    }
    
    @RequestMapping("manager")
    public String getForm(Model model,
            @RequestParam("min") Optional<Double> min,
            @RequestParam("max") Optional<Double> max,
            @RequestParam("keywords") Optional<String> keywords,
            @RequestParam("status") Optional<Integer> status,
            @RequestParam("p") Optional<Integer> p) {

        Product product = new Product();
        model.addAttribute("product", product);

        if (p.orElse(0) < 0) {
            return "redirect:/admin/product/manager";
        }
        if (session.get("minPrice") == null)
            session.set("minPrice", Double.MIN_VALUE);
        if (session.get("maxPrice") == null)
            session.set("maxPrice", Double.MAX_VALUE);
        if (session.get("keywords") == null)
            session.set("keywords", "");
        if (session.get("status") == null)
            session.set("status", 0);

        double minPrice = min.orElse(Double.parseDouble(session.get("minPrice").toString()));
        double maxPrice = max.orElse(Double.parseDouble(session.get("maxPrice").toString()));
        String kwords = keywords.orElse(session.get("keywords"));
        int stt = status.orElse(session.get("status"));

        session.set("minPrice", minPrice);
        session.set("maxPrice", maxPrice);
        session.set("keywords", kwords);
        session.set("status", stt);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = productRepo.findByPriceAnsKeywordsAndStatus("%" + kwords + "%", stt, minPrice, maxPrice,
                pageable);
        productRepo.saveAllAndFlush(page.getContent());
        model.addAttribute("page", page);
        if (p.orElse(0) > (page.getTotalPages() - 1)) {
            p = Optional.of(page.getTotalPages() - 1);
            return "redirect:/admin/product/manager?p=" + p.get();
        }

        return "admin/products-manager";
    }

    @RequestMapping("create")
    public String create(Model model,
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            @RequestParam(name = "file", required = false) MultipartFile multipartFile,
            @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = productRepo.findAll(pageable);
        model.addAttribute("page", page);

        boolean validate = true;

        if (productRepo.existsByUsername(product.getName().trim()) != 0) {
            model.addAttribute("nameErrorMessage", "Tên sản phẩm đã được sử dụng");
            validate = false;
        }

        if (!result.hasErrors() && validate == true) {
            if (!multipartFile.getOriginalFilename().equals("")) {
                product.setImage(s3Service.saveFile(multipartFile));
            }
            product.setId(0);
            product.setCreatedDate(new Date());
            product.setCreatedUserId(accountRepo.findByAccId(1));
            product.setLastModifiedDate(new Date());
            product.setLastModifiedUserId(accountRepo.findByAccId(1));
            productRepo.save(product);
            return "redirect:/admin/product/manager";
        }

        return "admin/products-manager";
    }

    @RequestMapping("/update")
    public String update(Model model,
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            @RequestParam(name = "file", required = false) MultipartFile multipartFile,
            @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = productRepo.findAll(pageable);
        model.addAttribute("page", page);
        boolean validate = true;
        for (Product prod : productRepo.findAll()) {
            if (prod.getName().trim().equals(product.getName().trim())
                    && prod.getId() != product.getId()) {
                model.addAttribute("nameErrorMessage", "Tên sản phẩm đã tồn tại");
                validate = false;
            }
        }
        if (!result.hasErrors() && validate == true) {
            Product pro = productRepo.findByProId(product.getId());
            if (!multipartFile.getOriginalFilename().equals("")) {
                product.setImage(s3Service.saveFile(multipartFile));
            }else {
                product.setImage(pro.getImage());
            }
            product.setCreatedDate(pro.getCreatedDate());
            product.setCreatedUserId(pro.getCreatedUserId());
            product.setLastModifiedDate(new Date());
            product.setLastModifiedUserId(accountRepo.findByAccId(1));
            productRepo.save(product);
            return "redirect:/admin/product/reset";
        }
        return "admin/products-manager";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") int id, 
            @ModelAttribute("product") Product product,
            @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = productRepo.findAll(pageable);
        model.addAttribute("page", page);
        if (!productRepo.existsById(id)) {
            model.addAttribute("idErrorMessage", "Id không tồn tại");
            return "admin/product-manager";
        }
        productRepo.deleteById(id);
        return "redirect:/admin/product/reset";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model,
            @PathVariable("id") int id,
            @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = productRepo.findAll(pageable);
        model.addAttribute("page", page);

        Product product = productRepo.findByProId(id);
        product.setLinkImage(s3Service.getPresignedURL(product.getImage()));
        model.addAttribute("product", product);

        return "admin/products-manager";
    }

    @RequestMapping("/reset")
    public String reset(Model model,
            @RequestParam("p") Optional<Integer> p) {
        session.remove("minPrice");
        session.remove("maxPrice");
        session.remove("keywords");
        session.remove("status");
        return "redirect:/admin/product/manager";
    }
    
    @RequestMapping("/export-file-excel")
    public void exportExcel(Model model) {
        try {
            pExport.export();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private File converMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    
    @RequestMapping("/import-from-excel")
    public String importExcel(Model model,
            @RequestParam("fileExcel") MultipartFile multiFile,
            @RequestParam("p") Optional<Integer> p) {

        Product product = new Product();
        model.addAttribute("product", product);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = productRepo.findAll(pageable);
        model.addAttribute("page", page);
        try {
            File file = converMultiPartToFile(multiFile);
            jobScheduler.enqueue(() -> bgJobService.saveFromExcel(file));
            System.out.println(file.getAbsolutePath());
            return "redirect:/admin/product/manager";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/product/products-manager";
    }

}
