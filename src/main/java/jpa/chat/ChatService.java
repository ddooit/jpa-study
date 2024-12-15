package jpa.chat;

import jakarta.transaction.Transactional;
import jpa.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final MemberService memberService;
    private final ChatRepository chatRepository;
    private final ParticipantRepository participantRepository;
    private final RoomRepository roomRepository;

    public ChatService(
            final ChatRepository chatRepository,
            final MemberService memberService,
            final ParticipantRepository participantRepository,
            final RoomRepository roomRepository
    ) {
        this.chatRepository = chatRepository;
        this.memberService = memberService;
        this.participantRepository = participantRepository;
        this.roomRepository = roomRepository;
    }

    public void createChat() {
        final var chatRoom = new ChatRoom("chat room title");
        final var speaker = memberService.findByName("MEMBER_1");

        final var chat = new Chat(speaker, chatRoom, "하이~", LocalDateTime.now());

        final var listener1 = memberService.findByName("MEMBER_2");
        final var listener2 = memberService.findByName("MEMBER_3");


        final var participants = List.of(speaker, listener1, listener2).stream()
                .map(member -> new ChatParticipant(member, chatRoom)).toList();

        chatRepository.save(chat);
        roomRepository.save(chatRoom);
        participantRepository.saveAll(participants);
    }

    public void getRoomById(final Long roomId) {
        final var room = roomRepository.findById(roomId).orElseThrow();
        logger.info("<< getRoomById >>");

        logger.info("<< ChatRoom >> : {}", room);
        logger.info("<< Chats >> : {}", room.getChats().size());
        logger.info("<< ChatParticipants >> : {}", room.getParticipants().size());
    }

}
