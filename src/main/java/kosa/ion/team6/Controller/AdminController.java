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
    @GetMapping("/member/{kind}")
    public ResponseEntity<Page<Member>> getAllMemberWithPaging(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String keyword,
            @PathVariable String kind) {
        PageRequest pageRequest = PageRequest.of(page, size);
        System.out.println(kind);
        System.out.println(keyword);
        return ResponseEntity.ok(adminService.getAllMemberWithPaging(pageRequest, kind, keyword));
    }

    @PostMapping("/member")
    public ResponseEntity<Boolean> delSelectedMember(@RequestBody String selectlist) {


        String[] arr = selectlist.substring(15,selectlist.length()-2).split(",");

        for(int i=0; i<arr.length ;i++){
            System.out.println(arr[i]);
        }

        return ResponseEntity.ok(adminService.delMember(arr));

    }

}
