package com.pn.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
/**
 * @Description 用户实体类
 * @Author chobit
 * @Data 2022/7/14 17:31
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class User extends BaseEntity {

    @NotEmpty(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    @Email(message = "invalid email")
    private String email;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "ip")
    private String ip;
}