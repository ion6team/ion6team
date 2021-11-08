package kosa.ion.team6.Controller;

import kosa.ion.team6.DTO.LoginDto;
import kosa.ion.team6.DTO.MemberDto;
import kosa.ion.team6.DTO.TokenDto;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Jwt.JwtFilter;
import kosa.ion.team6.Jwt.TokenProvider;
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

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public MemberController(MemberService memberService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder){
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
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

    // 회원 정보 조회
    @GetMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')") // 두가지 권한 모두 허용
    public ResponseEntity<Member> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities().get());
    }

    // 회원 수정
    @PutMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<String> EditMemberInfo(HttpServletRequest request, @RequestBody MemberDto memberDto) {
        System.out.println(memberDto.getEmail());
        System.out.println(memberDto.getPassword());
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
    public ResponseEntity<String> DeleteMember(HttpServletRequest request){
        return ResponseEntity.ok(memberService.deleteMember(memberService.getMyUserWithAuthorities().get().getId()));
    }

    // 이메일 중복 체크
    @GetMapping(value="/member/{email}")
    public ResponseEntity<Boolean> isEmailDup(@PathVariable("email") String email){
        return ResponseEntity.ok(memberService.isEmailDuplicate(email));
    }
}
