package kosa.ion.team6.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kosa.ion.team6.Domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	 Member findByEmail(String email);
	 boolean existsByEmail(String email);
	 Member findById(long id);
}
