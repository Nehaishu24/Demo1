package com.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.RoleDAOInt;
import com.demo.dto.RoleDTO;

@Service
public class RoleServiceImpl implements RoleServiceInt {

    @Autowired
    private RoleDAOInt dao;
    
    @Transactional(propagation = Propagation.REQUIRED)
    public long add(RoleDTO dto) {
        return dao.add(dto);
    }

	
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(RoleDTO dto) {
        dao.update(dto);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(long pk) {
        RoleDTO dto = findById(pk);
        dao.delete(dto);
    }
  
    
    @Transactional(readOnly = true)
    public RoleDTO findById(long pk) {
        return dao.findByPk(pk);
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) {
        return dao.search(dto, pageNo, pageSize);
    }
}
