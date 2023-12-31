package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CartDAO;
import com.example.dto.CartProductDTO;

@Service("cartService")
public class CartService {
	@Autowired
	CartDAO dao;
	
	public List<CartProductDTO> cartList(int user_no) {
		List<CartProductDTO> list=dao.cartList(user_no);
		return list;
	}
	
	public int cartAdd(CartProductDTO dto) {
		int n=dao.cartAdd(dto);
		return n;
	}

	public int amountUpdate(HashMap<String, Object> map) {
		int n =dao.amountUpdate(map);
		return n;
	}

	public int cartUpdate(HashMap<String, Object> map) {
		int n =dao.cartUpdate(map);
		return n;
	}

	public int delete(String delnum) {
		int n=dao.delete(delnum);
		return n;
	}

	public int allDelete(ArrayList<String> list) {
		int n=dao.allDelete(list);
		return n;
	}

	public int cartCheckDel(ArrayList<Integer> list) {
		int n=dao.cartCheckDel(list);
		return n;
	}


	
}
