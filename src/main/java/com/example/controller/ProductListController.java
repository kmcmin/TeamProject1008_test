package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.ProductService;

@Controller
public class ProductListController {

	@Autowired
	ProductService service;

	@RequestMapping("/productList")
	public String productList() {
		return "productList";
	}
	
//	@RequestMapping("/productDetail")
//	public String productDetail() {
//		return "goods/productDetail";
//	}

	@RequestMapping("/getProductList") 
	public @ResponseBody List<Object> getProductList(@RequestParam HashMap<Object, Object> filter){
		// 필터값 서비스로 넘겨줌.
		//System.out.println("여기다"+filter);
		List<Object> productList = new ArrayList<Object>();
		productList = service.getProductList(filter);
		return productList;
	}
	
}
