package com.zegobird.oauth2center.service;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:46
 */
public interface TbRolePermissionService {

    Boolean saveRolePermission(Long roleId,String permissionIds);

    int insertPermissionRole(Long permissionId, Long roleId);

}

