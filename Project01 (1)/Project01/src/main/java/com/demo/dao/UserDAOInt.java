package com.demo.dao;

import java.util.List;

import com.demo.dto.UserDTO;

public interface UserDAOInt {

    public long add(UserDTO dto);
    
    public void update(UserDTO dto);
    
    public void delete(UserDTO dto);
    
    public UserDTO findByPk(long pk);
    
    public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize);

    public UserDTO findUserByEmail(String email);
}
