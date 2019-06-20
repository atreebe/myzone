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
    private int newsauthorid;
    @Column(name = "news_title",length = 256)
    private String newsTitle;
    @Column(name = "news_content",columnDefinition="text")
    private String newsContent;
    @Column(name = "news_summary",columnDefinition="text")
    private String newsSummary;
    @Column(name = "news_pic",length = 256)
    private String newsPic;
    @Column(name = "news_good",length = 8)
    private int newsgood;
    @Column(name = "news_time")
    @CreatedDate
    @JsonFormat(pattern="yyyy-mm-dd hh:mm:ss")
    private Date newsTime;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getNewsauthorid() {
        return newsauthorid;
    }

    public void setNewsauthorid(int newsauthorid) {
        this.newsauthorid = newsauthorid;
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
    public int getNewsgood(){
        return newsgood;
    }
    public void setNewsgood(int newsgood) {
        this.newsgood = newsgood;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }
}
