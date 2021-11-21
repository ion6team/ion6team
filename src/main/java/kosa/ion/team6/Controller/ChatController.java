package kosa.ion.team6.Controller;

import kosa.ion.team6.Domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/api/chat/message")
    public void SocketHandler(ChatMessage socketVO) {
        String roomId = socketVO.getRoomId();
        // vo에서 getter로 userName을 가져옵니다.
        String userName = socketVO.getSender();
        // vo에서 setter로 content를 가져옵니다.
        String content = socketVO.getMessage();

        // 생성자로 반환값을 생성합니다.
        ChatMessage result = new ChatMessage(roomId, userName, content);
        // 반환
        messagingTemplate.convertAndSend("http://localhost:8080/sub/chat/room", result);

    }
}