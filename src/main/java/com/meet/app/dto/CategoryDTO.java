package com.meet.app.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class CategoryDTO {

    private Long categoryID;

    private String categoryName;
}
