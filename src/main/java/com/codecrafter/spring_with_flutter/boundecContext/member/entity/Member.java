package com.codecrafter.spring_with_flutter.boundecContext.member.entity;

import com.codecrafter.spring_with_flutter.base.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member extends BaseEntity {
    private String username;
    private String nickname;
    private String password;
    private boolean isAdmin;
    private String providerTypeCode;

    public List<? extends GrantedAuthority> takeGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

        if (isAdmin) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return grantedAuthorities;
    }
}
