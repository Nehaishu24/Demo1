package com.demo.form;

import javax.validation.constraints.NotEmpty;

public class RoleForm {

    private Long id;

    @NotEmpty(message = "Role name is required")
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
