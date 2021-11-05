package kosa.ion.team6.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import kosa.ion.team6.DTO.MemberDto;
import kosa.ion.team6.Domain.Authority;
import kosa.ion.team6.Jwt.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import kosa.ion.team6.Exception.DuplicateMemberException;

//
@Service
public class MemberService implements UserDetailsService{

	private MemberRepository memberRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String email) {
		return memberRepository.findOneWithAuthoritiesByEmail(email)
				.map(user -> createUser(email, user))
				.orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
	}

	private org.springframework.security.core.userdetails.User createUser(String username, Member member) {
		if (!member.isActivated()) {
			throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
		}
		List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(member.getEmail(),
				member.getPassword(),
				grantedAuthorities);
	}

	@Transactional
	public Member signup(MemberDto memberDto) {
		// 중복 체크
		if (memberRepository.findOneWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null) != null) {
			throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
		}

		//권한 정보 넣음
		Authority authority = Authority.builder()
				.authorityName("ROLE_USER")
				.build();

		Member m = Member.builder()
				.email(memberDto.getEmail())
				.password(passwordEncoder.encode(memberDto.getPassword()))
				.name(memberDto.getName())
				.address(memberDto.getAddress())
				.address_detail(memberDto.getAddress_detail())
				.phone(memberDto.getPhone())
				.resident1(memberDto.getResident1())
				.resident2(memberDto.getResident2())
				.authorities(Collections.singleton(authority))
				.activated(true)
				.build();

		return memberRepository.save(m);

	}

	@Transactional
	public String editMember(Long id, MemberDto memberDto){
		Member editMember = memberRepository.findById(id).get();

		editMember.setEmail(memberDto.getEmail());
		editMember.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		editMember.setName(memberDto.getName());
		editMember.setAddress(memberDto.getAddress());
		editMember.setAddress_detail(memberDto.getAddress_detail());

		return editMember.getEmail() + " 수정됨.";
	}

	@Transactional
	public String deleteMember(Long id){
		Member deleteMember = memberRepository.findById(id).get();

		deleteMember.setActivated(false);

		return deleteMember.getEmail() + " 비활성화";
	}

	// 이메일을 받아서 정보를 가져옴
	@Transactional(readOnly = true)
	public Optional<Member> getUserWithAuthorities(String email) {
		return memberRepository.findOneWithAuthoritiesByEmail(email);
	}

	// 현재 SecurityContext에 저장된 email의 정보를 가져옴
	@Transactional(readOnly = true)
	public Optional<Member> getMyUserWithAuthorities() {
		return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail);
	}

	// 전체 정보를 가져옴
	@Transactional(readOnly = true)
	public List<Member> getAllMemberInfo(){
		return (List<Member>) memberRepository.findAll();
	}

	//이메일 중복 체크
	public boolean isEmailDuplicate(String email){
		return memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null) != null;
	}



//	public Long join(Member m) {
//		//if(memberRepository.existsByEamil(m.getEmail())) {
//		//	return 0L;
//		//}
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		m.setPassword(passwordEncoder.encode(m.getPassword()));
//
//		return memberRepository.save(m).getId();
//	}
//
//	public List<Member> findAll(){
//		return memberRepository.findAll();
//	}
//
//	public boolean checkEmail(String email) {
//		if(memberRepository.existsByEmail(email))
//			return true;
//		else
//			return false;
//	}
//
//	public Member selectOne(long id) {
//
//		return memberRepository.findById(id);
//	}

/*    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
*//*        Optional<Member> memberWrapper = memberRepository.findByEmail(email);
        Member member = memberWrapper.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(email)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return new User(member.getName(),member.getPw(),authorities);*//*
        return (UserDetails) memberRepository.findByEmail(email);
                //orElseThrow(() -> new UsernameNotFoundException(email));
    }*/

//	public Member validationLogin(MemberDto memberDto){
//		Member lmember = memberRepository.findByEmail(memberDto.getUsername());
//
//		if(lmember==null){
//			return null;
//		}
//
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		if(!passwordEncoder.matches(memberDto.getPassword(), lmember.getPassword())){
//			return null;
//		}
//
//		return lmember;
//	}
}
