package com.zegobird.oauth2center.service.impl;

import com.zegobird.oauth2center.domain.TbRole;
import com.zegobird.oauth2center.dto.AddRoleInput;
import com.zegobird.oauth2center.dto.query.RoleQuery;
import com.zegobird.oauth2center.mapper.TbRoleMapper;
import com.zegobird.oauth2center.service.TbRoleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:46
 */
@Service
public class TbRoleServiceImpl implements TbRoleService {

    @Resource
    private TbRoleMapper roleMapper;

    @Override
    public List<TbRole> getRoleList(RoleQuery query) {
        Example example=new Example(TbRole.class);
        Example.Criteria c = example.createCriteria();
        if(StringUtil.isNotEmpty(query.getName())){
            c.andLike("name", "%"+query.getName()+"%");
        }
        example.setOrderByClause("id desc");
        return roleMapper.selectByExample(example);
    }

    @Override
    public TbRole add(AddRoleInput input) {
        TbRole role=input.toRole();
        roleMapper.insert(role);
        return  role;
    }

    @Override
    public TbRole getRoleById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer update(TbRole role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Integer delete(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}

