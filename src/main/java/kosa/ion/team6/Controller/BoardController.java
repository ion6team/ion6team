package kosa.ion.team6.Controller;

import kosa.ion.team6.DTO.ReplyDto;
import kosa.ion.team6.Domain.Reply;
import kosa.ion.team6.Repository.CategoryRepository;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public BoardController(BoardService boardService, MemberService memberService){
        this.boardService = boardService;
        this.memberService = memberService;
    }

    // 우리집 주소 게시물 조회
    @GetMapping("/board")
    public ResponseEntity<Page> boardList(
            @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value="category_id") long category_id,
            @RequestParam(required = false, value="keyword") String keyword
    ) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@" + keyword);
        String[] arr = memberService.getMyUserWithAuthorities().get().getAddress().split(" ");
        String hopeaddress = arr[0] + " " + arr[1];

        return ResponseEntity.ok(boardService.getMyAddressBoardList(category_id, hopeaddress, keyword, pageable));
    }

//    // 다른 주소 게시물 조회
//    @GetMapping("/board")
//    public Page boardList(@PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//        String[] arr = memberService.getMyUserWithAuthorities().get().getAddress().split(" ");
//        String hopeaddress = arr[0] + " " + arr[1];
//
//        return boardService.getMyAddressBoardList(hopeaddress, pageable);
//    }

    // 게시물 상세보기
    @GetMapping("/board/{id}")
    public Board boardDetail(@PathVariable("id") long id) {
        Board boardDetail = boardService.selectBoardDetail(id);

        return boardDetail;
    }

    // 게시물 쓰기
    @PostMapping("/board")
    public Board save(@RequestPart(value="data") BoardDto boardDto , @RequestPart(value="file", required=false) MultipartFile file )throws Exception {
        Board add = boardService.addboard(boardDto, memberService.getMyUserWithAuthorities().get(), file);
        return add;
    }

    // 게시물 삭제
    @DeleteMapping("/board/{id}")
    public void delete (@PathVariable long id) {
        boardService.delBoard(id);

    }

    // 게시물 수정
    @PutMapping("/board/{id}")
    public void update (@PathVariable long id ,@RequestBody Board board) {
        boardService.upBoard(id,board);
    }

    /////////////////////////// 댓글 ////////////////////////////////////

    // 게시물 상세페이지 댓글 조회
    @GetMapping("/reply/{id}")
    public Page ReplyList(@PathVariable long id, @PageableDefault(size=10,sort="id",
            direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("Controller" + id);
        return boardService.replyList(id,pageable);
    }

    // 댓글 작성
    @PostMapping("/reply/{id}")
    public Reply save(@PathVariable long id, @RequestBody ReplyDto replyDto) {

        Reply replyadd = boardService.replyadd(id, replyDto, memberService.getMyUserWithAuthorities().get());
        return replyadd;
    }

    // 댓글 삭제
    @DeleteMapping("/reply/{id}")
    public void delreply (@PathVariable long id) {
        boardService.delreply(id);
    }

    // 댓글 수정
    @PutMapping("/reply/{id}")
    public void uprelpydate (@PathVariable long id ,@RequestBody Reply reply) {
        boardService.upRelpy(id,reply);
    }

    ///////////////////////////////////검색///////////////////////////////

//    @GetMapping("/board/localsearch")
//    public Page localsearch(@RequestParam(value= "boardaddress")String boardaddress, @RequestParam(value= "category_id") long id ,
//                            @PageableDefault(size =3, sort ="id",direction = Sort.Direction.DESC) Pageable pageable) {
//
//        Page<Board> searchList = boardService.localsearch(boardaddress,id,pageable);
//
//
//        return searchList;
//
//    }

    ////////////////////////////////////카테고리/////////////////////////////

    @GetMapping("/category")
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }


    @GetMapping("/board/category")
    public Page oneCategory(@PageableDefault(size =16, sort ="id",direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.oneCategory(pageable);
    }
}
