package pl.krzysztofskul.cadmdb.company.manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long>{

}
