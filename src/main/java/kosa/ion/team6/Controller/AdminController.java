package kosa.ion.team6.Controller;

import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Repository.MemberRepository;
import kosa.ion.team6.Service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @GetMapping("/member/all")
//    @PreAuthorize("hasAnyRole('ADMIN')") // admin만 허용
//    public ResponseEntity<Member> getUserInfo(@PathVariable String email) {
//        return ResponseEntity.ok(memberService.getUserWithAuthorities(email).get());
//    }

    @GetMapping("/member/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Member>> getAllMemberInfo(HttpServletRequest request){
        return ResponseEntity.ok(memberService.getAllMemberInfo());
    }
}
