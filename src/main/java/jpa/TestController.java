package jpa;

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

}
