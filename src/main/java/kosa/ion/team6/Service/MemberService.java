package kosa.ion.team6.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import kosa.ion.team6.DTO.MemberDto;
import kosa.ion.team6.Domain.Authority;
import kosa.ion.team6.Domain.Board;
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
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
                .zzim("0")
                .chatting("0")
                .resident1(memberDto.getResident1())
                .resident2(memberDto.getResident2())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return memberRepository.save(m);

    }

    @Transactional
    public String adiminMeInfo(Long id, MemberDto memberDto) {

        Optional<Member> updateUser = memberRepository.findById(id);

        updateUser.ifPresent(selectUser -> {
            selectUser.setName(memberDto.getName());
            selectUser.setEmail(memberDto.getEmail());
            selectUser.setPhone(memberDto.getPhone());
            selectUser.setAddress(memberDto.getAddress());
            selectUser.setAddress_detail(memberDto.getAddress_detail());
            selectUser.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            memberRepository.save(selectUser);
        });

        return "changed";

    }
    
    @Transactional
    public String editMember(Long id, MemberDto memberDto) {

        Optional<Member> updateUser = memberRepository.findById(id);

        updateUser.ifPresent(selectUser -> {
            selectUser.setName(memberDto.getName());
            selectUser.setEmail(memberDto.getEmail());
            selectUser.setPhone(memberDto.getPhone());
            selectUser.setAddress(memberDto.getAddress());
            selectUser.setAddress_detail(memberDto.getAddress_detail());
            memberRepository.save(selectUser);
        });

        return "changed";

    }

    @Transactional
    public String deleteMember(Long id) {
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
    public List<Member> getAllMemberInfo() {
        return (List<Member>) memberRepository.findAll();
    }

    //이메일 중복 체크
    public boolean isEmailDuplicate(String email) {
        return memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null) != null;
    }

    public String findEmail(MemberDto memberDto) {
        Optional<Member> m = memberRepository.findByNameAndResident1AndResident2(
                memberDto.getName(), memberDto.getResident1(), memberDto.getResident2());

        if (m.isPresent()) {
            return m.get().getEmail();
        } else {
            return " ";
        }

    }

    @Transactional
    public boolean addZzim(Long memberid, Long id) {
        Optional<Member> updateUser = memberRepository.findById(memberid);

        System.out.println("바뀔 컬럼" + updateUser.get().getZzim() + "-"+id);
        if (updateUser.get().getZzim().contains("-"+id)) {
            updateUser.ifPresent(selectUser -> {
                selectUser.setZzim(selectUser.getZzim().replaceAll("-"+id, ""));
                memberRepository.save(selectUser);
            });
            return false;
        } else {
            updateUser.ifPresent(selectUser -> {
                selectUser.setZzim(selectUser.getZzim() + "-"+id);
                memberRepository.save(selectUser);

            });
            return true;
        }
    }

    @Transactional
    public void addChatting(Long id,Long id1, Long id2) {
        Optional<Member> updateUser = memberRepository.findById(id1);

        if (updateUser.get().getChatting().contains("-"+id)) {
            System.out.println("이미있음");
        } else {
            updateUser.ifPresent(selectUser -> {
                selectUser.setChatting(selectUser.getChatting() + "-"+id);
                //memberRepository.save(selectUser);
            });
        }

        Optional<Member> updateUser2 = memberRepository.findById(id2);

        if (updateUser2.get().getChatting().contains("-"+id)) {
            System.out.println("이미있음");
        } else {
            updateUser2.ifPresent(selectUser -> {
                selectUser.setChatting(selectUser.getChatting() + "-"+id);
                memberRepository.save(selectUser);
            });
        }
    }
}
