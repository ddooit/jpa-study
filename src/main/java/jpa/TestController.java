package jpa;

import jpa.chat.ChatService;
import jpa.member.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final MemberService memberService;
    private final ChatService chatService;

    public TestController(
            final MemberService memberService,
            final ChatService chatService
    ) {
        this.memberService = memberService;
        this.chatService = chatService;
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

    @GetMapping("/create-chat")
    public void createChat() {
        chatService.createChat();
    }

    @GetMapping("/chat-room/{chatRoomId}")
    public void getChatRoom(@PathVariable long chatRoomId) {
        chatService.getRoomById(chatRoomId);
    }

    @GetMapping("/chat-room-using-reference/{chatRoomId}")
    public void getChatRoomUsingReference(@PathVariable long chatRoomId) {
        chatService.getRoomByIdUsingReference(chatRoomId);
    }

    @GetMapping("/problem-when-eager")
    public void problemWhenEager() {
        chatService.problemWhenEager();
    }


    @GetMapping("/problem-when-lazy")
    public void problemWhenLazy() {
        chatService.problemWhenLazy();
    }

    @GetMapping("/solve-problem-when-lazy")
    public void solveProblemWhenLazy() {
        chatService.solveProblemWhenLazy();
    }

    @GetMapping("/paging-and-sorting")
    public void pagingAndSorting() {
        chatService.pagingAndSorting();
    }

}
