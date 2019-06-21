package com.example.myzone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id",length = 8)
    private int commentId;
    @Column(name = "comment_content",length = 256)
    private String commentContent;
    @Column(name = "comment_author",length = 16)
    private String commentAuthor;
    @Column(name = "comment_good",length=8)
    private int commentGood;
    @Column(name = "news_id",length = 8)
    private int commentNewsId;
    @Column(name = "comment_time")
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date commentTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentGood() {
        return commentGood;
    }

    public void setCommentGood(int commentGood) {
        this.commentGood = commentGood;
    }

    public int getCommentNewsId() {
        return commentNewsId;
    }

    public void setCommentNewsId(int commentNewsId) {
        this.commentNewsId = commentNewsId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }
}
