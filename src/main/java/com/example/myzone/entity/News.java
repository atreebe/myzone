package com.example.myzone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")
@EntityListeners(AuditingEntityListener.class)
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id",length = 8)
    private int newsId;
    @Column(name = "news_authorid",length = 8)
    private int newsAuthorId;
    @Column(name = "news_title",length = 256)
    private String newsTitle;
    @Column(name = "news_content",columnDefinition="text")
    private String newsContent;
    @Column(name = "news_summary",columnDefinition="text")
    private String newsSummary;
    @Column(name = "news_pic",length = 256)
    private String newsPic;
    @Column(name = "news_good",length = 8)
    private int newsGood;
    @Column(name = "news_comment",length = 8)
    private int newsComment;
    @Column(name = "news_time")
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date newsTime;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getNewsAuthorId() {
        return newsAuthorId;
    }

    public void setNewsAuthorId(int newsAuthorId) {
        this.newsAuthorId = newsAuthorId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsSummary() {
        return newsSummary;
    }

    public void setNewsSummary(String newsSummary) {
        this.newsSummary = newsSummary;
    }

    public String getNewsPic() {
        return newsPic;
    }

    public void setNewsPic(String newsPic) {
        this.newsPic = newsPic;
    }

    public int getNewsGood() {
        return newsGood;
    }

    public void setNewsGood(int newsGood) {
        this.newsGood = newsGood;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public int getNewsComment() {
        return newsComment;
    }

    public void setNewsComment(int newsComment) {
        this.newsComment = newsComment;
    }
}
