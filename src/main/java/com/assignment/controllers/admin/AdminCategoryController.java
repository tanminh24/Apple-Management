package com.assignment.controllers.admin;

import java.util.Optional;

import javax.validation.Valid;

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

import com.assignment.entities.Category;
import com.assignment.repositories.CategoryRepository;
import com.assignment.services.ParamService;
import com.assignment.services.SessionService;

@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    SessionService session;
    
    @Autowired
    ParamService paramService;

    @RequestMapping("manager")
    public String getForm(Model model,
            @RequestParam("keywords") Optional<String> keywords,
            @RequestParam("status") Optional<Integer> status,
            @RequestParam("p") Optional<Integer> p) {

        Category category = new Category();
        model.addAttribute("category", category);

        if (p.orElse(0) < 0) {
            return "redirect:/admin/category/manager";
        }
        if (session.get("keywords") == null) session.set("keywords", "");
        if (session.get("status") == null) session.set("status", 0);
        
        String kwords = keywords.orElse(session.get("keywords"));
        int stt = status.orElse(session.get("status"));

        session.set("keywords", kwords);
        session.set("status", stt);
        
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = categoryRepo.findByKeywordsAndStatus("%" + kwords + "%", stt , pageable);
        for (Category item : page.getContent()) {
            item.setQuantityProduct(categoryRepo.countProductByCategory(item.getId()));
        }
        categoryRepo.saveAllAndFlush(page.getContent());
        model.addAttribute("page", page);
        if (p.orElse(0) > (page.getTotalPages() - 1)) {
            p = Optional.of(page.getTotalPages() - 1);
            return "redirect:/admin/category/manager?p=" + p.get();
        }

        return "admin/categories-manager";
    }

    @RequestMapping("create")
    public String create(Model model,
            @Valid @ModelAttribute("category") Category category,
            BindingResult result,
            @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = categoryRepo.findAll(pageable);
        for (Category item : page.getContent()) {
            item.setQuantityProduct(categoryRepo.countProductByCategory(item.getId()));
        }
        model.addAttribute("page", page);

        boolean validate = true;
        if (categoryRepo.existsByName(category.getName().trim()) != 0) {
            model.addAttribute("nameErrorMessage", "Tên danh mục đã được sử dụng");
            validate = false;
        }
        if (!result.hasErrors() && validate == true) {
            categoryRepo.save(category);
            return "redirect:/admin/category/manager";
        }
        return "admin/categories-manager";
    }

    @RequestMapping("/update")
    public String update(Model model,
            @Valid @ModelAttribute("category") Category category,
            BindingResult result,
            @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = categoryRepo.findAll(pageable);
        for (Category item : page.getContent()) {
            item.setQuantityProduct(categoryRepo.countProductByCategory(item.getId()));
        }
        model.addAttribute("page", page);

        boolean validate = true;
        for (Category cate : categoryRepo.findAll()) {
            if (cate.getName().trim().equals(category.getName().trim())
                    && cate.getId() != category.getId()) {
                model.addAttribute("nameErrorMessage", "Tên danh mục đã tồn tại");
                validate = false;
                return "category/categories-manager";
            }
        }
        if (!result.hasErrors() && validate == true) {
            categoryRepo.save(category);
            return "redirect:/admin/category/reset";
        }
        return "admin/categories-manager";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model,
            @PathVariable("id") int id,
            @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = categoryRepo.findAll(pageable);
        for (Category item : page.getContent()) {
            item.setQuantityProduct(categoryRepo.countProductByCategory(item.getId()));
        }
        model.addAttribute("page", page);

        Category category = categoryRepo.findByCateId(id);
        model.addAttribute("category", category);
        return "admin/categories-manager";
    }

    @RequestMapping("/reset")
    public String reset(Model model,
            @RequestParam("p") Optional<Integer> p) {
        session.remove("keywords");
        session.remove("status");
        return "redirect:/admin/category/manager";
    }

}
