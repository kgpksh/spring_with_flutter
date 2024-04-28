package com.codecrafter.spring_with_flutter.boundecContext.article.controller;

import com.codecrafter.spring_with_flutter.base.rs.ResponseDataWrapper;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleCreating;
import com.codecrafter.spring_with_flutter.boundecContext.article.dto.request.ArticleRead;
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

    @PostMapping("/create")
    public ResponseEntity<ResponseDataWrapper> create(@RequestBody @Valid ArticleCreating articleCreating, Authentication authentication) {
        ResponseDataWrapper responseDataWrapper = articleService.create(authentication.getName(), articleCreating);
        return ResponseEntity.ok(responseDataWrapper);
    }

    @GetMapping("/read")
    public ResponseEntity<ResponseDataWrapper<PageWrapper>> readPage(@RequestBody @Valid ArticleRead articleRead,
                                                                     BindingResult bindingResult) {
        ResponseDataWrapper<PageWrapper> result = articleService.readArticles(articleRead);
        return ResponseEntity.ok(result);
    }
}
