package com.codecrafter.spring_with_flutter.boundecContext.article.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@JsonDeserialize(as = ArticlePageImpl.class)
public interface ArticlePage {
    long getId();
    Date getLast_modified_date();
    String getNickname();
    String getCategory();
    String getTitle();
    long getView();
}
