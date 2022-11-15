package com.assignment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT p FROM Product p where p.id = :id")
    public Product findByProId(@Param("id") int id);

    @Query(value = "SELECT p FROM Product p WHERE p.name LIKE :name AND p.available = :status AND p.price BETWEEN :min AND :max")
    public Page<Product> findByPriceAnsKeywordsAndStatus(@Param("name") String keywords, @Param("status") int status, 
            @Param("min") double minPrice, @Param("max") double maxPrice, Pageable pageable);

    @Query(value="SELECT count(p) FROM Product p where p.name = :name")
    public int existsByUsername(@Param("name") String name);
    
    @Query(value="SELECT p FROM Product p ORDER BY p.createdDate DESC")
    public Page<Product> findTop5ProductsByDate(Pageable pageable);
    
}
