package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
// 개인정보 동의서 테이블
public class Info{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String information;
}
