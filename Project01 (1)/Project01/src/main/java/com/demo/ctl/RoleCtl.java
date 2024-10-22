package com.demo.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.demo.common.CommonCtl;
import com.demo.common.ORSResponse;
import com.demo.dto.RoleDTO;
import com.demo.form.RoleForm;
import com.demo.service.RoleServiceInt;

@RestController
@RequestMapping(value = "role")
public class RoleCtl extends CommonCtl {

	@Autowired
	RoleServiceInt service;

	@PostMapping("save")
	public ORSResponse add(@RequestBody @Valid RoleForm form, BindingResult bindingResult) {
		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}
		RoleDTO dto = new RoleDTO();
		dto.setId(form.getId());
		dto.setName(form.getName());

		if (dto.getId() != null && dto.getId() > 0) {
			service.update(dto);
			res.addMessage("Role updated successfully");
		} else {
			service.add(dto);
			res.addMessage("Role added successfully");
		}
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		ORSResponse res = new ORSResponse(true);
		service.delete(id);
		res.addMessage("Role deleted successfully");
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse res = new ORSResponse(true);
		RoleDTO dto = service.findById(id);
		if (dto != null) {
			res.addData(dto);
		}
		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody RoleForm form, @PathVariable int pageNo) {
		ORSResponse res = new ORSResponse(true);
		RoleDTO dto = new RoleDTO();
		dto.setName(form.getName());
		List<RoleDTO> list = service.search(dto, pageNo, 5);

		if (list.isEmpty()) {
			res.addMessage("No records found");
		} else {
			res.addData(list);
		}
		return res;
	}
}
