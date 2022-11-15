package com.assignment.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT c FROM Category c where c.id = :id")
    public Category findByCateId(@Param("id") int id);

    @Query(value = "SELECT c FROM Category c where c.name = :name")
    public Category findByName(@Param("name") String name);

    @Query(value = "SELECT count(p) FROM Product p where p.categoryId.id = :categoryId")
    public int countProductByCategory(@Param("categoryId") int categoryId);

    @Query(value = "SELECT count(c) FROM Category c where c.name = :name")
    public int existsByName(@Param("name") String name);

    @Query(value = "SELECT c FROM Category c WHERE c.name LIKE :name AND c.available = :status")
    public Page<Category> findByKeywordsAndStatus(@Param("name") String keywords, @Param("status") int status,
            Pageable pageable);

}
