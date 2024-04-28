package com.codecrafter.spring_with_flutter.boundecContext.article.service;

import com.codecrafter.spring_with_flutter.base.rs.ResponseDataWrapper;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleCreating;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleDeleting;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleReading;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleUpdating;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.response.ArticlePage;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.response.PageWrapper;
import com.codecrafter.spring_with_flutter.boundecContext.article.entity.Article;
import com.codecrafter.spring_with_flutter.boundecContext.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ResponseDataWrapper create(String username, ArticleCreating articleCreating) {
        Article article = new Article();
        article.setAuthor(username);
        article.setCategory(articleCreating.getCategory());
        article.setTitle(articleCreating.getTitle());
        article.setContent(articleCreating.getContent());
        article.setView(0L);
        articleRepository.save(article);

        return ResponseDataWrapper.of("S-3", "Creating success", null);
    }

    public ResponseDataWrapper modifyArticle(ArticleUpdating articleUpdating) {
        Optional<Article> wrappedArticle = articleRepository.findById(articleUpdating.getId());
        Article article = wrappedArticle.orElseThrow();
        article.setTitle(articleUpdating.getTitle());
        article.setContent(articleUpdating.getContent());
        articleRepository.save(article);
        return ResponseDataWrapper.of("S-5", "Updating success", null);
    }

    public ResponseDataWrapper deleteArticle(ArticleDeleting articleDeleting) {
        articleRepository.deleteById(articleDeleting.getId());
        return ResponseDataWrapper.of("S-6", "Deleting success", null);
    }

    public ResponseDataWrapper<PageWrapper> readArticles(ArticleReading articleRead) {
        List<ArticlePage> articleList = readArticleList(articleRead);
        int lastIdx = articleList.size() - 1;
        long lastId = articleList.get(lastIdx).getId();

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setLastId(lastId);
        pageWrapper.setArticles(articleList);
        return ResponseDataWrapper.of("S-4", "Reading Success", pageWrapper);
    }

    private List<ArticlePage> readArticleList(ArticleReading articleRead) {
        String category = articleRead.getCategory();
        long fromId = articleRead.getFromId();
        int selectRange = articleRead.getSelectRange();

        if(category == null) {
            if(fromId == -1) {
                Optional<List<ArticlePage>> wrappedResult = articleRepository.findFirstArticlePage(selectRange);
                return wrappedResult.orElseThrow();
            }
            Optional<List<ArticlePage>> wrappedResult = articleRepository.findArticlePage(fromId, selectRange);
            return wrappedResult.orElseThrow();
        }

        if(fromId == -1) {
            Optional<List<ArticlePage>> wrappedResult = articleRepository.findFirstArticlePageByCategory(category, selectRange);
            return wrappedResult.orElseThrow();
        }
        Optional<List<ArticlePage>> wrappedResult = articleRepository.findArticlePageByCategory(category, fromId, selectRange);
        return wrappedResult.orElseThrow();
    }
}
