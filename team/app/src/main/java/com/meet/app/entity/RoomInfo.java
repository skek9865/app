package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"category","school"})
@Getter
@Table(name = "Room_Info")
// 모임 정보 테이블
public class RoomInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    // 정기 모임 여부
    @NotNull
    @Column(name = "isPeriodic")
    private boolean periodic;

    // 모임 시작 날짜
    @NotNull
    private LocalDate startDate;

    // 모임 끝 날짜
    @NotNull
    private LocalDate endDate;

    @NotNull
    @Builder.Default
    @Column(name = "present_people")
    private Integer nowPeople = 1;

    @NotNull
    @Column(name = "maximum_people")
    private Integer maxPeople;

    // 선호 옵션
    @Column(name = "preferred_option")
    private String prefer;

    // 활동 장소
    @NotNull
    @Column(name = "active_place")
    private String place;

    @NotNull
    @Builder.Default
    private Integer views = 0;

    // 모임 횟수
    private Integer frequency;

    // 모임 마감 여부
    @NotNull
    @Builder.Default
    private boolean isEnd = false;

    // 모임 삭제 여부
    @NotNull
    @Builder.Default
    private boolean isDel = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private School school;

}
