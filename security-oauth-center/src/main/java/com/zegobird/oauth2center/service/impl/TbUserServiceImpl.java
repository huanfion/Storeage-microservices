package com.zegobird.oauth2center.service.impl;

import com.zegobird.oauth2center.domain.TbUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zegobird.oauth2center.mapper.TbUserMapper;
import com.zegobird.oauth2center.service.TbUserService;
import tk.mybatis.mapper.entity.Example;

/**
 * @version 1.0
 * @author huanfion
 * @date 2019/8/8 19:46
 */
@Service
public class TbUserServiceImpl implements TbUserService{

    @Resource
    private TbUserMapper tbUserMapper;
    @Override
    public TbUser getByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username",username);
        return tbUserMapper.selectOneByExample(example);

    }
}
