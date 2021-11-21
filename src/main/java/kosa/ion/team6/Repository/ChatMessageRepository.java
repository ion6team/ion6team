package kosa.ion.team6.Repository;

import kosa.ion.team6.Domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

}