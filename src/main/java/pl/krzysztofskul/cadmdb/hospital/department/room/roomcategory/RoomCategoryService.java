package pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomCategoryService {

	private RoomCategoryRepo roomCategoryRepo;

	/**
	 * @param roomCategoryRepo
	 */
	@Autowired
	public RoomCategoryService(RoomCategoryRepo roomCategoryRepo) {
		super();
		this.roomCategoryRepo = roomCategoryRepo;
	}

	public void save(RoomCategory roomcategory) {
		roomCategoryRepo.save(roomcategory);
	}
	
	public List<RoomCategory> loadAll() {
		return roomCategoryRepo.findAll();
	}
	
	
}
