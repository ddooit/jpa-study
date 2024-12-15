package jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, CustomEntityManagerSupport<Member, Long> {
    Optional<Member> findTopByName(String name);
}
