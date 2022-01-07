package com.meet.app.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class RoomFaceImageDTO {

    private Long roomID;

    private String fileBase64;
}
