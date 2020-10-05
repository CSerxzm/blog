package com.xzm.bean;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Integer id;

    private Boolean adminComment;

    private String avatar;

    private String content;

    private Date createTime;

    private String email;

    private String nickname;

    private Integer blogId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", adminComment=" + adminComment +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", blogId=" + blogId +
                '}';
    }
}