package com.b2.prj02.dto.response;

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
    private String nickName;
//    private String phoneNumber;
    private String address;
//    private String gender;
//    private Integer payMoney;
    private UserStatus status;

    public static ProfileInfoResponseDTO from(User user){
        return ProfileInfoResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .address(user.getAddress())
                .status(user.getStatus())
                .build();
    }
}
