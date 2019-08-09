package com.zegobird.servicehi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zegobird.servicehi.entity.BaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author huanfion
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tbl_employee")
public class Employee extends BaseEntity<Employee> {

    private static final long serialVersionUID=1L;
    @TableId(type=IdType.AUTO)
    private Integer id;

    private String lastName;

    private String email;

    private String gender;

    private Integer age;


   @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
