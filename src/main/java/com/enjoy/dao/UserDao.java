package com.enjoy.dao;

import com.enjoy.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@CacheNamespace
public interface UserDao {

	@Insert("insert t_user(id,user_name) values(#{id},#{userName})")
	void insert(User u);

	@Update("update t_user set user_name = #{userName} where id=#{id} ")
	void update(User u);

	@Delete("delete from t_user where id=#{id} ")
	void delete(@Param("id") String id);

	@Select("select id,user_name from t_user where id=#{id} ")
	User find(@Param("id") String id);

	List<User> query(@Param("userName") String userName);

}
