package com.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    @Query(value="SELECT count(o) FROM Order o where o.userId.username = :username AND o.status = :status")
    public int existsByUsernameAndStatus(@Param("username") String username, @Param("status") int status);
    
}
