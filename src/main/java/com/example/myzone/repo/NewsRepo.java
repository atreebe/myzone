package com.example.myzone.repo;

import com.example.myzone.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepo extends JpaRepository<News,InternalError> {
}
