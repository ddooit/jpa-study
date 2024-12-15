package jpa.chat;

import jakarta.persistence.*;
import jpa.member.Member;
import lombok.Getter;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class ChatParticipant {
    @Id
    @GeneratedValue
    @Column(name = "PARTICIPANT_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    protected ChatParticipant() {
    }

    public ChatParticipant(
            final Member member,
            final ChatRoom chatRoom
    ) {
        this.member = member;
        this.chatRoom = chatRoom;
    }

    @Override
    public String toString() {
        return "ChatParticipant{" +
                "id=" + id +
                ", member=" + member +
                '}';
    }
}
