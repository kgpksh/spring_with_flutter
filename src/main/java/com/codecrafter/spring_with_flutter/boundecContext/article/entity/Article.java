package com.codecrafter.spring_with_flutter.boundecContext.article.entity;

import com.codecrafter.spring_with_flutter.base.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "article", indexes = {@Index(name = "index_author", columnList = "author")})
public class Article extends BaseEntity {
    private String author;
    private String category;
    private String title;
    private String content;
    private long view;
}
