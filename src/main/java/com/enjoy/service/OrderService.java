package com.enjoy.service;

import com.enjoy.annotation.NeedSetFieldValue;
import com.enjoy.dao.OrderDao;
import com.enjoy.model.Order;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@NeedSetFieldValue
	public Page<Order> pageQuery(String customerId, int pageNum, int pageSize) {
		Page<Order> page = PageHelper.startPage(pageNum, pageSize);
		this.orderDao.query(customerId);

		// 需要获得订单的客户姓名
		return page;
	}
}
