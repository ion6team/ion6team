package kosa.ion.team6.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReplyRepository replyRepository;

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Board> getMyAddressBoardList(long category_id, String hopeaddress, String keyword, Pageable pageable) { //페이징

        if (category_id == 0 && keyword.equals("")) {
            return boardRepository.findByHopeaddressContains(hopeaddress, pageable);
        } else if (keyword.equals("")) {
            return boardRepository.findByCategory_idAndHopeaddressContains(category_id, hopeaddress, pageable);
        } else {
            System.out.println("%%%%%" + category_id);
            return boardRepository.findByCategory_idAndHopeaddressContainsAndTitleContainsOrContentsContains(category_id, hopeaddress, keyword, keyword, pageable);
        }
    }

    @Transactional
    public Board addboard(BoardDto boardDto, Member member, List<MultipartFile> file) throws Exception {

        if (file != null) {
            String arr[] = new String[3];
            String projectPath = System.getProperty("user.dir") + "\\front\\public\\upload"; //저장 경로 잡기

            UUID uuid = UUID.randomUUID(); //랜덤으로 아이디 만들어줌


            int i = 0;
            for (MultipartFile m : file) {
                String fileName = uuid + "_" + m.getOriginalFilename(); // uuid 붙여서 파일이름

                File saveFile = new File(projectPath, fileName); //경로랑 파일이름 만들어서 객체저장

                m.transferTo(saveFile);
                arr[i++] = fileName;
            }

            if (arr[1] == null) {
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
                        .filepath1(arr[0])

                        .build();
                return boardRepository.save(board);

            } else if (arr[2] == null) {
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
                        .filepath1(arr[0])
                        .filepath2(arr[1])

                        .build();
                return boardRepository.save(board);

            }
//		board.setFilepath1(arr[0]);
//		board.setFilepath2(arr[1]);
//		board.setFilepath3(arr[2]);
            else {
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
                        .filepath1(arr[0])
                        .filepath2(arr[1])
                        .filepath3(arr[2])
                        .build();
                return boardRepository.save(board);
            }
        } else {
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
                    .filepath1("이미지없음.PNG")

                    .build();
            return boardRepository.save(board);
        }
//
//		.filename1(fileName)
//		.filepath(fileName)


    }


    @Transactional
    public Board selectBoardDetail(Long id) {

        Optional<Board> board = boardRepository.findById(id);

        board.ifPresent(uphit -> {
            uphit.setHit(uphit.getHit() + 1);
            boardRepository.save(uphit);
        });
        return board.get();
    }

    @Transactional
    public void delBoard(long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void upBoard(long id, BoardDto boardDto, List<MultipartFile> file) throws Exception {
        Board updata = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패: 아이디가 없다");
        });//영속화 완료


        updata.setTitle(boardDto.getTitle());
        updata.setContents(boardDto.getContents());
        updata.setHopeaddress(boardDto.getHopeaddress());
        updata.setPrice(boardDto.getPrice());
        updata.setUpdate_date(new Date());

        if (file != null) {
            String arr[] = new String[3];
            String projectPath = System.getProperty("user.dir") + "\\front\\public\\upload"; //저장 경로 잡기
            UUID uuid = UUID.randomUUID(); //랜덤으로 아이디 만들어줌


            int i = 0;
            for (MultipartFile m : file) {
                String fileName = uuid + "_" + m.getOriginalFilename(); // uuid 붙여서 파일이름

                File saveFile = new File(projectPath, fileName); //경로랑 파일이름 만들어서 객체저장

                m.transferTo(saveFile);
                arr[i++] = fileName;
            }

            if (arr[1] == null) {
                updata.setFilepath1(arr[0]);
                updata.setFilepath2(null);
                updata.setFilepath3(null);


            } else if (arr[2] == null) {
                updata.setFilepath1(arr[0]);
                updata.setFilepath2(arr[1]);
                updata.setFilepath3(null);

            } else {
                updata.setFilepath1(arr[0]);
                updata.setFilepath2(arr[1]);
                updata.setFilepath3(arr[2]);
            }
        }

    }

    @Transactional(readOnly = true)
    public Page<Reply> replyList(Long id, Pageable pageable) {

        boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("보드아이디가 없다");
        });
        System.out.println("Service" + id);
        return replyRepository.findByBoard_id(id, pageable);
    }

    @Transactional
    public Reply replyadd(Long id, ReplyDto replyDto, Member memberInfo) {
        boardRepository.findById(id).orElseThrow(() -> {
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
        replyRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("리플아이디가 없다");
        });
        replyRepository.deleteById(id);
    }

    @Transactional
    public void upRelpy(Long id, Reply reply) {
        Reply updata = replyRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("작성자 아이디가 아닙니다");

        });//영속화 완료


        updata.setContent(reply.getContent());
    }

    @Transactional(readOnly = true)
    public Page oneCategory(Pageable pageable) {

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
