package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "school")
@Getter
// 회원 테이블
public class Member {

    @Id
    private String id;

    @Column(length = 18, unique = true)
    private String nickname;

    @NotNull
    private String department;

    @NotNull
    @Column(name = "stu_num")
    private String stuNum;

    @NotNull
    @Column(length = 24)
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Builder.Default
    private float level = 0;

    @NotNull
    private boolean isMen;

    @NotNull
    @Column(length = 6)
    private String birth;

    @NotNull
    @Builder.Default
    private boolean isDel = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private School school;
}
