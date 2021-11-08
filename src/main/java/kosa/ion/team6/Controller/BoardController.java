package kosa.ion.team6.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllCategory(){
		return ResponseEntity.ok(boardService.allCategory());
	}


//	@Autowired
//	private BoardService boardService;
//
//	@GetMapping("/board/boardlist")
//	public String boardList(Model model) {
//		List<Board> boards = boardService.selectBoardList();
//		model.addAttribute("boards", boards);
//		return "board/boardlist";
//	}
//
//	@GetMapping("/board/new")
//	public String boardNew(@AuthenticationPrincipal Member member, Model model) {
//		System.out.println("@@ 글쓰는 사람 ID, 아디 @@@" + member.getId() + ", "+ member.getName());
//		List<Category> c = new ArrayList<Category>();
//		model.addAttribute("member", member);
//		model.addAttribute("category", boardService.allCategory());
//
//		model.addAttribute("board", new BoardDto());
//		return "board/addboard";
//	}
//
//
//	@PostMapping("/board/new")
//	public String addBoard(BoardDto board) {
//		System.out.println("@@@@@");
//		System.out.println(board.getTitle());
//		System.out.println(board.getCategory_id());
//		System.out.println(board.getMember_id());
//		System.out.println(board.getContents());
//		boardService.addboard(board);
//		return "redirect:/";
//	}
//
//	@GetMapping("/board/boardDetail/{id}")
//	public String boardDetail(@PathVariable("id") long id, @AuthenticationPrincipal Member member, Model model) {
//		boolean b;
//		Board board = boardService.selectBoardDetail(id);
//		if(board.getMember().getId().equals(member.getId())) {
//			model.addAttribute("check",true);
//		}else {
//			model.addAttribute("check", false);
//		}
//
//		model.addAttribute("board", board);
//		//model.addAttribute("board", boardService.selectBoardDetail(id));
//		return "board/detail";
//	}
//
//	//@DeleteMapping
//	@GetMapping("/board/boardDetail/delete/{id}")
//	@ResponseBody
//	public String delBoard(@PathVariable("id") long id) {
//		boardService.delBoard(id);
//		return "삭제됨 <a href='/board/boardlist'>리스트로</a>";
//	}
//
//	@GetMapping("/board/category")
//	public String categoryBoard(@RequestParam("category_id") Long id, Model model) {
//		System.out.println("@@@@@@@@@@@@@@@@"+id);
//		model.addAttribute("board", boardService.oneCategory(id));
//		return "board/category";
//	}

}
