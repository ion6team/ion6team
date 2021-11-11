package kosa.ion.team6.Controller;

import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Repository.MemberRepository;
import kosa.ion.team6.Service.AdminService;
import kosa.ion.team6.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

// 관리자만 접속가능한 Rest Api
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 관리자 권한 전체 멤버 조회
    @GetMapping("/member")
    public ResponseEntity<Page<Member>> getAllMemberWithPaging(
            @RequestParam int page, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(adminService.getAllMemberWithPaging(pageRequest));
    }
}
