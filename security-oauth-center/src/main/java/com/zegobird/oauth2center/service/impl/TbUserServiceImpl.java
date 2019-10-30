package com.zegobird.oauth2center.service.impl;

import com.zegobird.oauth2center.domain.TbUser;
import com.zegobird.oauth2center.dto.*;
import com.zegobird.oauth2center.dto.query.UserQuery;
import com.zegobird.oauth2center.dto.query.UserQueryByRole;
import com.zegobird.oauth2center.mapper.TbUserMapper;
import com.zegobird.oauth2center.service.TbUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:46
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserMapper userMapper;

    @Override
    public TbUser getByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);
        TbUser user = userMapper.selectOneByExample(example);
        return user;

    }
    @Override
    public List<TbUser> getUserList(UserQuery userQuery) {
        Example example = new Example(TbUser.class);
        Example.Criteria c = example.createCriteria();
        if(StringUtil.isNotEmpty(userQuery.getNickname())){
            c.andLike("nickname", "%"+userQuery.getNickname()+"%");
        }
        if(StringUtil.isNotEmpty(userQuery.getUsername())){
            c.andLike("username","%"+ userQuery.getUsername()+"%");
        }
        if(null != userQuery.getStatus()){
            c.andEqualTo("status",userQuery.getStatus());
        }
        example.setOrderByClause("created desc");
        List<TbUser> list = userMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<UserOutput> getUserOutPutList(UserQuery userQuery) {
        String nickname = userQuery.getNickname();
        String username = userQuery.getUsername();
        Integer status = userQuery.getStatus();
        List<UserOutput> list = userMapper.selectByPage(nickname, username, status);
        return list;
    }

    @Override
    public List<RoleUserOutput> getUserOutPutListByRole(UserQueryByRole userQuery) {
        Long roleid=userQuery.getRoleid();
        List<RoleUserOutput> list = userMapper.selectPageByRole(roleid);
        return list;
    }


    @Override
    public List<TbUser> getUserListByRoleId(Long roleId) {
        return userMapper.selectAllUserByRoleId(roleId);
    }

    @Override
    public TbUser findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    @Override
    @Transactional
    public TbUser add(AddUserInput input) {
        TbUser user=input.toUser();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insertSelective(user);
        input.getRoles().forEach(roleId->userMapper.insertUserRole(user.getId(),roleId,new Date()));
        user.setPassword(null);
        return user;
    }

    @Transactional
    @Override
    public TbUser updateUser(UpdateUserInput input) {
        TbUser user=input.toUser();
        Example example=new Example(TbUser.class);
        example.createCriteria().andEqualTo("id",input.Id);
        user.setUpdated(new Date());
        userMapper.updateByExampleSelective(user,example);
        userMapper.deleteUserRoleByUserId(input.getId());
        input.getRoles().forEach(roleId->userMapper.insertUserRole(user.getId(),roleId,new Date()));
        return  user;
    }

    @Override
    public int updateUserPwd(UserPasswordInput input) {
        TbUser user=new TbUser();
        user.setId(input.getId());
        user.setPassword(new BCryptPasswordEncoder().encode(input.getPassword()));
        Example example=new Example(TbUser.class);
        example.createCriteria().andEqualTo("id",input.Id);
        return userMapper.updateByExampleSelective(user,example);
//        return userMapper.selectOneByExample(example);
    }

    @Override
    public void resetPwd(Long uid, String defaultPassword) {
        TbUser user=userMapper.selectByPrimaryKey(uid);
        user.setPassword(new BCryptPasswordEncoder().encode(defaultPassword));
        user.setUpdated(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateUserStatus(long id, Short status) {
        TbUser user=userMapper.selectByPrimaryKey(id);
        user.setStatus(status);
        Example example=new Example(TbUser.class);
        example.createCriteria().andEqualTo("id",id);
        return  userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<TbUser> queryByName(String name){
        Example example=new Example(TbUser.class);
        example.selectProperties("id", "username");
        example.createCriteria().andEqualTo("username", name);
        List<TbUser> list = userMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<TbUser> queryByNames(String[] names){
        Example example=new Example(TbUser.class);
        example.selectProperties("id", "username");
        Example.Criteria criteria = example.createCriteria();
        if(null != names){
            for(int i=0; i<names.length; i++){
                if(names[i] != null){
                    criteria.orEqualTo("username", names[i]);
                }
            }
        }
        //example.createCriteria().andIn("username", Collections.singleton(ls.iterator()));
        List<TbUser> list = userMapper.selectByExample(example);
        return list;
    }
}

