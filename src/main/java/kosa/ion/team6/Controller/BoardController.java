package kosa.ion.team6.Controller;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import kosa.ion.team6.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import kosa.ion.team6.DTO.BoardDto;
import kosa.ion.team6.Domain.Board;
import kosa.ion.team6.Domain.Category;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Service.BoardService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok(boardService.allCategory());
    }

	@GetMapping("/board")
	public Page boardList(@PageableDefault(size=3,sort="id",
			direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Board> boardList=boardService.selectBoardList(pageable);
		return boardList;
	}

    @PostMapping("/board")
    public Board save(@RequestBody Board board) {
        System.out.println("#######");
        board.setMember(memberService.getMyUserWithAuthorities().get());
        return boardService.addboard(board);
    }

    @GetMapping("/board/{id}")
    public Board boardDetail(@PathVariable("id") long id,  Member member) {
        Board boardDetail = boardService.selectBoardDetail(id);
        //model.addAttribute("board", boardService.selectBoardDetail(id));
        return boardDetail;
    }
}
