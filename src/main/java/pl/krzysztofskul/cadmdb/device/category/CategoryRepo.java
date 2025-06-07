package pl.krzysztofskul.cadmdb.device.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	Category findByCode(String code);
	Category findByNamePL(String namePL);
	Category findByNameEN(String nameEN);
	List<Category> findAllByCodeStartingWith(String prefix);
	List<Category> findAllByCode(String code);
	
}
