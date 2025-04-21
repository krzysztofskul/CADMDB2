package pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("Room_NameStandardizedRepo")
public interface NameStandardizedRepo extends JpaRepository<NameStandardized, Long>{

}
