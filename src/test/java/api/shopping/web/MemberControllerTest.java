package api.shopping.web;

import api.shopping.domain.Member;
import api.shopping.request.MemberRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MemberController memberController;

    @Test
    @Rollback(value = false)
    void 맴버_저장하기() {
        MemberRequest hong = new MemberRequest("홍길동", "hong1234@naver.com", "1234");
        memberController.save(hong);

    }

}