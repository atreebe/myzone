package com.example.myzone.repo;

import com.example.myzone.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    @Query(value="Select * from comment order by comment_id desc;",nativeQuery = true)
    public List<Comment> findDescComment();

    //查询评论
    @Query(value = "Select * from comment where comment_content like ?1 order by comment_id desc;",nativeQuery = true)
    public List<Comment> queryComment(String querytext);

    public Comment findByCommentId(int id);
}
