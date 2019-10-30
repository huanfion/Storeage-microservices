package com.zegobird.oauth2center.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.zegobird.common.framework.converter.BeanConverter;
import com.zegobird.common.util.TreeUtils;
import com.zegobird.oauth2center.domain.TbPermission;
import com.zegobird.oauth2center.dto.FunctionPermission;
import com.zegobird.oauth2center.dto.PermissionTree;
import com.zegobird.oauth2center.dto.query.PermissionQuery;
import com.zegobird.oauth2center.mapper.TbPermissionMapper;
import com.zegobird.oauth2center.service.TbPermissionService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * @version 1.0
 * @author huanfion
 * @date 2019/8/8 19:47
 */
@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper permissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return permissionMapper.selectByUserId(userId);
    }

    @Override
    public List<TbPermission> getPermissionList(PermissionQuery query) {
        Example example = new Example(TbPermission.class);
        Example.Criteria c = example.createCriteria();
        if(StringUtil.isNotEmpty(query.getEnname())){
            c.andLike("enname", "%"+query.getEnname()+"%");
        }
        if(StringUtil.isNotEmpty(query.getName())){
            c.andLike("username","%"+ query.getName()+"%");
        }
        c.andEqualTo("status",query.getStatus());
        c.andEqualTo("isdeleted",0);
        example.setOrderByClause("created desc");
        return permissionMapper.selectByExample(example);
    }

    @Override
    public int update(TbPermission permission) {
       return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public int delete(Long id){
        TbPermission permission = permissionMapper.selectByPrimaryKey(id);
        permission.setUpdated(new Date());
        permission.setIsdeleted((short) 1);
        return permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public int add(TbPermission permission) {
        return permissionMapper.insert(permission);
    }

    @Override
    public List<PermissionTree> getPermissionTree() {
        List<TbPermission> permissionList = permissionMapper.selectAll();
        List<PermissionTree> tree = BeanConverter.convert(PermissionTree.class, permissionList);
        return tree.stream().filter(e -> !parentIdNotNull(e.getParentId()))
                .map(e -> TreeUtils.findChildren(e, tree)).collect(toList());
    }

    @Override
    public List<PermissionTree> getFunctionTree() {
        List<FunctionPermission> permissionList = permissionMapper.getFunctionList();
        List<PermissionTree> tree = BeanConverter.convert(PermissionTree.class, permissionList);
        return tree.stream().filter(e -> !parentIdNotNull(e.getParentId()))
                .map(e -> TreeUtils.findChildren(e, tree)).collect(toList());
    }

    @Override
    public List<PermissionTree> getAllRoleFunctionTree(Long roleId) {
        List<FunctionPermission> permissionList = permissionMapper.getAllFunctionsByRole(roleId);
        List<PermissionTree> tree = BeanConverter.convert(PermissionTree.class, permissionList);
        return tree.stream().filter(e -> !parentIdNotNull(e.getParentId()))
                .map(e -> TreeUtils.findChildren(e, tree)).collect(toList());
    }

    @Override
    public List<FunctionPermission> getRoleFunctions(Long roleId){
        return permissionMapper.getRoleFunctions(roleId);
    }

    /**
     * 父ID不为0并且不为空
     *
     * @param parentId
     * @return
     */
    private boolean parentIdNotNull(Long parentId) {
        boolean flag = Objects.nonNull(parentId) && parentId != 0;
        return flag;
    }
}
