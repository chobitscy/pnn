package com.pn.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pn.entry.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 用户展示类
 * @Author chobit
 * @Data 2022/7/14 17:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"password"})
public class UserVo extends User {

}