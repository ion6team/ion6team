package kosa.ion.team6.Service;

import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Repository.BoardRepository;
import kosa.ion.team6.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private MemberRepository memberRepository;
    private BoardRepository boardRepository;

    public AdminService(MemberRepository memberRepository, BoardRepository boardRepository){
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public Page<Member> getAllMemberWithPaging(Pageable pageable){
        return memberRepository.findAll(pageable);
    }
}
