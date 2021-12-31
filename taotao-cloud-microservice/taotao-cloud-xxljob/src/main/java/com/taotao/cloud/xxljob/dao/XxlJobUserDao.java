package com.taotao.cloud.xxljob.dao;

import com.taotao.cloud.xxljob.core.model.XxlJobUser;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 */
@Mapper
public interface XxlJobUserDao {

	public List<XxlJobUser> pageList(@Param("offset") int offset,
                                     @Param("pagesize") int pagesize,
                                     @Param("username") String username,
									 @Param("role") int role);
	public int pageListCount(@Param("offset") int offset,
							 @Param("pagesize") int pagesize,
							 @Param("username") String username,
							 @Param("role") int role);

	public XxlJobUser loadByUserName(@Param("username") String username);

	public int save(XxlJobUser xxlJobUser);

	public int update(XxlJobUser xxlJobUser);
	
	public int delete(@Param("id") int id);

}