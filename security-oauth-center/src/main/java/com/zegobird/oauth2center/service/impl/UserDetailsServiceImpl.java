package com.zegobird.oauth2center.service.impl;

import com.zegobird.oauth2center.domain.TbPermission;
import com.zegobird.oauth2center.domain.TbUser;
import com.zegobird.oauth2center.service.TbPermissionService;
import com.zegobird.oauth2center.service.TbUserService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:32
 */
@Service("userDetaillServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private TbPermissionService tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser tbUser = tbUserService.getByUsername(username);
        if (tbUser == null) {
            throw new UsernameNotFoundException("用户:" + username + ",不存在!");
        }
        List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(tbUser.getId());
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

        tbPermissions.forEach(tbPermission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
            grantedAuthorities.add(grantedAuthority);
        });
        // 标识位设置

        boolean enabled = true; // 可用性 :true:可用 false:不可用
        boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
        boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
        boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定

        return new User(tbUser.getUsername(), tbUser.getPassword(),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked, grantedAuthorities);
    }
}