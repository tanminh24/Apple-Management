package com.assignment.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @Column(nullable=false, precision=12)
    private double amount;
    
    @Column(name="created_date", nullable=false)
    private Date createdDate;
    
    @Column(name="phone_number", length=255)
    private String phoneNumber;
    
    @Column(nullable=false, length=255)
    private String address;
    
    @Column(nullable=false, precision=10)
    private int status;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="user_id", nullable=false)
    private Account userId;

}
