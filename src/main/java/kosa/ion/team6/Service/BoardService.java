package kosa.ion.team6.Service;

import java.util.Date;
import java.util.List;

import kosa.ion.team6.DTO.BoardDto;
import kosa.ion.team6.DTO.ReplyDto;
import kosa.ion.team6.Domain.Board;
import kosa.ion.team6.Domain.Category;
import kosa.ion.team6.Domain.Member;
import kosa.ion.team6.Domain.Reply;
import kosa.ion.team6.Repository.BoardRepository;
import kosa.ion.team6.Repository.CategoryRepository;
import kosa.ion.team6.Repository.MemberRepository;
import kosa.ion.team6.Repository.ReplyRepository;
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

	@Autowired
	private ReplyRepository replyRepository;

	@Transactional(readOnly = true)
	public Page<Board> getMyAddressBoardList(long category_id, String hopeaddress, String keyword, Pageable pageable){ //페이징
		if(keyword.equals("")) {
			return boardRepository.findByCategory_idAndHopeaddressContains(category_id, hopeaddress, pageable);
		}else{
			System.out.println("%%%%%" + category_id );
			return boardRepository.findByCategory_idAndHopeaddressContainsAndTitleContainsOrContentsContains(category_id, hopeaddress,  keyword, keyword,pageable);
		}
	}
	@Transactional
	public Board addboard(BoardDto boardDto, Member member) {

		Board board = Board.builder()
				.title(boardDto.getTitle())
				.category(categoryRepository.findById(boardDto.getCategory_id()))
				.contents(boardDto.getContents())
				.hopeaddress(boardDto.getHopeaddress())
				.price(boardDto.getPrice())
				.create_date(new Date())
				.member(member)
				.onsale(false)
				.hit(0)
				.build();

		return boardRepository.save(board);
	}


	@Transactional(readOnly = true)
	public Board selectBoardDetail(Long id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 글 아이디가 없다");
				});
	}

	@Transactional
	public void delBoard(long id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void upBoard(long id, Board board ) {
		Board updata = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패: 아이디가 없다");
		});//영속화 완료
		updata.setTitle(board.getTitle());
		updata.setContents(board.getContents());
		updata.setOnsale(board.isOnsale());
		updata.setHopeaddress(board.getHopeaddress());
		updata.setPrice(board.getPrice());
		updata.setUpdate_date(new Date());
	}

	@Transactional(readOnly = true)
	public Page<Reply> replyList(Long id, Pageable pageable) {

		boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("보드아이디가 없다");
		});
		System.out.println("Service" + id);
		return replyRepository.findByBoard_id(id,pageable);

	}
	@Transactional
	public Reply replyadd(Long id, ReplyDto replyDto, Member memberInfo) {
		boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("보드아이디가 없다");
		});

		Reply reply = Reply.builder()
				.content(replyDto.getContents())
				.board(boardRepository.findById(id).get())
				.member(memberInfo)
				.build();

		return replyRepository.save(reply);
	}

	@Transactional
	public void delreply(Long id) {
		replyRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("리플아이디가 없다");
		});
		replyRepository.deleteById(id);
	}

	@Transactional
	public void upRelpy(Long id, Reply reply) {
		Reply updata = replyRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("작성자 아이디가 아닙니다");

		});//영속화 완료


		updata.setContent(reply.getContent());
	}

	@Transactional(readOnly = true)
	public Page oneCategory( Pageable pageable){

		return categoryRepository.findAll(pageable);
	}


	//////////////////////////////////////////////검색////////////////////////////////////////

	// 검색
//	@Transactional(readOnly = true)
//	public Page<Board> localsearch(String hopeaddress, long id, Pageable pageable) {
//
//		if(id==0&&hopeaddress.equals("전체 전체")) {
//			Page<Board> searchobject = boardRepository.findAll(pageable);
//			return searchobject;
//		}
//		else if(id==0&&hopeaddress.equals("")) {
//			Page<Board> searchobject = boardRepository.findAll(pageable);
//			return searchobject;
//		}
//		else if(hopeaddress.equals("전체 전체")&&id!=0) {
//			Page<Board> searchobject = boardRepository.findByCategory_id(id,pageable);
//			return searchobject;
//
//		}
//		else if(hopeaddress.equals(hopeaddress)&&id==0) {
//			Page<Board> searchobject = boardRepository.findByHopeaddress(hopeaddress,pageable);
//			return searchobject;
//		}
//		else {
//			Page<Board> searchobject = boardRepository.findByBoardaddressAndCategory_id(hopeaddress,id,pageable);
//			return searchobject;
//		}
//	}

}
