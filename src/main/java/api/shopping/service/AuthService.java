package api.shopping.service;

import api.shopping.domain.Member;
import api.shopping.execption.AlreadyExistsEmailException;
import api.shopping.repository.MemberRepository;
import api.shopping.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(Signup signup) {
        Optional<Member> userOptional = memberRepository.findByEmail(signup.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        String encryptedPassword = passwordEncoder.encode(signup.getPassword());

        var user = Member.builder()
                .email(signup.getEmail())
                .password(encryptedPassword)
                .name(signup.getName())
                .build();
        memberRepository.save(user);
    }
}
