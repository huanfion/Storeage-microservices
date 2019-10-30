package com.zegobird.product.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品信息
 * </p>
 *
 * @author huanfion
 * @since 2019-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_productinfo")
public class Productinfo extends Model<Productinfo> {

    private static final long serialVersionUID=1L;

    /**
     * 产品ID
     */
    @TableId(type = IdType.AUTO)
    private Long productid;

    /**
     * 产品编号（SPU）
     */
    private String productcode;

    /**
     * 产品条码
     */
    private String productbarcode;

    /**
     * 产品名称
     */
    private String productname;

    /**
     * 分类ID
     */
    private Integer typeid;

    /**
     * 分类名称
     */
    private String typename;

    /**
     * 完全路径Id
     */
    private String fulltypeid;

    /**
     * 完成路径
     */
    private String fulltypename;

    /**
     * 单位Id 
     */
    private Integer unitid;

    /**
     * 单位名称
     */
    private String unitname;

    /**
     * 供应商Id
     */
    private Integer supplierid;

    /**
     * 供应商名称
     */
    private String suppliername;

    /**
     * 货主ID
     */
    private Integer consignorid;

    /**
     * 货主编号
     */
    private String consignorcode;

    /**
     * 货主名称
     */
    private String consignorname;

    /**
     * 品牌ID
     */
    private Integer brandid;

    /**
     * 品牌名称
     */
    private String brandname;

    /**
     * 商品主图 （商品主图片是json方式存储，格式：
[{"FileName": "123456","FilePath": "/ProductImg/123456.jpg"} ]
FileName是文件名，FilePath是图片地址）
     */
    private JSONArray productimage;

    /**
     * 采购价 
     */
    private BigDecimal purchaseprice;

    /**
     * 成本价
     */
    private BigDecimal costprice;

    /**
     * 销售价
     */
    private BigDecimal saleprice;

    /**
     * 预售 （0 否 1 是）
     */
    private Integer presell;

    /**
     * 状态 (1 正常  0  禁用)
     */
    private Integer state;

    /**
     * 是否开启规格 (0 关闭  1 开启)
     */
    private Integer isopen;

    /**
     * 排序号
     */
    private Integer orderno;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createid;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdate;

    /**
     * 修改人ID
     */
    @TableField(fill = FieldFill.UPDATE)
    private Integer modifyid;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String modifier;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifydate;

    /**
     * 货币Id 
     */
    @TableField("currencyid")
    private Integer currencyid;

    /**
     * 货币
     */
    private String currency;


    /**
     * 是否保质期
     */
    private Integer isexpiration;
    /**
     * 保质期天数
     */
    private Integer expirationnum;
    /**
     * 禁收时效
     */
    private Integer limitationofreceipt;
    /**
     * 禁售时效
     */
    private Integer limitationofsales;
    /**
     * 预警天数
     */
    private Integer warningdays;
    /**
     * 是否质检
     */
    private Integer qualitycontrol;
    /**
     * 是否扫描条码
     */
    private Integer scanbarcode;
    /**
     * 质检批次属性
     */
    private JSONArray batchattr;
    @Override
    protected Serializable pkVal() {
        return this.productid;
    }

}
