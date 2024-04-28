package com.codecrafter.spring_with_flutter.boundecContext.article.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageWrapper {
    private long lastId;
    List<ArticlePage> articles;
}
