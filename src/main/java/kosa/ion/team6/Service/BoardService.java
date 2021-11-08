package kosa.ion.team6.Service;

import java.util.Date;
import java.util.List;
import kosa.ion.team6.Domain.Board;
import kosa.ion.team6.Domain.Category;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Repository.BoardRepository;
import kosa.ion.team6.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	// 모든 카테고리 정보 가져오기
	public List<Category> allCategory(){
		return categoryRepository.findAll();
	}

	@Transactional
	public Long writeNewBoard(Board board, Member member) { //타이틀 컨텐트만 받음 글쓰기할때 유저정보도 필요함
		board.setMember(member);

		return boardRepository.save(board).getId();
	}

	@Transactional(readOnly = true)
	public Board selectOneBoard(Long id) {

		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디가 없다");
				});
	}

	@Transactional(readOnly = true)
	public Page<Board> selectBoardList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}

	@Transactional
	public Board addboard(Board board) {

		board.setTitle(board.getTitle());
		board.setContents(board.getContents());
		board.setCreate_date(new Date());
		board.setHope_address(board.getHope_address());
		board.setPrice(board.getPrice());
		board.setOnslae(false);
		return boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Board selectBoardDetail(Long id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 글 아이디가 없다");
				});
	}
}
