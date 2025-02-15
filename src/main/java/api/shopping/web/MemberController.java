package api.shopping.web;

import api.shopping.domain.Member;
import api.shopping.request.MemberRequest;
import api.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    

    @PostMapping("/saveMember")
    public void save(@RequestBody MemberRequest request) {
        memberService.save(new Member(request.getName(), request.getEmail(), request.getPassword()));

    }



}
