package kosa.ion.team6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kosa.ion.team6.Domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
   @Query(value = "select * from board where category_id =?1 ", nativeQuery=true)
   List<Board> findByCategory_Id(long id);

   Page<Board> findByHopeaddressContains(String hopeaddress, Pageable pageable);

   // 검색 //

//   Page<Board> findByCategory_idAndTitleContaining(Long id, String keyword, Pageable pageable);
//
//   Page<Board> findByTitleContaining(String keyword, Pageable pageable);
//
//   Page<Board> findByTitleContainingOrContentsContaining(String keyword, String keyword2, Pageable pageable);
//
//
//
//   Page<Board> findByCategory_idAndTitleContainingOrContentsContaining(Long id, String keyword, String keyword2,
//                                                                       Pageable pageable);
//
//   Page<Board> findByBoardaddress(String boardaddress,Pageable pageable);
//
//
//
//   Page<Board> findByBoardaddressAndTitleContainingOrContentsContaining(String hope_address, String keyword,
//                                                                        String keyword2, Pageable pageable);
//
//   Page<Board> findByBoardaddressAndCategory_idAndTitleContainingOrContentsContaining(String hope_address, Long id,
//                                                                                      String keyword, String keyword2, Pageable pageable);
//
//
//   Page<Board> findByCategory_id(long id, Pageable pageable);
//
//   Page<Board> findByBoardaddressAndCategory_id(String boardaddress, long id, Pageable pageable);
}
