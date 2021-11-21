package kosa.ion.team6.Controller;

import kosa.ion.team6.DTO.LoginDto;
import kosa.ion.team6.DTO.MemberDto;
import kosa.ion.team6.DTO.TokenDto;
import kosa.ion.team6.Domain.Board;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Jwt.JwtFilter;
import kosa.ion.team6.Jwt.TokenProvider;
import kosa.ion.team6.Service.BoardService;
import kosa.ion.team6.Service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final BoardService boardService;

    public MemberController(MemberService memberService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, BoardService boardService) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.boardService = boardService;
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    // 회원가입
    @PostMapping("/member/new")
    public ResponseEntity<Member> signup(@Valid @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    // 회원 정보 조회
    @GetMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')") // 두가지 권한 모두 허용
    public ResponseEntity<Member> getMyUserInfo() {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities().get());
    }

    // 회원 수정
    @PutMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<String> EditMemberInfo(@RequestBody MemberDto memberDto) {
        System.out.println(memberDto.getEmail());
        System.out.println(memberDto.getName());
        System.out.println(memberDto.getAddress());
        System.out.println(memberDto.getAddress_detail());


        return ResponseEntity.ok(
                memberService.editMember(memberService.getMyUserWithAuthorities().get().getId(), memberDto)
        );
    }

    // 회원 삭제
    @DeleteMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<String> DeleteMember() {
        return ResponseEntity.ok(memberService.deleteMember(memberService.getMyUserWithAuthorities().get().getId()));
    }

    // 이메일 중복 체크
    @GetMapping(value = "/member/{email}")
    public ResponseEntity<Boolean> isEmailDup(@PathVariable("email") String email) {
        return ResponseEntity.ok(memberService.isEmailDuplicate(email));
    }

    @GetMapping(value = "/member/address")
    public ResponseEntity<String> getMemberAddress() {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities().get().getAddress());
    }

    @PostMapping(value = "/member/email")
    public String findEmail(@RequestBody MemberDto memberDto) {
        System.out.println(memberDto.getName());
        System.out.println(memberDto.getResident1());
        System.out.println(memberDto.getResident2());
        String email = memberService.findEmail(memberDto);
        if (email.equals(" ")) {
            email = "notfound";
        }
        System.out.println(email);
        return email;
    }

    @PostMapping(value = "/member/password")
    public String findPassword(@RequestBody MemberDto memberDto) {
        String email = memberService.findEmail(memberDto);
        System.out.println(email);
        return email;
    }

//    @GetMapping(value="/member/wishlist")
//    public ResponseEntity<List<Board>> getAllWishList(HttpServletRequest request){
//        //return ResponseEntity.ok(memberServi
//    }

    //////////////////////찜/////////////////////////
    @GetMapping("/zzim")
    public String checkzzim() {
        return memberService.getMyUserWithAuthorities().get().getZzim();
    }

    @GetMapping("/zzim/{id}")
    public Boolean addZzim(@PathVariable long id) {
        System.out.println(id);
        Long memberid = memberService.getMyUserWithAuthorities().get().getId();

        return memberService.addZzim(memberid, id);
        // return false 면 찜 삭제
        // return true 면 찜 추가
    }

    @GetMapping("/member/zzim")
    public List<Board> getAllZZim() {
        String str = memberService.getMyUserWithAuthorities().get().getZzim();
        System.out.println(str);
        String[] arr = str.split("-");
        System.out.println(arr.length);
        List<Board> boardList = new ArrayList<Board>();
        for (int i = 1; i < arr.length; i++) {

            boardList.add(boardService.findById(Long.parseLong(arr[i].trim())).get());
        }

        return boardList;
    }

    //////////// 채팅 /////////////

    @GetMapping("/chatting/{id}")
    public void addChattingRoom(@PathVariable long id) {

        Long id1 = boardService.findById(id).get().getMember().getId();
        Long id2 = memberService.getMyUserWithAuthorities().get().getId();

        System.out.println("글쓴사람 id" + id1);
        System.out.println("로그인 id" + id2);

        memberService.addChatting(id, id1, id2);
    }

    @GetMapping("/member/chat")
    public List<Board> getAllChatting() {
        String str = memberService.getMyUserWithAuthorities().get().getChatting();
        System.out.println(str);
        String[] arr = str.split("-");
        System.out.println(arr.length);
        List<Board> boardList = new ArrayList<Board>();

        for (int i = 1; i < arr.length; i++) {
            boardList.add(boardService.findById(Long.parseLong(arr[i].trim())).get());
        }

        return boardList;
    }
}
