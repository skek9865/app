package com.meet.app.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class MyRoomDTO {

    private RoomInfoDTO roomInfoDTO;

    // 모임 탈퇴 여부
    private boolean isOut;
}
