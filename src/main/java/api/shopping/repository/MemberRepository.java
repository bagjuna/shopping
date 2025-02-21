package api.shopping.repository;

import api.shopping.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    void findByEmailAndPassword(String email, String password);


    Optional<Member> findByEmail(String email);
}


