package com.codecrafter.spring_with_flutter.boundecContext.article.dto.response;

import lombok.Getter;

import java.util.Date;

@Getter
public class ArticlePageImpl implements ArticlePage {
    private long id;
    private Date last_modified_date;
    private String nickname;
    private String category;
    private String title;
    private long view;
}
