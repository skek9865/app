package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
// 카테고리 테이블
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "category_name")
    private String categoryName;

    @NotNull
    @Column(name = "inquiry_sum")
    @Builder.Default
    private Long count = 0L;
}
