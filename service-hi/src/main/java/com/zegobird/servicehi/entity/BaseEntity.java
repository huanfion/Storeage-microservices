package com.zegobird.servicehi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/7/12 17:41
 */
@Data
@NoArgsConstructor
public class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable {
    /**
     * 创建者ID
     */
    @TableField(fill = FieldFill.INSERT)
    protected String createUid;

    /**
     * 修改者ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String updateUid;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;
}
