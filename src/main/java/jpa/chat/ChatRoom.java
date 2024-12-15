package jpa.chat;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ChatRoom {
    @Id
    @GeneratedValue
    @Column(name = "CHAT_ROOM_ID")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatParticipant> participants = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<Chat> chats = new ArrayList<>();

    protected ChatRoom() {
    }

    public ChatRoom(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
