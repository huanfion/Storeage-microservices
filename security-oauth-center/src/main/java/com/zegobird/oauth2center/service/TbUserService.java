package com.zegobird.oauth2center.service;

import com.zegobird.oauth2center.domain.TbUser;

import java.util.List;

/**
 * @version 1.0
 * @author huanfion
 * @date 2019/8/8 19:46
 */
public interface TbUserService{

        TbUser getByUsername(String username);
        List<TbUser> getUserList();
}
