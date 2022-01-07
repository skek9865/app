package com.meet.app.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "school")
@Getter
// 회원 테이블
public class Member implements UserDetails {

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

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public void modifyUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public void deleteUser() {
        this.isDel = true;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
