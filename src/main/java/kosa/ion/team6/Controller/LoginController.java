package kosa.ion.team6.Controller;

import kosa.ion.team6.DTO.MemberDto;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Repository.MemberRepository;
import kosa.ion.team6.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class LoginController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    final private static Logger LOG = Logger.getGlobal();
    public static final String SECURED_TEXT = "Hello from the secured resource!";

    @GetMapping("/login")
    public void login() {
        LOG.info("GET successfully called on /login resource");
    }

//    @GetMapping("/hello")
//    public void hello() {
//        System.out.println("HelloWorld");
//    }

    @PostMapping(value="/join")
    @ResponseBody
    public void join(@RequestBody Member member) {
        LOG.info(" ##### Join Infomation #####");
        LOG.info(member.getEmail());
        LOG.info(member.getName());
        LOG.info(member.getPassword());
        LOG.info(member.getAddress());
        LOG.info(member.getAddress_detail());
        memberService.join(member);
        LOG.info(" ##### Login Success #####");
    }

    @GetMapping(value="/join/{email}")
    @ResponseBody
    public boolean checkDupEmail(@PathVariable("email") String email){
        LOG.info("이메일 중복체크 : " + email);
       return memberService.checkEmail(email);
    }

//
//    @PostMapping(value="/login")
//    @ResponseBody
//    public Member login(@RequestBody MemberDto memberDto) {
//        Member loginMember = memberRepository.findByEmail(memberDto.getEmail());
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        LOG.info( "ID >> " + memberDto.getUsername());
//        LOG.info( "PW >> " + passwordEncoder.encode(memberDto.getPassword()));
//
//        return memberService.validationLogin(memberDto);
//    }
}
