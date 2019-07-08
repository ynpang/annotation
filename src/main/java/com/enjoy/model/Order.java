package com.enjoy.model;

import com.enjoy.annotation.SetValue;
import com.enjoy.dao.UserDao;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Order implements Serializable {

	private static final long serialVersionUID = -8821751371277937944L;

	private String id;

	private String customerId;

	@SetValue(beanClass = UserDao.class, method = "find", paramField = "customerId", targetField = "userName")
	private String customerName;

}
