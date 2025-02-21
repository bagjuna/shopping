package api.shopping.service;

import api.shopping.domain.Member;
import api.shopping.execption.UserNotFound;
import api.shopping.repository.MemberRepository;
import api.shopping.request.MemberRequest;
import api.shopping.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }


    public void signUp(MemberRequest request) {
        memberRepository.save(new Member(request.getName(), request.getEmail(), request.getPassword()));
    }

    public void signin(MemberRequest request) {
        memberRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

    }


    public MemberResponse getUserProfile(Long userId) {
        Member user = memberRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        return new MemberResponse(user);
    }

}
