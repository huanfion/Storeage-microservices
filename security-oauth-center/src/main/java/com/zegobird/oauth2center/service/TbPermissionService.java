package com.zegobird.oauth2center.service;

import com.zegobird.oauth2center.domain.TbPermission;

import java.util.List;

/**
 * @version 1.0
 * @author huanfion
 * @date 2019/8/8 19:47
 */
public interface TbPermissionService{
    List<TbPermission> selectByUserId(Long userId);

}
