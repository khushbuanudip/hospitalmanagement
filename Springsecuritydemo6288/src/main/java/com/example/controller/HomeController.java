package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/students/studentProfile")
	// @PreAuthorize("hasAuthority('ROLE_USER')")
	public String getWelcome() {
		return "Hello students";
	}

	@GetMapping("/teachers/teacherProfile")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String getGoodby() {
		return "Hello teachers";
	}

}
