package com.zegobird.oauth2center.service;

import com.zegobird.oauth2center.domain.TbPermission;
import com.zegobird.oauth2center.dto.FunctionPermission;
import com.zegobird.oauth2center.dto.PermissionTree;
import com.zegobird.oauth2center.dto.query.PermissionQuery;

import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:47
 */
public interface TbPermissionService {
    List<TbPermission> selectByUserId(Long userId);

    List<TbPermission> getPermissionList(PermissionQuery query);

    int update(TbPermission permission);

    int delete(Long id);

    int add(TbPermission permission);

    List<PermissionTree> getPermissionTree();

    List<PermissionTree> getFunctionTree();

    List<PermissionTree> getAllRoleFunctionTree(Long roleId);

    List<FunctionPermission> getRoleFunctions(Long roleId);
}
