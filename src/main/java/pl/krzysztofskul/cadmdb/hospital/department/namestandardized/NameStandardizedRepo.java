package pl.krzysztofskul.cadmdb.hospital.department.namestandardized;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("Department_NameStandardizedRepo")
public interface NameStandardizedRepo extends JpaRepository<NameStandardized, Long>{

}
