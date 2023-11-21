package com.b2.prj02.service;

import com.b2.prj02.dto.request.UserDeleteRequestDTO;
import com.b2.prj02.dto.request.UserLoginRequestDTO;
import com.b2.prj02.dto.request.UserSignupRequestDTO;
import com.b2.prj02.entity.User;
import com.b2.prj02.repository.ProfileRepository;
import com.b2.prj02.role.UserStatus;
import com.b2.prj02.repository.UserRepository;
import com.b2.prj02.service.jwt.JwtTokenProvider;
import com.b2.prj02.service.jwt.TokenBlacklist;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    public ResponseEntity<?> signup(UserSignupRequestDTO user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            User newUser = User.builder()
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .address(user.getAddress())
                    .gender(user.getGender())
                    .phoneNumber(user.getPhoneNumber())
                    .status(UserStatus.USER)
                    .build();

            userRepository.save(newUser);

            return ResponseEntity.status(200).body("회원가입에 성공하셨습니다.");
        }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이미 가입된 정보입니다.");
    }

    public ResponseEntity<?> login(UserLoginRequestDTO user) {
        Optional<User> loginUser = userRepository.findByEmail(user.getEmail());
        if(loginUser.isPresent()){
            String newToken = passwordEncoder.matches(user.getPassword(), loginUser.get().getPassword())
                    ?jwtTokenProvider.createToken(user.getEmail(), loginUser.get().getStatus()):null;

            if(newToken!=null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(newToken);
                return ResponseEntity.status(200).headers(headers).body("반갑습니다 " + user.getEmail() + "님");
            }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호를 다시 확인해주세요.");
        }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이메일을 다시 확인해주세요.");
    }

    // 수정
//    public ResponseEntity<?> login(String email, String password) {
//        User user = getUserByEmail(email);
//        if(!passwordEncoder.matches(password, user.getPassword())){
//            String newToken = jwtTokenProvider.createToken(user.getUserId(), user.getStatus());
//
//            if(newToken!=null) {
//                HttpHeaders headers = new HttpHeaders();
//                headers.setBearerAuth(newToken);
//                return ResponseEntity.status(200).headers(headers).body("반갑습니다 " + user.getEmail() + "님");
//            }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호를 다시 확인해주세요.");
//        }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이메일을 다시 확인해주세요.");
//    }

    public ResponseEntity<?> logout(String token) {
        try {
            if(userRepository.findByEmail(jwtTokenProvider.findEmailBytoken(token)).isPresent()) {
                TokenBlacklist.addToBlacklist(token);
                return ResponseEntity.status(200).body("이용해 주셔서 감사합니다.");
            }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("로그아웃에 실패하셨습니다.");
        }catch (Exception e){
            e.printStackTrace();
            throw new MalformedJwtException("로그아웃에 실패하셨습니다.");
        }
    }

    public ResponseEntity<?> deleteUser(String token, UserDeleteRequestDTO deleteUser) {
        String email = jwtTokenProvider.findEmailBytoken(token);
        Optional<User> storedUser = userRepository.findByEmail(email);

        User updatedUser = storedUser.map(user -> user.updateStatus(UserStatus.DELETED))
                .orElseThrow(() -> new RuntimeException("없는 유저입니다."));

        if(email.equals(deleteUser.getEmail()) && passwordEncoder.matches(deleteUser.getPassword(), storedUser.get().getPassword())){
            userRepository.save(updatedUser);
            return ResponseEntity.status(200).body("회원 탈퇴되셨습니다.");
        }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이메일 또는 비밀번호가 틀렸습니다.");

    }

//    private User getUserByEmail(String email){
//        return profileRepository.findByEmail(email).orElseThrow(()->new RuntimeException("유저가 존재하지 않습니다."));
//    }
}
