package kosa.ion.team6.Controller;

import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class LoginController {

    @Autowired
    MemberService memberService;

    final private static Logger LOG = Logger.getGlobal();
    public static final String SECURED_TEXT = "Hello from the secured resource!";

    @GetMapping("/login")
    public void login() {
        LOG.info("GET successfully called on /login resource");
    }

    @GetMapping("/hello")
    public void hello() {
        System.out.println("HelloWorld");
    }

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
}
