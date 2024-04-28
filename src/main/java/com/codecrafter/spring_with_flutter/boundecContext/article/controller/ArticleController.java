package com.codecrafter.spring_with_flutter.boundecContext.article.controller;

import com.codecrafter.spring_with_flutter.base.rs.ResponseDataWrapper;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleCreating;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleDeleting;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleReading;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleUpdating;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.response.PageWrapper;
import com.codecrafter.spring_with_flutter.boundecContext.article.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/createArticle/v1")
    public ResponseEntity<ResponseDataWrapper> createArticle(@RequestBody @Valid ArticleCreating articleCreating, Authentication authentication) {
        ResponseDataWrapper responseDataWrapper = articleService.create(authentication.getName(), articleCreating);
        return ResponseEntity.ok(responseDataWrapper);
    }

    @GetMapping("/readArticleList/v1")
    public ResponseEntity<ResponseDataWrapper<PageWrapper>> readPageArticle(@RequestBody @Valid ArticleReading articleRead,
                                                                     BindingResult bindingResult) {
        ResponseDataWrapper<PageWrapper> result = articleService.readArticles(articleRead);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/updateArticle/v1")
    public ResponseEntity<ResponseDataWrapper> updateArticle(@RequestBody @Valid ArticleUpdating articleUpdating) {
        ResponseDataWrapper responseDataWrapper = articleService.modifyArticle(articleUpdating);
        return ResponseEntity.ok(responseDataWrapper);
    }

    @DeleteMapping("/deleteArticle/v1")
    public ResponseEntity<ResponseDataWrapper> deleteArticle(@RequestBody @Valid ArticleDeleting articleDeleting) {
        ResponseDataWrapper responseDataWrapper = articleService.deleteArticle(articleDeleting);
        return ResponseEntity.ok(responseDataWrapper);
    }
}
