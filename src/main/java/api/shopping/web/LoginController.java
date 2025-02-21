package api.shopping.web;

import api.shopping.request.MemberRequest;
import api.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/api/signUp")
    public void signUp(@RequestBody MemberRequest request) {
        memberService.signUp(request);
    }

    @PostMapping("/api/Login")
    public void login(@RequestBody MemberRequest request) {
        memberService.signin(request);
    }


}
