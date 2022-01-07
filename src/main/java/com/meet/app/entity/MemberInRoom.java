package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roomInfo","member"})
@Getter
@Table(name = "Member_room")
// 각 모임의 참가 회원 테이블
public class MemberInRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 방장 여부
    @NotNull
    private boolean isRoomMaster;

    // 모임 탈퇴 여부
    @NotNull
    @Builder.Default
    private boolean isEnd = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private RoomInfo roomInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;
}
