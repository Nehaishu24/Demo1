package com.demo.dao;

import java.util.List;

import com.demo.dto.RoleDTO;

public interface RoleDAOInt {

    public long add(RoleDTO dto);
    
    public void update(RoleDTO dto);
    
    public void delete(RoleDTO dto);
    
    public RoleDTO findByPk(long pk);
    
    public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize);
}
