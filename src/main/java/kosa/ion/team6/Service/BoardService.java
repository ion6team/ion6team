package kosa.ion.team6.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kosa.ion.team6.DTO.BoardDto;
import kosa.ion.team6.Domain.Board;
import kosa.ion.team6.Domain.Category;
import kosa.ion.team6.Repository.BoardRepository;
import kosa.ion.team6.Repository.CategoryRepository;
import kosa.ion.team6.Repository.MemberRepository;

@Service
public class BoardService {
	private BoardRepository boardRepository;
	private MemberRepository memberRepository;
	private CategoryRepository categoryRepository;
	private static long board_id = 0L;

	@Autowired
	public BoardService(BoardRepository boardRepository, MemberRepository memberRepository, CategoryRepository categoryRepository) {
		this.boardRepository = boardRepository;
		this.memberRepository = memberRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public List<Board> selectBoardList(){
		return boardRepository.findAll();
	}
	
	public Long addboard(BoardDto board) {
		Board b = new Board();
		b.setTitle(board.getTitle());
		b.setContents(board.getContents());
		b.setMember(memberRepository.findById(board.getMember_id()));
		b.setCategory(categoryRepository.findById(Long.parseLong(board.getCategory_id())));
		b.setCreate_date(new Date());
		b.setHope_address(board.getHope_address());
		b.setPrice(board.getPrice());
		b.setHit(0);
		b.setOnslae(false);
		boardRepository.save(b);		
		return b.getId();
	}
	
	public List<Category> allCategory(){
		return categoryRepository.findAll();
	}
	
	@Transactional
	public Board selectBoardDetail(Long id) {
		System.out.println("@@@@@ 조회수 @@@@"+ boardRepository.findById(id).orElse(null).getHit());
		boardRepository.findById(id).orElse(null).setHit(boardRepository.findById(id).orElse(null).getHit()+1);
		return boardRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public void delBoard(long id) {
		boardRepository.deleteById(id);
	}
	
	public List<Board> oneCategory(long id){
	
		return boardRepository.findByCategory_Id(id);
	}
		
}
