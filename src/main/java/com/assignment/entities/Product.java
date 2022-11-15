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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @Size(min = 4, message = "{Size.product.name}")
    @NotEmpty(message = "{NotEmpty.product.name}")
    @Column(nullable=false, length=255)
    private String name;
    
    @Column(length=255)
    private String image;
    
    @Column(length=500)
    private String linkImage;
    
    @NotNull(message = "{NotNull.product.price}")
    @Min(value = 0, message = "{NotBlank.product.price}")
    @Column(nullable=false, precision=12)
    private double price;
    
    @NotNull(message = "{NotNull.product.quantity}")
    @Min(value = 0, message = "{NotBlank.product.quantity}")
    @Column(nullable=false, precision=10)
    private int quantity;
    
    @Column(name="created_date", nullable=false)
    private Date createdDate;
    
    @Column(name="last_modified_date", nullable=false)
    private Date lastModifiedDate;
    
    @NotNull(message = "{NotNull.product.available}")
    @Min(value = 0, message = "{NotBlank.product.available}")
    @Max(value = 2, message = "{NotBlank.product.available}")
    @Column(nullable=false, precision=10)
    private int available;
    
    @NotNull(message = "{NotNull.product.categoryId}")
    @ManyToOne(optional=false)
    @JoinColumn(name="category_id", nullable=false)
    private Category categoryId;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="created_user_id", nullable=false)
    private Account createdUserId;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="last_modified_user_id", nullable=false)
    private Account lastModifiedUserId;

}
