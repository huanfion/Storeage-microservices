package com.zegobird.oauth2center.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zegobird.oauth2center.mapper.TbRolePermissionMapper;
import com.zegobird.oauth2center.service.TbRolePermissionService;
/**
 * @version 1.0
 * @author huanfion
 * @date 2019/8/8 19:46
 */
@Service
public class TbRolePermissionServiceImpl implements TbRolePermissionService{

    @Resource
    private TbRolePermissionMapper tbRolePermissionMapper;

}
