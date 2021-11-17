package kosa.ion.team6.Service;

import kosa.ion.team6.Domain.Board;
import kosa.ion.team6.Domain.Category;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Repository.BoardRepository;
import kosa.ion.team6.Repository.CategoryRepository;
import kosa.ion.team6.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    public AdminService(MemberRepository memberRepository, CategoryRepository categoryRepository,
    BoardRepository boardRepository) {
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.boardRepository = boardRepository;
    }

    public Page<Member> getAllMemberWithPaging(Pageable pageable, String kind, String keyword) {
        if (kind.equals("id")) {
            return memberRepository.findMemberById(keyword, pageable);
        } else if (kind.equals("email")) {
            return memberRepository.findByEmailContains(keyword, pageable);
        } else if (kind.equals("address")) {
            return memberRepository.findByAddressContains(keyword, pageable);
        } else if (kind.equals("name")) {
            return memberRepository.findByNameContains(keyword, pageable);
        } else {
            return memberRepository.findAll(pageable);
        }
    }

    public boolean delMember(String arr[]) {

        Boolean result = true;

        for (int i = 0; i < arr.length; i++) {
            Optional<Member> updateUser = memberRepository.findById(Long.parseLong(arr[i]));
            updateUser.ifPresent(selectUser -> {
                selectUser.setActivated(false);

                memberRepository.save(selectUser);
            });
        }
        return true;
    }

    public boolean rejoinMember(long id){
        Optional<Member> updateUser = memberRepository.findById(id);
        updateUser.ifPresent(selectUser -> {
            selectUser.setActivated(true);

            memberRepository.save(selectUser);
        });

        return true;
    }
    ////////////////////////// 보드 ////////////////////////////

    public Page<Board> getBoardWithPagingWithKeyword(Pageable pageable, String kind, String keyword){
        return boardRepository.findAll(pageable);
    }

    ////////////////////////// 카테고리 ////////////////////////
    public Page<Category> getAllCategory(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }
}
