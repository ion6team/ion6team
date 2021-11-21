package kosa.ion.team6.Controller;

import kosa.ion.team6.Domain.ChatMessage;
import kosa.ion.team6.Repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;


@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    @MessageMapping("/api/chat/message")
    public void SocketHandler(ChatMessage socketVO) {


        String roomid = socketVO.getChatid();
        // vo에서 getter로 userName을 가져옵니다.
        String userName = socketVO.getSender();
        // vo에서 setter로 content를 가져옵니다.
        String content = socketVO.getMessage();
        // 생성자로 반환값을 생성합니다.
        //ChatMessage result = new ChatMessage( roomid, userName, content, new Date());

        ChatMessage cm = ChatMessage.builder()
                .chatid(roomid)
                .sender(userName)
                .message(content)
                .createdate(new Date())
                .build();
        chatMessageRepository.save(cm);
        // 반환
        messagingTemplate.convertAndSend("http://localhost:8080/sub/chat/room", cm);

    }
}