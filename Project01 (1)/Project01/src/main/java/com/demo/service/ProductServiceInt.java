package com.demo.service;

import java.util.List;

import com.demo.dto.ProductDTO;

public interface ProductServiceInt {

    public long add(ProductDTO dto);
    
    public void update(ProductDTO dto);
    
    public void delete(long pk);
    
    public ProductDTO findById(long pk);
    
    public List<ProductDTO> search(ProductDTO dto, int pageNo, int pageSize);

    
   }
