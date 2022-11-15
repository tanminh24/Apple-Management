package com.assignment.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.entities.Account;
import com.assignment.entities.Order;
import com.assignment.entities.OrderDetail;
import com.assignment.entities.Product;
import com.assignment.repositories.AccountRepository;
import com.assignment.repositories.OrderDetailRepository;
import com.assignment.repositories.OrderRepository;
import com.assignment.repositories.ProductRepository;
import com.assignment.services.CartService;
import com.assignment.services.SessionService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    SessionService session;
   
    @Autowired
    CartService cart;
    
    @Autowired
    AccountRepository accountRepo;
    
    @Autowired
    ProductRepository productRepo;
    
    @Autowired
    OrderRepository orderRepo;
    
    @Autowired
    OrderDetailRepository odRepo;
    
    @GetMapping("/view")
    public String index(Model model) {
        model.addAttribute("cart", cart);
        session.set("cartSize",cart.getCount());
        return "cart/cart";
    }
    
    @RequestMapping("/add/{id}")
    public String add(Model model,
            @PathVariable("id") int id) {
        cart.add(id);
        return "redirect:/cart/view";
    }
    
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, @RequestParam("quantity") Integer quantity) {
        cart.update(id, quantity);
        return "redirect:/cart/view";
    }
    
    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        cart.remove(id);
        return "redirect:/cart/view";
    }
    
    @RequestMapping("/pay/{username}")
    public String pay(@PathVariable("username") String username) {
        if(accountRepo.existsByUsername(username)==0) {
            return "redirect:/cart/view";
        }
        if(orderRepo.existsByUsernameAndStatus(username, 0)==0) {
            Account acc = accountRepo.findByUsername(username);
            Order o = new Order();
            o.setUserId(acc);
            o.setAmount(cart.getAmount());
            o.setCreatedDate(new Date());
            o.setPhoneNumber("0123456789");
            o.setAddress("Ha Noi");
            o.setStatus(1);
            orderRepo.save(o);
            
            for (Product p : cart.getItems()) {
                OrderDetail od = new OrderDetail();
                od.setProductId(p);
                od.setOrderId(o);
                od.setPrice(p.getPrice());
                od.setQuantity(p.getQuantity());
                odRepo.save(od);
            }
            
        }
        return "";
    }

    
}
