package com.mgh.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author mgh
 * @since 2022-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ucenter_member")
@ApiModel(value="Member对象", description="会员表")
@ToString
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "邮箱")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    private String sign;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    @TableField(fill = FieldFill.INSERT)
    private Integer isDisabled;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
