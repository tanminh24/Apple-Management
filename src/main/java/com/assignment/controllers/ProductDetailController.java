package com.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.entities.Product;
import com.assignment.repositories.ProductRepository;
import com.assignment.services.S3Service;

@Controller
@RequestMapping("/product")
public class ProductDetailController {

    @Autowired
    ProductRepository productRepo;
    
    @Autowired
    S3Service s3Service;
    
    @RequestMapping("/detail/{id}")
    public String productDetail(Model model,@PathVariable("id") int id) {
        Product p = productRepo.findByProId(id);
        p.setLinkImage(s3Service.getPresignedURL(p.getImage()));
        model.addAttribute("product", p);
        return "/product/product-detail";
    }
    
}
