package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

	private RoomRepo roomRepo;

	/**
	 * @param roomRepo
	 */
	@Autowired
	public RoomService(RoomRepo roomRepo) {
		super();
		this.roomRepo = roomRepo;
	}
	
	public Room saveAndReturn(Room room) {
		return roomRepo.save(room);
	}
	
	public Room loadById(Long id) {
		return roomRepo.findById(id).get();
	}

	public Room loadByIdWithEquipment(Long id) {
		Room room = this.loadById(id);
		Hibernate.initialize(room.getProductList());
		return room;	
	}
	
	public Room loadByIdWithDataArchRoom(Long id) {
		Room room = this.loadById(id);
		Hibernate.initialize(room.getDataArchRoom());
		return room;
	}	
	
	public Room loadByIdWithEquipmentAndDataArch(Long id) {
		Room room = this.loadById(id);
		Hibernate.initialize(room.getProductList());
		Hibernate.initialize(room.getDataArchRoom());
		return room;
	}
	
	public List<Room> loadAll() {
		return roomRepo.findAll();
	}

	public List<Room> loadAllByProductList_Id(Long productId) {
		List<Room> roomList = roomRepo.findAllByProductList_Id(productId);
		return roomList;
	}
	
	public void deleteById(Long id) {	
		roomRepo.deleteById(id);
	}
	
}
