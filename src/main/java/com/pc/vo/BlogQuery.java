package com.pc.vo;

import com.pc.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {
    private Integer id;
    private String title;
    private Integer recommended;
    private Date updateTime;
    private Integer typeId;
    private Type type;
}
