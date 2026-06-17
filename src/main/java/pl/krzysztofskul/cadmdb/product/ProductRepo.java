package pl.krzysztofskul.cadmdb.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.krzysztofskul.cadmdb.product.category.Category;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

	List<Product> findAllByIsActiveOrderByCategoryNamePLAsc(boolean isActive);

	List<Product> findAllByCategory(Category category);
	
}
