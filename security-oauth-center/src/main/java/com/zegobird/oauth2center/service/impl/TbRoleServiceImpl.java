package com.zegobird.oauth2center.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zegobird.oauth2center.mapper.TbRoleMapper;
import com.zegobird.oauth2center.service.TbRoleService;
/**
 * @version 1.0
 * @author huanfion
 * @date 2019/8/8 19:46
 */
@Service
public class TbRoleServiceImpl implements TbRoleService{

    @Resource
    private TbRoleMapper tbRoleMapper;

}
