package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "board")
@Getter
@Table(name = "Room_Activity_Image")
// 모임 활동 사진 테이블
public class RoomActivityImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String path;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private Board board;
}
