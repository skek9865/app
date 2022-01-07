package com.meet.app.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class MemberDTO {

    private String memberID;

    private String nickname;

    private String department;

    private String stuNum;

    private String password;

    private String name;

    private int level;

    private boolean isMen;

    private String birth;

    private boolean isDel = false;

    private Integer schoolID;
}
