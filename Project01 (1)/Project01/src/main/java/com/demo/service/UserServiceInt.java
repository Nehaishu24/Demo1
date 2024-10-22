package com.demo.service;

import java.util.List;

import com.demo.dto.UserDTO;

public interface UserServiceInt {

    public long add(UserDTO dto);
    
    public void update(UserDTO dto);
    
    public void delete(long pk);
    
    public UserDTO findById(long pk);
    
    public UserDTO findByEmail(String useranme);
    
    public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize);
}
