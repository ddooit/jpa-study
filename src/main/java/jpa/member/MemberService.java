package jpa.member;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Transactional
public class MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember() {
        final var memberId1 = createOne(new Member("MEMBER_1"));
        final var memberId2 = createOne(new Member("MEMBER_2"));
        final var memberId3 = createOne(new Member("MEMBER_3"));
        printOne(memberId1);
        printOne(memberId2);
        printOne(memberId3);
        logger.info("========== INSERT QUERY ==========");
    }

    public void createMemberWithFlush() {
        final var memberId1 = createOne(new Member("MEMBER_4"));
        final var memberId2 = createOne(new Member("MEMBER_5"));
        logger.info("========== INSERT QUERY ==========");
        memberRepository.flush();
        printOne(memberId1);
        printOne(memberId2);
    }

    public void createMemberWithFlushAndClear() {
        final var memberId1 = createOne(new Member("MEMBER_6"));
        final var memberId2 = createOne(new Member("MEMBER_7"));
        logger.info("========== INSERT QUERY ==========");
        memberRepository.flushAndClear();
        logger.info("========== SELECT QUERY ==========");
        printOne(memberId1);
        printOne(memberId2);
    }

    public void testEquality() {
        final var memberId1 = createOne(new Member("MEMBER_1"));

        final var optionalMember1 = memberRepository.findById(memberId1);
        final var optionalMember2 = memberRepository.findById(memberId1);

        logger.info("Equality : {}", optionalMember1.get().equals(optionalMember2.get()));
        logger.info("Identity : {}", optionalMember1.get() == optionalMember2.get());
    }

    public void testEqualityWhenMapping() {
        final var memberId1 = createOne(new Member("MEMBER_1"));

        final var optionalMember1 = memberRepository.findById(memberId1).map(it -> new MemberResponse(it.getId(), it.getName()));
        final var optionalMember2 = memberRepository.findById(memberId1).map(it -> new MemberResponse(it.getId(), it.getName()));

        logger.info("Equality : {}", optionalMember1.get().equals(optionalMember2.get()));
        logger.info("Identity : {}", optionalMember1.get() == optionalMember2.get());
    }

    public Member findByName(final String name) {
        return memberRepository.findTopByName(name).orElseThrow();
    }

    private Long createOne(final Member member) {
        final var savedMember = memberRepository.save(member);

        logger.info("<< createOne >> : {}", savedMember.getId());

        return savedMember.getId();
    }

    private void printOne(final Long id) {
        final var findMember = memberRepository.findById(id).orElseThrow(RuntimeException::new);

        logger.info("<< printOne >> : {}", findMember);
    }

    private record MemberResponse(
            Long id,
            String name
    ) {
        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final MemberResponse that = (MemberResponse) o;
            return Objects.equals(id, that.id) && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
