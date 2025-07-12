package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

	List<Room> findAllByProductList_Id(Long productId);

}
