package com.cmdc.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author : wuwensheng
 * @date : 10:37 2020/7/1
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false,of = "userId")
@Builder
@Table(name = "sys_user")
@Accessors(chain = true)
public class User {
    private Integer id;
    private String userId;
    private String userName;
    private String password;
    private String userRemarks;


    public static User getUser(String userId, String userName, String encryptPassword, String remark) {
        return User.builder().userId(userId).userName(userName).password(encryptPassword).userRemarks(remark).build();
    }
}
