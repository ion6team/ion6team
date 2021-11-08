package kosa.ion.team6.Controller.나중에삭제;

import kosa.ion.team6.DTO.MemberDto;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {
//    private final MemberService memberService;
//
//    public UserController(MemberService memberService) {
//        this.memberService = memberService;
//    }

//    // 회원가입
//    @PostMapping("/member/new")
//    public ResponseEntity<Member> signup(
//            @Valid @RequestBody MemberDto memberDto
//    ) {
//        System.out.println("#################");
//        System.out.println(memberDto.getEmail());
//        System.out.println(memberDto.getPassword());
//        System.out.println(memberDto.getName());
//        System.out.println(memberDto.getAddress());
//        System.out.println(memberDto.getAddress_detail());
//        System.out.println("회원가입");
//        return ResponseEntity.ok(memberService.signup(memberDto));
//    }
//
//    // 회원 정보 조회
//    @GetMapping("/member")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')") // 두가지 권한 모두 허용
//    public ResponseEntity<Member> getMyUserInfo(HttpServletRequest request) {
//        return ResponseEntity.ok(memberService.getMyUserWithAuthorities().get());
//    }
//
//    // 회원 수정
//    @PutMapping("/member")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<String> EditMemberInfo(HttpServletRequest request, @RequestBody MemberDto memberDto) {
//        System.out.println(memberDto.getEmail());
//        System.out.println(memberDto.getPassword());
//        System.out.println(memberDto.getName());
//        System.out.println(memberDto.getAddress());
//        System.out.println(memberDto.getAddress_detail());
//
//        return ResponseEntity.ok(
//                memberService.editMember(memberService.getMyUserWithAuthorities().get().getId(), memberDto)
//        );
//    }
//
//    // 회원 삭제
//    @DeleteMapping("/member")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<String> DeleteMember(HttpServletRequest request){
//        return ResponseEntity.ok(memberService.deleteMember(memberService.getMyUserWithAuthorities().get().getId()));
//    }
//
//    // 이메일 중복 체크
//    @GetMapping(value="/member/{email}")
//    public ResponseEntity<Boolean> isEmailDup(@PathVariable("email") String email){
//        return ResponseEntity.ok(memberService.isEmailDuplicate(email));
//    }
}
