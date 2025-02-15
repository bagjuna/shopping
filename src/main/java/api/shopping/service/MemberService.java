package api.shopping.service;

import api.shopping.domain.Member;
import api.shopping.repository.MemberRepository;
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



}
