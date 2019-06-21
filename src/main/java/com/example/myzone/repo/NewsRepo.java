package com.example.myzone.repo;

import com.example.myzone.entity.News;
import com.example.myzone.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepo extends JpaRepository<News,InternalError> {
    public News findByNewsId(int id);

    @Query(value="Select * from news order by news_id desc;",nativeQuery = true)
    public List<News> findDescNews();

    //查询评论
    @Query(value = "Select * from news where news_content like ?1 order by news_id desc;",nativeQuery = true)
    public List<News> queryNews(String querytext);
}
