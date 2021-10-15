package kosa.ion.team6.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService{

	private MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Long join(Member m) {
		//if(memberRepository.existsByEamil(m.getEmail())) {
		//	return 0L;
		//}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		m.setPassword(passwordEncoder.encode(m.getPassword()));
		
		return memberRepository.save(m).getId();
	}
	
	public List<Member> findAll(){
		return memberRepository.findAll();
	}
	
	public boolean checkEmail(String email) {
		if(memberRepository.existsByEmail(email))
			return true;
		else
			return false;
	}
	
	public Member selectOne(long id) {
		
		return memberRepository.findById(id);
	}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
/*        Optional<Member> memberWrapper = memberRepository.findByEmail(email);
        Member member = memberWrapper.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(email)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return new User(member.getName(),member.getPw(),authorities);*/
        return (UserDetails) memberRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
