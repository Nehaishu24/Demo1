package com.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ST_PRODUCT")
public class ProductDTO {

    @Id
    @GeneratedValue(generator = "demo")
    @GenericGenerator(name = "demo", strategy = "native")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 100)
    private String productName;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "IS_VISIBLE")
    private Boolean isVisible ;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
}
