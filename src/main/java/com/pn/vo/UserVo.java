package com.pn.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pn.entry.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户展示类
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"password", "admin"})
public class UserVo extends User {

}