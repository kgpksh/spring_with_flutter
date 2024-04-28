package com.codecrafter.spring_with_flutter.boundecContext.article.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ArticleReading {
    private String category;
    private long fromId;
    @NotNull(message="반드시 숫자가 입력 되어야 합니다.")
    @Max(value=50, message="Number of selecting items over limited.")
    private int selectRange;
}
