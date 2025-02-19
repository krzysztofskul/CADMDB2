package pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCategoryRepo extends JpaRepository<RoomCategory, Long>{

}
