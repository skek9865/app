package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "member")
@Getter
// 설정 테이블
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Builder.Default
    @Column(name = "chat_alarm")
    private boolean chatAlarm = true;

    @NotNull
    @Builder.Default
    @Column(name = "push_alarm")
    private boolean push_alarm = true;

    @OneToOne
    @NotNull
    private Member member;
}
