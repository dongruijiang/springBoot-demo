package com.sb.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.domain.result;

@RestController
public class HelloController {
	
	@GetMapping(value = "/test" , produces = MediaType.APPLICATION_JSON_VALUE)
	public result hello() {
		result r = new result("200", "成功!");
		return r;
	}
}
