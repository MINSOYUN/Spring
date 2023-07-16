package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.mapper.HotelMapper;
import com.momo.vo.HotelVO;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	HotelMapper mapper;
	@Override
	public List<HotelVO> getList(Model model) {
		List<HotelVO> list = mapper.getList();
		model.addAttribute("list", list);
		return null;
	}
}
