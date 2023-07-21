package com.spring.jpa.api.userapi.service;

import com.spring.jpa.api.userapi.dto.UserSignUpResponseDTO;
import com.spring.jpa.api.userapi.dto.request.LoginRequestDTO;
import com.spring.jpa.api.userapi.dto.request.UserRequestSignUpDTO;
import com.spring.jpa.api.userapi.dto.response.LoginResponseDTO;
import com.spring.jpa.api.userapi.entity.User;
import com.spring.jpa.api.userapi.repository.UserRepository;
import com.spring.jpa.auth.TokenProvider;
import com.spring.jpa.auth.TokenUserInfo;
import com.spring.jpa.utils.exception.DuplicatedEmailException;
import com.spring.jpa.utils.exception.NoRegisteredArgumentsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;

//    @Value("${upload.path}")
//    private String uploadRootPath;

    //회원 가입 처리
    public UserSignUpResponseDTO create(
            final UserRequestSignUpDTO dto
    )
            throws RuntimeException {

        String email = dto.getEmail();
        if(dto == null) {
            throw new NoRegisteredArgumentsException("가입 정보가 없습니다.");
        }

        if(isDuplicate(email)) {
            log.warn("이메일이 중복되었습니다. - {}", email);
            throw new DuplicatedEmailException("중복된 이메일 입니다.");
        }

        //패스워드 인코딩
        String encoded = encoder.encode(dto.getPassword());
        dto.setPassword(encoded);

        //유저 엔터티로 변환
        User user = dto.toEntity();

        User saved = userRepository.save(user);

        log.info("회원 가입 정상 수행됨! - saved user - {}", saved);

        return new UserSignUpResponseDTO(saved);
    }

    public boolean isDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    // 회원 인증
    public LoginResponseDTO authenticate(final LoginRequestDTO dto) {

        // 이메일을 통해 회원 정보를 조회.
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("가입된 회원이 아닙니다!")
                );

        //패스워드 검증
        String rawPassword = dto.getPassword(); // 입력 비번
        String encodedPassword = user.getPassword(); // DB에 저장된 비번

        if(!encoder.matches(rawPassword, encodedPassword)) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        log.info("{}님 로그인 성공!", user.getUserName());

        // 로그인 성공 후에 클라이언트에게 뭘 리턴할 것인가??
        // -> JWT를 클라이언트에게 발급 해 줘야 함.
        String token = tokenProvider.createToken(user);

        return new LoginResponseDTO(user, token);
    }

}