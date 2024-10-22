package com.demo.service;

import java.util.List;

import com.demo.dto.RoleDTO;

public interface RoleServiceInt {

    public long add(RoleDTO dto);
    
    public void update(RoleDTO dto);
    
    public void delete(long pk);
    
    public RoleDTO findById(long pk);
    
    public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize);
}
