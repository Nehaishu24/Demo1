package com.demo.ctl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.common.CommonCtl;
import com.demo.common.ORSResponse;
import com.demo.config.JwtTokenUtil;
import com.demo.dto.UserDTO;
import com.demo.form.LoginForm;
import com.demo.form.UserForm;
import com.demo.service.UserServiceInt;

@RestController
@RequestMapping("/auth")
public class LoginCtl extends CommonCtl {

	@Autowired
	private UserServiceInt userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtService;

	@PostMapping("/signup")
	public ORSResponse signup(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO newUser = new UserDTO();
		newUser.setUsername(form.getUsername());
		newUser.setEmail(form.getEmail());
		newUser.setPassword(form.getPassword());
		newUser.setRoleId(form.getRoleId());

		userService.add(newUser);
		res.addMessage("User registered successfully");
		return res;
	}

	@PostMapping("/signin")
	public ORSResponse signin(@RequestBody @Valid LoginForm form, BindingResult bindingResult, HttpSession session) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO searchDTO = new UserDTO();
		searchDTO.setEmail(form.getEmail());
		List<UserDTO> foundUsers = userService.search(searchDTO, 0, 1);
		UserDTO foundUser = foundUsers.get(0);
		
		
		if (foundUsers.isEmpty()) {
			res.setSuccess(false);
			res.addMessage("Invalid username or password");
			return res;
		}

		if (!form.getPassword().equals(foundUser.getPassword())) {
			res.setSuccess(false);
			res.addMessage("Invalid username or password");
			return res;
		}

		session.setAttribute("username", foundUser);

		final UserDetails userDetails = jwtService.loadUserByUsername(foundUser.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println("Token = " + token);

		res.addResult("token", token);
		res.addMessage("Login successful");
		return res;
	}
}
