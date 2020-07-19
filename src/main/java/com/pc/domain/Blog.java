package com.pc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private int id;
    private String title;
    private String firstPicture;
    private String flag;
    private String content;
    private String description;
    private int views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommended;
    private Date createTime;
    private Date updateTime;

    private int userId;
    private int typeId;
    private String tagIds;
    private User user;
    private List<Tag> tags;
    private Type type;

    public Blog(String title, String firstPicture, String flag, String content, String description, int views, boolean appreciation, boolean shareStatement, boolean commentabled, boolean published, boolean recommended, Date createTime, Date updateTime) {
        this.title = title;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.content = content;
        this.description = description;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatement = shareStatement;
        this.commentabled = commentabled;
        this.published = published;
        this.recommended = recommended;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
