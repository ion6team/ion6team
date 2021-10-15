package kosa.ion.team6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosa.ion.team6.Domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	 Category findById(long id);
}
