package jpa.chat;

import jpa.member.CustomEntityManagerSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ChatRepository extends JpaRepository<Chat, Long>, CustomEntityManagerSupport<Chat, Long> {

    @Query("select c from Chat c join fetch c.chatRoom")
    List<Chat> findAllWithFetchJoin();

    @Query("select c.chatRoom from Chat c")
    List<ChatRoom> findAllChatRoom();

}

@Repository
interface ParticipantRepository extends JpaRepository<ChatParticipant, Long> {
}

@Repository
interface RoomRepository extends JpaRepository<ChatRoom, Long> {
}
