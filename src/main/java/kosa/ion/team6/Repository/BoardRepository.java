package kosa.ion.team6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kosa.ion.team6.Domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
   Optional<Board> findById(Long id);
}
