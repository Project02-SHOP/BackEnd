package com.b2.prj02.shop.user.dto.request;


import com.b2.prj02.shop.user.role.UserRole;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequestDTO {
    private String email;
    private String nickName;
    private String password;
    private String address;
    private String gender;
    private UserRole userRole;
    private String filePath;
}
