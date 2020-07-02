package com.cmdc.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : wuwensheng
 * @date : 23:33 2020/7/1
 */
@Mapper
public interface UserRoleMapper {
    void insert(@Param("userId") String userId, @Param("roleId") Integer roleId);
}
