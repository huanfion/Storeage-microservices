package com.zegobird.oauth2center.mapper;

import com.zegobird.oauth2center.domain.TbRolePermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TbRolePermissionMapper extends MyMapper<TbRolePermission> {
    int insert(TbRolePermission rolePermission);

    /**
     * 批量插入
     * @param rolePermissionList
     * @return
     */
    int insertForeach( List<TbRolePermission> rolePermissionList);

    int insertPermissionRole(@Param("permissionId") Long permissionId, @Param("roleId") Long roleId);

    int deletePermissionRoleByPermissionId(@Param("permissionId") Long permissionId);

    public List<Map> queryPermissionRole(@Param("permissionId") Long permissionId);

    int deleteFunctionsByRole(@Param("roleId") Long roleId);
}