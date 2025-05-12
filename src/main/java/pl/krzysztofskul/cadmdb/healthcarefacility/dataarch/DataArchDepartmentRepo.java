package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataArchDepartmentRepo extends JpaRepository<DataArchDepartment, Long> {

}
