package pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategory;
import pl.krzysztofskul.cadmdb.random.Random;

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
	
	public List<RoomCategory> loadRandomList(int amount) {
		List<RoomCategory> roomCategoryList = this.loadAll();
		List<RoomCategory> roomCategorylistAllowed = new ArrayList<RoomCategory>();
		for (int i = 0; i < amount; i++) {
			roomCategorylistAllowed.add(roomCategoryList.get(Random.randomInt(0, roomCategoryList.size())));
		}
		roomCategoryList.retainAll(roomCategorylistAllowed);
		return roomCategoryList;
	}
}
