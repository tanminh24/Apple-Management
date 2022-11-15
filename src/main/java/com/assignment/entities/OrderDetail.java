package com.assignment.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_details")
public class OrderDetail implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @Column(nullable=false, precision=12)
    private double price;
    
    @Column(nullable=false, precision=10)
    private int quantity;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="order_id", nullable=false)
    private Order orderId;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="product_id", nullable=false)
    private Product productId;

}
