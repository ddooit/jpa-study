package jpa.chat;

import jakarta.persistence.*;
import jpa.member.Member;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Chat {
    @Id
    @GeneratedValue
    @Column(name = "CHAT_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    private String message;

    private LocalDateTime sendTime;

    protected Chat() {
    }

    public Chat(
            final Member member,
            final ChatRoom chatRoom,
            final String message,
            final LocalDateTime sendTime
    ) {
        this.member = member;
        this.chatRoom = chatRoom;
        this.message = message;
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", member=" + member +
                ", message='" + message + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
