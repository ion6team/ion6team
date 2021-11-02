package kosa.ion.team6.Controller;

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
public class UserController {
    private final MemberService memberService;

    public UserController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(
            @Valid @RequestBody MemberDto memberDto
    ) {
        System.out.println("#################");
        System.out.println(memberDto.getEmail());
        System.out.println(memberDto.getPassword());
        System.out.println(memberDto.getName());
        System.out.println(memberDto.getAddress());
        System.out.println(memberDto.getAddress_detail());
        System.out.println("회원가입");
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')") // 두가지 권한 모두 허용
    public ResponseEntity<Member> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')") // admin만 허용
    public ResponseEntity<Member> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getUserWithAuthorities(email).get());
    }
}
