package com.meet.app.dto;

import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PrisonDTO {

    private Long prisonID;

    private String title;

    private String content;

    private String memberID;

    private String badMemberID;
}
