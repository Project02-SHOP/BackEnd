package com.b2.prj02.dto;

import com.b2.prj02.entity.User;
import com.b2.prj02.role.UserStatus;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProfileInfoResponseDTO {

    private Long userId;
    private String email;
    private String phoneNumber;
    private String address;
    private String gender;
    private Integer payMoney;
    private UserStatus status;

    public static ProfileInfoResponseDTO from(User user){
        return ProfileInfoResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .gender(user.getGender())
                .payMoney(user.getPayMoney())
                .status(user.getStatus())
                .build();
    }
}
