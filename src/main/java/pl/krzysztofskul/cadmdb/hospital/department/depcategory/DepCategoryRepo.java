package pl.krzysztofskul.cadmdb.hospital.department.depcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepCategoryRepo extends JpaRepository<DepCategory, Long>{

}
