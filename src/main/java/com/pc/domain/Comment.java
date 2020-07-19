package com.pc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    private int id;
    private String nickName;
    private String content;
    private String avatar;
    private Date createTime;
}
