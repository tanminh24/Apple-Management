package com.assignment.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entities.Product;
import com.assignment.repositories.OrderDetailRepository;
import com.assignment.repositories.ProductRepository;

@Service
public class CartService {

    @Autowired
    ProductRepository productRepo;

    Map<Integer, Product> map = new HashMap<>();

    public Product add(Integer id) {
        Product p = map.get(id);
        if (p == null) {
            p = productRepo.findByProId(id);
            p.setQuantity(1);
            map.put(id, p);
        } else {
            p.setQuantity(p.getQuantity() + 1);
        }
        return p;
    }

    public void remove(Integer id) {
        map.remove(id);
    }

    public Product update(Integer id, int qty) {
        Product p = map.get(id);
        if (p != null) {
            p.setQuantity(qty);
        }
        if (p.getPrice() <= 0) {
            remove(id);
        }
        return p;
    }

    public void clear() {
        map.clear();
    }

    public Collection<Product> getItems() {
        return map.values();
    }

    public int getCount() {
        return map.size();
    }

    public double getAmount() {
        double amount = 0;
        for (Product p : map.values()) {
            amount += p.getQuantity() * p.getPrice();
        }
        return amount;
    }
}
