package com.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ProductDAOInt;
import com.demo.dao.RoleDAOInt;
import com.demo.dao.UserDAOInt;
import com.demo.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductServiceInt {

    @Autowired
    private ProductDAOInt dao;
    
    
    @Transactional(propagation = Propagation.REQUIRED)
    public long add(ProductDTO dto) {
        return dao.add(dto);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(ProductDTO dto) {
        dao.update(dto);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(long pk) {
        ProductDTO dto = findById(pk);
        dao.delete(dto);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(long pk) {
        return dao.findByPk(pk);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> search(ProductDTO dto, int pageNo, int pageSize) {
        return dao.search(dto, pageNo, pageSize);
    }
    
    
    
}
