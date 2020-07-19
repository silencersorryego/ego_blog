package com.pc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int id;
    private String nickName;
    private String userName;
    private String password;
    private String email;
    private boolean type;
    private String avatar;
    private Date createTime;
    private Date updateTime;
}
