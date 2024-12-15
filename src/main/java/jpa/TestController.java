package jpa;

import jpa.member.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final MemberService memberService;

    public TestController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/create-member")
    public void createMember() {
        memberService.createMember();
    }

    @GetMapping("/create-member-with-flush")
    public void createMemberWithFlush() {
        memberService.createMemberWithFlush();
    }

    @GetMapping("/create-member-with-flush-and-clear")
    public void createMemberWithFlushAndClear() {
        memberService.createMemberWithFlushAndClear();
    }

    @GetMapping("/test-equality")
    public void testEquality() {
        memberService.testEquality();
    }

    @GetMapping("/test-equality-with-mapping")
    public void testEqualityWhenMapping() {
        memberService.testEqualityWhenMapping();
    }
}
