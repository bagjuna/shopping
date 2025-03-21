package api.shopping.web;

import api.shopping.config.AppConfig;
import api.shopping.config.UserPrincipal;
import api.shopping.request.Signup;
import api.shopping.response.MemberResponse;
import api.shopping.service.AuthService;
import api.shopping.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
//    private final AppConfig appConfig;
    private final MemberService memberService;

    @GetMapping("/api/users/me")
    public ResponseEntity<MemberResponse> getMe(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        MemberResponse userResponse = memberService.getUserProfile(userPrincipal.getUserId());
        return ResponseEntity.ok().body(userResponse);
    }


    @PostMapping("/api/auth/signup")
    public void signup(@RequestBody Signup signup) {
        log.info("signup: {}", signup);
        authService.signup(signup);
    }

    @GetMapping("/api/usertest")
    public String user(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userPrincipal.getUsername();
    }

}
