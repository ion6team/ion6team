package kosa.ion.team6.Domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Table
public class ChatMessage {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomid;

    private String chatid;
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private Date createdate;
}