package hi.place.controller;

import hi.place.dto.user.auth.UserFirstRegisterRequestDto;
import hi.place.dto.user.UserLoginDto;
import hi.place.dto.user.auth.FirstRegisterResponseDto;
import hi.place.dto.user.auth.UserSecondRegisterRequestDto;
import hi.place.dto.user.auth.UserThirdRegisterRequestDto;
import hi.place.model.user.User;
import hi.place.security.jwt.JwtTokenProvider;
import hi.place.service.AuthenticationService;
import hi.place.service.mapper.AuthenticationUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationUserMapper authMapper;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) throws RuntimeException {
        User user = authenticationService.login(
                userLoginDto.getEmail(), userLoginDto.getPassword());

        Set<User.UserType> roles = new HashSet<>();
        roles.add(user.getType());
        String token = jwtTokenProvider.createToken(user.getEmail(), roles);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("userId", user.getId().toString());
        return new ResponseEntity<>(tokenMap, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<FirstRegisterResponseDto> createNewUser(@RequestBody UserFirstRegisterRequestDto newUser) {
        User registeredUser = authenticationService.register(authMapper.toModalFromFirstRegisterDto(newUser));
        return new ResponseEntity<>(new FirstRegisterResponseDto(registeredUser.getId()), HttpStatus.CREATED);
    }

    @PutMapping("/register/second")
    public ResponseEntity<FirstRegisterResponseDto> registerSecondStep(@RequestBody UserSecondRegisterRequestDto newUserData) {
        User updated = authenticationService.updateData(authMapper.toModalFromSecondRegisterDto(newUserData));
        return new ResponseEntity<>(new FirstRegisterResponseDto(updated.getId()), HttpStatus.OK);
    }

    @PutMapping("/register/third")
    public ResponseEntity<FirstRegisterResponseDto> registerThirdStep(@RequestBody UserThirdRegisterRequestDto newUserData) {
        User updated = authenticationService.updateData(authMapper.toModalFromThirdRegisterDto(newUserData));
        return new ResponseEntity<>(new FirstRegisterResponseDto(updated.getId()), HttpStatus.OK);
    }
}
