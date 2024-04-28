package com.codecrafter.spring_with_flutter.boundecContext.article.repository;

import com.codecrafter.spring_with_flutter.boundecContext.article.dto.response.ArticlePage;
import com.codecrafter.spring_with_flutter.boundecContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = """
                SELECT a.id, a.last_modified_date, nickname, category, title, `view`
                FROM article as a
                JOIN member ON a.author = member.username
                WHERE category = :category AND a.deleted_date IS NULL
                ORDER BY id DESC LIMIT :selectRange
            """, nativeQuery = true)
    Optional<List<ArticlePage>> findFirstArticlePageByCategory(@Param("category") String category, @Param("selectRange") int selectRange);

    @Query(value = """
                SELECT a.id, a.last_modified_date, nickname, category, title, `view`
                FROM article as a
                JOIN member ON a.author = member.username
                WHERE category = :category AND a.id < :fromId AND a.deleted_date IS NULL
                ORDER BY id DESC LIMIT :selectRange
            """, nativeQuery = true)
    Optional<List<ArticlePage>> findArticlePageByCategory(@Param("category") String category, @Param("fromId") long fromId,
                                                          @Param("selectRange") int selectRange);

    @Query(value = """
                SELECT a.id, a.last_modified_date, nickname, category, title, `view`
                FROM article as a
                JOIN member ON a.author = member.username
                WHERE a.deleted_date IS NULL
                ORDER BY id DESC LIMIT :selectRange
            """, nativeQuery = true)
    Optional<List<ArticlePage>> findFirstArticlePage(@Param("selectRange") int selectRange);

    @Query(value = """
                SELECT a.id, a.last_modified_date, nickname, category, title, `view`
                FROM article as a
                JOIN member ON a.author = member.username
                WHERE a.id < :fromId AND a.deleted_date IS NULL
                ORDER BY id DESC LIMIT :selectRange
            """, nativeQuery = true)
    Optional<List<ArticlePage>> findArticlePage(@Param("fromId") long fromId, @Param("selectRange") int selectRange);
}
