package kosa.ion.team6.Controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kosa.ion.team6.DTO.MemberDto;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Service.BoardService;
import kosa.ion.team6.Service.MemberService;

@Controller
@CrossOrigin(origins="*")

public class HomeController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/")
	public String home(@AuthenticationPrincipal Member member, Model model) {
		if(Objects.isNull(member)) 
			return "home";
		
		model.addAttribute("name", member.getName());
		model.addAttribute("category", boardService.allCategory());
		return "home";
	}
	
	@GetMapping("/member/new")
	public String joinForm(Model model) {
		model.addAttribute("member", new Member());
		return "member/join";
	}
	
	@PostMapping(value="/member/new")
	@ResponseBody
	public String join(Member member) {
		System.out.println(member.getName());
		if(memberService.checkEmail(member.getEmail())) {
			return "이메일 중복 다시 ㄱ";
		}
		
		if(memberService.join(member)== 0L)
			return "join failed <br> email Joongbok <a href='/'>go home</a>";
		else 
			return "join success <br> <a href='/'>go home</a>";
	}
	
    @GetMapping(value ="/member/login")
    public String login(){
        return "member/login";
    }
    
    @GetMapping(value ="/member/failed")
    public String loginfailed(){
        return "/member/failed";
    }
    
    
    @GetMapping(value ="/admin")
    public String adminForm() {
    	return "admin/admin";
    }
    
    @GetMapping(value = "/admin/memberlist")
    public String manageMember(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);

    	return "admin/memberlist";
    }
    
    @GetMapping(value = "/member/all")
    @ResponseBody
    public List<Member> manageMember1() {
        

    	return memberService.findAll();
    }
    
    @GetMapping(value = "/check")
    @ResponseBody
    public String checkAxios(@RequestParam("id") String id, @RequestParam("pw") String pw) {
    	System.out.println(id);
    	System.out.println(pw);
    	
    	if(id.equals("son") && pw.equals("1")) {
    		return "로그인 성공";
    	}else
    		return "로그인 실패";
    }
    
    @PostMapping(value = "/check")
    @ResponseBody
    public String checkAxios2(@RequestBody final MemberDto memberDto) {
    	System.out.println(memberDto.getId());
    	System.out.println(memberDto.getPw());
    	
    	if(memberDto.getId().equals("son") && memberDto.getPw().equals("1")) {
    		return "로그인 성공";
    	}else
    		return "로그인 실패";
    }
    
    
   
}
