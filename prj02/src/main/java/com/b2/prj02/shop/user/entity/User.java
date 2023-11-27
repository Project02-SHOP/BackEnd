package com.b2.prj02.shop.user.entity;

import com.b2.prj02.shop.user.role.CustomGrantedAuthority;
import com.b2.prj02.shop.user.role.UserActiveStatus;
import com.b2.prj02.shop.user.role.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userId;

    private String email;
    private String password;

    @Column(name = "nick_name")
    private String nickName;

    private String filePath;

    private String address;
    private String gender;

    @Column(name = "pay_money")
    private Double payMoney;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "user_active_status")
    @Enumerated(EnumType.STRING)
    private UserActiveStatus userActiveStatus;

    public void deleteUser() {
        this.userActiveStatus = UserActiveStatus.DELETED;
    }

    private Integer stack;

    public void addStack() {
        this.stack++;
    }

    public void resetStack(){
        this.stack = 0;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void buy(Double totalPrice) {
        if(this.payMoney<totalPrice)
            throw new DisabledException("잔고가 부족합니다.");
        this.payMoney-=totalPrice;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new CustomGrantedAuthority(this.userRole));
    }

    @Override
    public String getUsername() {
        return email;
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
