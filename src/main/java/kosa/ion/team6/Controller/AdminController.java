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

// 관리자만 접속가능한 Rest Api
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 관리자 권한 전체 멤버 조회
    @GetMapping("/member/all")
    public ResponseEntity<List<Member>> getAllMemberInfo(){
        return ResponseEntity.ok(memberService.getAllMemberInfo());
    }
}
