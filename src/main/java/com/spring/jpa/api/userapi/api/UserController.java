package com.spring.jpa.api.userapi.api;

import com.spring.jpa.api.storeapi.service.StoreService;
import com.spring.jpa.api.userapi.dto.request.LoginRequestDTO;
import com.spring.jpa.api.userapi.entity.User;
import com.spring.jpa.auth.TokenUserInfo;
import com.spring.jpa.utils.exception.DuplicatedEmailException;
import com.spring.jpa.utils.exception.NoRegisteredArgumentsException;
import com.spring.jpa.api.userapi.dto.response.UserSignUpResponseDTO;
import com.spring.jpa.api.userapi.dto.request.UserRequestSignUpDTO;
import com.spring.jpa.api.userapi.dto.response.LoginResponseDTO;
import com.spring.jpa.api.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final StoreService storeService;

    //이메일 중복 확인 요청 처리
    //GET: /api/auth/check?email=zzzz@xxx.com
    @GetMapping("/check")
    public ResponseEntity<?> check(String email) {
        if(email.trim().equals("")) {
            return ResponseEntity.badRequest()
                    .body("이메일이 없습니다!");
        }
        boolean resultFlag = userService.isDuplicate(email);
        log.info("{} 중복?? - {}", email, resultFlag);

        return ResponseEntity.ok().body(resultFlag);
    }

    //회원 가입 요청 처리
    //POST: /api/auth
    @PostMapping
    public ResponseEntity<?> signup(
            @Validated UserRequestSignUpDTO dto,
            BindingResult result
    ) {
        log.info("/api/auth POST - {}", dto);

        if(result.hasErrors()) {
            log.warn(result.toString());
            return ResponseEntity.badRequest()
                    .body(result.getFieldError());
        }

        try {

            UserSignUpResponseDTO responseDTO = userService.create(dto);

            // 장바구니 생성
            storeService.createBasket(dto);
            return ResponseEntity.ok()
                    .body(responseDTO);

        } catch (NoRegisteredArgumentsException e) {
            log.warn("필수 가입 정보를 전달받지 못했습니다.");
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        } catch (DuplicatedEmailException e) {
            log.warn("이메일이 중복되었습니다.");
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        } catch (Exception e) {
            log.warn("기타 예외가 발생했습니다.");
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    //로그인 요청 처리
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(
            @RequestBody LoginRequestDTO dto
    ) {
        try {
            LoginResponseDTO responseDTO
                    = userService.authenticate(dto);

            return ResponseEntity.ok().body(responseDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> user(
            @AuthenticationPrincipal TokenUserInfo userInfo
    ) {
        Optional<User> responseDTO = userService.getUser(userInfo.getEmail());

        return ResponseEntity.ok().body(responseDTO);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> updateUser (
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @RequestBody UserRequestSignUpDTO requestDTO
    ) {
        Optional<User> responseDTO = userService.update(requestDTO, userInfo.getEmail());
        return ResponseEntity.ok().body(responseDTO);
    }



}

