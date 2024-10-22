package com.demo.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.CommonCtl;
import com.demo.common.ORSResponse;
import com.demo.dto.UserDTO;
import com.demo.form.UserForm;
import com.demo.service.UserServiceInt;

@RestController
@RequestMapping(value = "user")
public class UserCtl extends CommonCtl {

	@Autowired
	UserServiceInt service;

	@PostMapping("save")
	public ORSResponse add(@RequestBody @Valid UserForm form, BindingResult bindingResult) {
		
		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}
		
		UserDTO dto = new UserDTO();
		dto.setId(form.getId());
		dto.setUsername(form.getUsername());
		dto.setEmail(form.getEmail());
		dto.setPassword(form.getPassword());
		dto.setRoleId(form.getRoleId());

		if (dto.getId() != null && dto.getId() > 0) {
			service.update(dto);
			res.addMessage("User updated successfully");
		} else {
			service.add(dto);
			res.addMessage("User added successfully");
		}
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		
		ORSResponse res = new ORSResponse(true);
		service.delete(id);
		res.addMessage("User deleted successfully");
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse res = new ORSResponse(true);
		UserDTO dto = service.findById(id);
		if (dto != null) {
			res.addData(dto);
		}
		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {
		ORSResponse res = new ORSResponse(true);
		UserDTO dto = new UserDTO();
		dto.setUsername(form.getUsername());
		dto.setEmail(form.getEmail());
		List<UserDTO> list = service.search(dto, pageNo, 5);

		if (list.isEmpty()) {
			res.addMessage("No records found");
		} else {
			res.addData(list);
		}
		return res;
	}
}
