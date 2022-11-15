package com.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.OrderDetail;
import com.assignment.entities.Product;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{

}
