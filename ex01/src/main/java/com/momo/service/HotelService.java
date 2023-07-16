package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.vo.HotelVO;

@Service
public interface HotelService {
	public List<HotelVO> getList(Model model);
}
