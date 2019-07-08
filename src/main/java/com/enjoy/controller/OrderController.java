package com.enjoy.controller;

import com.enjoy.annotation.NeedSetFieldValue;
import com.enjoy.service.OrderService;
import com.enjoy.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private BeanUtil beanUtil;

	@RequestMapping("query")
	@NeedSetFieldValue
	public Object query(String customerId, int pageNum, int pageSize) {
		return this.orderService.pageQuery(customerId, pageNum, pageSize);

	}
}
