package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	//관리자 페이지 이동 테스트용
	@RequestMapping("admin")
	public String admin() {
		
		return "admin";
	}
}
