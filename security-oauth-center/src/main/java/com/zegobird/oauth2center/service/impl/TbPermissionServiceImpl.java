package com.zegobird.oauth2center.service.impl;

import com.zegobird.oauth2center.domain.TbPermission;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zegobird.oauth2center.mapper.TbPermissionMapper;
import com.zegobird.oauth2center.service.TbPermissionService;

import java.util.List;

/**
 * @version 1.0
 * @author huanfion
 * @date 2019/8/8 19:47
 */
@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }


}
