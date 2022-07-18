package com.pn.entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.pn.support.validate.AddValidationGroup;
import com.pn.support.validate.ExpireValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 用户实体类
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
@Accessors(chain = true)
public class User extends BaseEntity {

    @NotEmpty(message = "邮箱不能为空", groups = {ExpireValidationGroup.class})
    @ApiModelProperty(value = "邮箱")
    @Email(message = "invalid email")
    private String email;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @NotEmpty(message = "密码不能为空", groups = {ExpireValidationGroup.class})
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "管理员")
    private Boolean admin;
}