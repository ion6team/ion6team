package kosa.ion.team6.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kosa.ion.team6.Domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	
	@Query(value = "select * from board where category_id =?1 ", nativeQuery=true)
	List<Board> findByCategory_Id(long id);

}
