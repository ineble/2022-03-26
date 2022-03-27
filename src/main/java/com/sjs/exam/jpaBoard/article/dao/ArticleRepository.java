package com.sjs.exam.jpaBoard.article.dao;

import com.sjs.exam.jpaBoard.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
