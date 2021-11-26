package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roomInfo")
@Getter
@Table(name = "Room_Face_Image")
// 모임 대표 사진 테이블
public class RoomFaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String path;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    private RoomInfo roomInfo;
}
