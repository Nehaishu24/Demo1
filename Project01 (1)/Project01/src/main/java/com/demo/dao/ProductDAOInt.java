package com.demo.dao;

import java.util.List;

import com.demo.dto.ProductDTO;

public interface ProductDAOInt {

    public long add(ProductDTO dto);
    
    public void update(ProductDTO dto);
    
    public void delete(ProductDTO dto);
    
    public ProductDTO findByPk(long pk);
    
    public List<ProductDTO> search(ProductDTO dto, int pageNo, int pageSize);
}
