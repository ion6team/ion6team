package kosa.ion.team6.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kosa.ion.team6.Domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	@EntityGraph(attributePaths = "authorities") // Eager조회로 authorities정보를 같이 가져옴,
	Optional<Member> findOneWithAuthoritiesByEmail(String email);
	// 이메일을 기준으로 Member 정보를 가져올 때 권한 정보를 함께 가져옴

	 Member findByEmail(String email);
	 boolean existsByEmail(String email);
	 Member findById(long id);
}
