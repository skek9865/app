package com.meet.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class RoomInfoDTO {

    private Long id;

    private String title;

    // 정기 모임 여부
    private boolean periodic;

    // 모임 시작 날짜
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;

    // 모임 끝 날짜
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;

    private int nowPeople;

    private int maxPeople;

    // 선호 옵션
    private String prefer;

    // 활동 장소
    private String place;

    private int views;

    private Long categoryID;

    private Integer schoolID;

    private String memberID;

    // 모임 횟수
    private Integer frequency;

    // 모임 마감 여부
    private boolean isEnd;

    // 모임 삭제 여부
    private boolean isDel;
}
