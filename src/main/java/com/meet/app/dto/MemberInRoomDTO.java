package com.meet.app.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
public class MemberInRoomDTO {

    private Long roomID;

    private String memberID;

    // 모임 탈퇴 여부
    private boolean isEnd;
}
