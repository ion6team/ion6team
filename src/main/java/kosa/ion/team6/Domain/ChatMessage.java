package kosa.ion.team6.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class ChatMessage {
    @Id
    private String roomId;

    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}