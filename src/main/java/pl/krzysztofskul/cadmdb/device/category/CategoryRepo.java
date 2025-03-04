package pl.krzysztofskul.cadmdb.device.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	Category findByCode(String code);
	Category findByNamePL(String namePL);
	Category findByNameEN(String nameEN);
	
}
