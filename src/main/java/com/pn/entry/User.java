package com.pn.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description 用户实体类
 * @Author duhongyu
 * @Data 2022/7/14 17:31
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "ip")
    private String ip;
}