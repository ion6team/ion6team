package kosa.ion.team6.Repository;

import kosa.ion.team6.Domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query(value="select * from chat_message where chatid=?1 order by createdate", nativeQuery = true)
    List<ChatMessage> findByChatid(String chatid);
}