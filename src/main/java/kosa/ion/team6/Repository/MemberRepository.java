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

import kosa.ion.team6.Domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	@EntityGraph(attributePaths = "authorities") // Eager조회로 authorities정보를 같이 가져옴,
	Optional<Member> findOneWithAuthoritiesByEmail(String email);
	// 이메일을 기준으로 Member 정보를 가져올 때 권한 정보를 함께 가져옴
	@EntityGraph(attributePaths = "authorities")
	Page<Member> findAll(Pageable pageable);

	Member findByNameAndResident1AndResident2(String name, String resident1, String resident2);

	@Query(value="select * from member where member_id=:keyword", nativeQuery = true)
	Page<Member> findMemberById(@Param("keyword") String keyword, Pageable pageable);

	Page<Member> findByEmailContains(String email, Pageable pageable);
	Page<Member> findByNameContains(String name, Pageable pageable);
	Page<Member> findByAddressContains(String address, Pageable pageable);

}
