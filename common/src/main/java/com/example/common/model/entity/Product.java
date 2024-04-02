package com.example.common.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @Column(name = "create_at")
    private Date createAt;

    public Long getId() {
            return id;
        }
    public void setId(Long id) {
            this.id = id;
        }
    public String getName() {
            return name;
        }

    public void setName(String name) {
            this.name = name;
        }

    public Double getPrice() {
            return price;
        }

    public void setPrice(Double price) {
            this.price = price;
        }
    public Date getCreateAt() {
            return createAt;
        }

    public void setCreateAt(Date createAt) {
            this.createAt = createAt;
        }

}
