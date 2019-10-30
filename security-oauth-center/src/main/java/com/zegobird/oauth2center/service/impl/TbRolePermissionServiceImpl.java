package com.zegobird.oauth2center.service.impl;

import com.zegobird.oauth2center.domain.TbRolePermission;
import com.zegobird.oauth2center.mapper.TbRolePermissionMapper;
import com.zegobird.oauth2center.service.TbRolePermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:46
 */
@Service
public class TbRolePermissionServiceImpl implements TbRolePermissionService {

    @Resource
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Transactional
    @Override
    public Boolean saveRolePermission(Long roleId, String permissionIds) {
        if (StringUtil.isEmpty(permissionIds)) {
            tbRolePermissionMapper.deleteFunctionsByRole(roleId);
            return true;
        }
        List<TbRolePermission> rolePermissionList = Arrays
                .stream(permissionIds.split(","))
                .map(permissionId -> {
                    TbRolePermission rolePermission = new TbRolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(Long.valueOf(permissionId));
                    return rolePermission;
                }).collect(Collectors.toList());
        tbRolePermissionMapper.deleteFunctionsByRole(roleId);
        return tbRolePermissionMapper.insertForeach(rolePermissionList) > 0;
    }

    @Override
    public int insertPermissionRole(Long permissionId,Long roleId){
        return tbRolePermissionMapper.insertPermissionRole(permissionId,roleId);
    }
}

