package pl.krzysztofskul.cadmdb.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

	List<Product> findAllByIsActiveOrderByCategoryNamePLAsc(boolean isActive);

}
