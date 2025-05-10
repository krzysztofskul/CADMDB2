package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataArchRoomRepo extends JpaRepository<DataArchRoom, Long> {

}
