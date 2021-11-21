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

    private final ChatMessageRepository chatRoomRepository;

    @GetMapping("/open")
    @ResponseBody
    public Optional<ChatMessage> createNewChatRoomOrGetChatRoom(
            @RequestParam(value = "id1") int id1,
            @RequestParam(value = "id2") int id2
    ){
        String roomid="";
        if(id1 > id2){
            roomid = Integer.toString(id2) + "to" + Integer.toString(id1);
        }else{
            roomid = Integer.toString(id1) + "to" + Integer.toString(id2);
        }

        if(chatRoomRepository.findById(roomid).orElse(null) != null)
            return chatRoomRepository.findById(roomid);

        return null;
    }
}