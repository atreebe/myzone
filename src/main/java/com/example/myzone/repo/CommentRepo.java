package com.example.myzone.repo;

import com.example.myzone.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
