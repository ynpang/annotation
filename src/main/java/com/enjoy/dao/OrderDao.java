package com.enjoy.dao;

import com.enjoy.model.Order;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@CacheNamespace
public interface OrderDao {

	List<Order> query(@Param("customerId") String customerId);
}
