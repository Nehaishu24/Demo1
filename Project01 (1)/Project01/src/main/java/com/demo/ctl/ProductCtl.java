package com.demo.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.demo.common.CommonCtl;
import com.demo.common.ORSResponse;
import com.demo.dto.ProductDTO;
import com.demo.dto.RoleDTO;
import com.demo.form.ProductForm;
import com.demo.service.ProductServiceInt;

@RestController
@RequestMapping(value = "product")
public class ProductCtl extends CommonCtl{

    @Autowired
    ProductServiceInt service;

    @PostMapping("save")
    public ORSResponse add(@RequestBody @Valid ProductForm form, BindingResult bindingResult) {
    	
    	ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}
        ProductDTO dto = new ProductDTO();
        dto.setId(form.getId());
        dto.setProductName(form.getProductName());
        dto.setDescription(form.getDescription());
        dto.setPrice(form.getPrice());
        dto.setIsVisible(form.getIsVisible());

        if (dto.getId() != null && dto.getId() > 0) {
            service.update(dto);
            res.addMessage("Product updated successfully");
        } else {
            service.add(dto);
            res.addMessage("Product added successfully");
        }
        return res;
    }

    @GetMapping("delete/{id}")
    public ORSResponse delete(@PathVariable long id) {
        ORSResponse res = new ORSResponse(true);
        service.delete(id);
        res.addMessage("Product deleted successfully");
        return res;
    }

    @GetMapping("get/{id}")
    public ORSResponse get(@PathVariable long id) {
        ORSResponse res = new ORSResponse(true);
        ProductDTO dto = service.findById(id);
        if (dto != null) {
            res.addData(dto);
        }
        return res;
    }

    @PostMapping("search/{pageNo}")
    public ORSResponse search(@RequestBody ProductForm form, @PathVariable int pageNo) {
        ORSResponse res = new ORSResponse(true);
        ProductDTO dto = new ProductDTO();
        dto.setProductName(form.getProductName());
        dto.setDescription(form.getDescription());
        
        List<ProductDTO> list = service.search(dto, pageNo, 5);
          
        if (list.isEmpty()) {
            res.addMessage("No records found");
        } else {
            res.addData(list);
        }
        return res;
    }
   }
    
    
    
