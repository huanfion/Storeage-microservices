package com.zegobird.oauth2center.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zegobird.oauth2center.mapper.TbUserRoleMapper;
import com.zegobird.oauth2center.service.TbUserRoleService;
/**
 * @version 1.0
 * @author huanfion
 * @date 2019/8/8 19:46
 */
@Service
public class TbUserRoleServiceImpl implements TbUserRoleService{

    @Resource
    private TbUserRoleMapper tbUserRoleMapper;

}
