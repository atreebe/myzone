package com.example.myzone.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id",length = 8)
    private int CommentId;
    @Column(name = "comment_content",length = 256)
    private String commentContent;
    @Column(name = "comment_good",length=8)
    private int commentGood;
    @Column(name = "news_id",length = 8)
    private int commentNewsId;

    public int getCommentId() {
        return CommentId;
    }

    public void setCommentId(int commentId) {
        CommentId = commentId;
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
}
