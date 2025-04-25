package pl.krzysztofskul.cadmdb.hospital;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardized;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital, Long>{
	
}
