package com.demo.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CommonCtl {

	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse(true);
		System.out.println("inside the validate method of baseCtl");
		if (bindingResult.hasErrors()) {
			System.out.println("inside the hasErors true");

			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();
			System.out.println("list" + list);

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
				System.out.println("Field :: " + e.getField() + "  Message :: " + e.getDefaultMessage());
			});
			res.addInputError(errors);

			System.out.println("errors" + errors);
			System.out.println("res in validate method inside{}" + res);

		}
		System.out.println("res in validate method" + res);

		return res;

	}

}
