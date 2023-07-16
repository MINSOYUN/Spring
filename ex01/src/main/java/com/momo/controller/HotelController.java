package com.momo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.momo.service.HotelService;

import lombok.extern.log4j.Log4j;

@Log4j   
@Controller  
@RequestMapping("/hotel/*")
public class HotelController {
	
	@Autowired
	HotelService service;
	
	@GetMapping("list")
	public void getList(Model model) {
		service.getList(model);
	}
}
