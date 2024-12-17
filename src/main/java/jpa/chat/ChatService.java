package jpa.chat;

import jakarta.transaction.Transactional;
import jpa.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        logger.info("Class of ChatRoom : {}", room.getClass());
        logger.info("is ChatRoom Class : {}", room.getClass() == ChatRoom.class);

        logger.info("<< Chats >> : {}", room.getChats().size());
        logger.info("<< ChatParticipants >> : {}", room.getParticipants().size());
    }

    public void getRoomByIdUsingReference(final Long roomId) {
        final var room = roomRepository.getReferenceById(roomId);
        logger.info("<< getRoomByIdUsingReference >>");

        logger.info("<< ChatRoom >> : {}", room.getClass());
        logger.info("is ChatRoom Class : {}", room.getClass() == ChatRoom.class);
        logger.info("is ChatRoom instance : {}", room instanceof ChatRoom);
    }

    public void problemWhenEager() {
        createChatSet(10);
        logger.info("<< N+1 when EAGER >>");

        final var allChats = chatRepository.findAll(); // 1 + N called

        for (final Chat chat : allChats) {
            logger.info("<< Chat message >> : {}", chat.getMessage());
        }
    }

    public void problemWhenLazy() {
        createChatSet(10);
        logger.info("<< N+1 when Lazy >>");

        final var allChats = chatRepository.findAll(); // 1 called

        for (final Chat chat : allChats) {
            // N called
            logger.info("<< ChatRoom Title >> : {}", chat.getChatRoom().getTitle());
        }
    }

    public void solveProblemWhenLazy() {
        createChatSet(10);

        final var allChats = chatRepository.findAllWithFetchJoin(); // 1 called

        for (final Chat chat : allChats) {
            // Not called
            logger.info("<< ChatRoom Title >> : {}", chat.getChatRoom().getTitle());
        }
    }

    public void pagingAndSorting() {
        createChatSet(100);
        final var pageRequest = PageRequest.of(3, 10);
        final var chats = chatRepository.findAll(pageRequest);

        for (final Chat chat : chats) {
            logger.info("<< Chat id >> : {}", chat.getId());
            logger.info("<< Chat message >> : {}", chat.getMessage());
        }

        final var pageRequestWithSort = PageRequest.of(3, 10,
                Sort.by("id").reverse()
                        .and(Sort.by("message")));
        final var chatsWithSort = chatRepository.findAll(pageRequestWithSort);

        for (final Chat chat : chatsWithSort) {
            logger.info("<< Chat id >> : {}", chat.getId());
            logger.info("<< Chat message >> : {}", chat.getMessage());
        }
    }

    public void implicitJoin() {
        createChatSet(10);

        logger.info("<< Implicit Join >>");

        final var allChatRooms = chatRepository.findAllChatRoom();
        for (final ChatRoom chatRoom : allChatRooms) {
            logger.info("<< ChatRoom Title>> : {}", chatRoom.getTitle());
        }
    }

    private void createChatSet(final int count) {
        for (int i = 0; i < count; i++) {
            this.createChat();
        }
        chatRepository.flushAndClear();
    }
}
