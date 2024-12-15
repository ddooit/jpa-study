package jpa.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ChatRepository extends JpaRepository<Chat, Long> {
}

@Repository
interface ParticipantRepository extends JpaRepository<ChatParticipant, Long> {
}

@Repository
interface RoomRepository extends JpaRepository<ChatRoom, Long> {
}
