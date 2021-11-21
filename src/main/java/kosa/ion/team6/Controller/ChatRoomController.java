package kosa.ion.team6.Controller;


import kosa.ion.team6.Domain.ChatMessage;
import kosa.ion.team6.Repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/chat")
public class ChatRoomController {

    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/open/{chatid}")
    @ResponseBody
    public List<ChatMessage> createNewChatRoomOrGetChatRoom(@PathVariable String chatid){
        return chatMessageRepository.findByChatid(chatid);
    }
}