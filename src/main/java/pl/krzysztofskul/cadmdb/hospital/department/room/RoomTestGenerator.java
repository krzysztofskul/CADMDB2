package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategory;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategoryService;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;


@Service
public class RoomTestGenerator implements InitDataGenerator<Room>{

	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@Override
	public Room initDataAndReturn() {
		
		return new Room();
	}

	@Override
	public List<Room> iniListAndReturn() {		
		List<Room> roomList = new ArrayList<Room>();
		for (RoomCategory roomCategory : roomCategoryService.loadAll()) {
			Room room = this.initDataAndReturn();
			room.setRoomCategory(roomCategory);
			roomList.add(room);
		}
		return roomList;
	}

}
