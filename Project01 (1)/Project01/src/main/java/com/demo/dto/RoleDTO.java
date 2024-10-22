package com.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ST_ROLE")
public class RoleDTO {

    @Id
    @GeneratedValue(generator = "demo")
    @GenericGenerator(name = "demo", strategy = "native")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name; 

    
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
}
