package com.zegobird.product.common.mybatisplus;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zegobird.product.feign.UserClient;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/9/2 16:39
 */
public class CommonMetaObjectHandler implements MetaObjectHandler {
    /**
     * 创建时间
     */
    private final String createDate = "createdate";
    /**
     * 修改时间
     */
    private final String modifyDate = "modifydate";
    /**
     * 创建者ID
     */
    private final String createID = "createid";

    /**
     * 修改者ID
     */
    private final String modifyID = "modifyid";

    /**
     * 创建者 名称
     */
    private final String creator="creator";
    /**
     * 修改者名称
     */
    private final String modifier="modifier";
    @Autowired
    UserClient userClient;

    @Override
    public void insertFill(MetaObject metaObject) {
        setInsertFieldValByName(createDate,  LocalDateTime.now(), metaObject);
        setInsertFieldValByName(createID, getCurrentUser().getInteger("id"), metaObject);
        setInsertFieldValByName(creator,getCurrentUser().getString("username"),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName(modifyDate, LocalDateTime.now(), metaObject);
        setUpdateFieldValByName(modifyID, getCurrentUser().getInteger("id"), metaObject);
        setInsertFieldValByName(modifier,getCurrentUser().getString("username"),metaObject);
    }
    private JSONObject getCurrentUser(){
        String name = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        String response= userClient.getByUsername(name);
        JSONObject o = JSONObject.parseObject(response);
        Integer head = (Integer)(o.getJSONObject("respHeader").get("resultCode"));
        if(head!=0){
            throw new UsernameNotFoundException("找不到该用户，用户名：" + name);
        }
        return  o.getJSONObject("respBody").getJSONObject("res");
    }
}
