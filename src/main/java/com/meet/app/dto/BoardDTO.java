package com.meet.app.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class BoardDTO {

    private Long boardID;

    private String title;

    private String content;

    private LocalDate regDate;

    private Long roomID;

    private String memberID;
}
