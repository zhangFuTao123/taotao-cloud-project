// package com.taotao.cloud.sys.biz.service;
//
// import com.taotao.cloud.sys.api.dto.DictDTO;
// import com.taotao.cloud.sys.biz.entity.SysDict;
//
// import java.io.Serializable;
//
// /**
//  * 字典表 服务类
//  *
//  * @author shuigedeng
//  * @since 2020/4/30 11:18
//  */
// public interface ISysDictService {
//
//     /**
//      * 添加字典
//      *
//      * @param entity
//      * @return boolean
//      * @author shuigedeng
//      * @since 2020/4/30 11:19
//      */
//     boolean save(SysDict entity);
//
//     /**
//      * 修改字典
//      *
//      * @param dictDto
//      * @return boolean
//      * @author shuigedeng
//      * @since 2020/4/30 11:19
//      */
//     boolean updateDict(DictDTO dictDto);
//
//     /**
//      * 根据主键Id删除字典
//      *
//      * @param id
//      * @return boolean
//      * @author shuigedeng
//      * @since 2020/4/30 11:19
//      */
//     boolean removeById(Serializable id);
// }