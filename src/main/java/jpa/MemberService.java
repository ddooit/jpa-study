package jpa;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


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
        printOne(memberId1);
        printOne(memberId2);
        logger.info("========== INSERT QUERY ==========");
    }

    public void createMemberWithFlush() {
        final var memberId1 = createOne(new Member("MEMBER_1"));
        final var memberId2 = createOne(new Member("MEMBER_2"));
        logger.info("========== INSERT QUERY ==========");
        memberRepository.flush();
        printOne(memberId1);
        printOne(memberId2);
    }

    public void createMemberWithFlushAndClear() {
        final var memberId1 = createOne(new Member("MEMBER_1"));
        final var memberId2 = createOne(new Member("MEMBER_2"));
        logger.info("========== INSERT QUERY ==========");
        memberRepository.flushAndClear();
        logger.info("========== SELECT QUERY ==========");
        printOne(memberId1);
        printOne(memberId2);
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
}
