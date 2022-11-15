package com.assignment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.entities.Category;
import com.assignment.entities.Product;
import com.assignment.repositories.CategoryRepository;
import com.assignment.repositories.ProductRepository;
import com.assignment.services.S3Service;

@Controller
public class IndexController {

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProductRepository productRepo;
    
    @Autowired
    S3Service s3Service;

    @RequestMapping("/home")
    public String index(Model model) {

        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("cates", listCategories);

        Page<Product> pageProds = productRepo.findTop5ProductsByDate(PageRequest.of(0, 5));
        List<Product> listProds = pageProds.getContent();
        for (Product product : listProds) {
            product.setLinkImage(s3Service.getPresignedURL(product.getImage()));
        }
        model.addAttribute("prods", listProds);
        
        return "index";
    }

}
