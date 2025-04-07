package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategory;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategoryService;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;


@Service
public class RoomTestGenerator implements InitDataGenerator<Room>{

	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Override
	public Room initDataAndReturn() {
		Room room = new Room();
		List<Device> deviceListAll = deviceService.loadAll();
		room.addDevice(deviceListAll.get(new Random().nextInt(deviceListAll.size())));
		room.addDevice(deviceListAll.get(new Random().nextInt(deviceListAll.size())));
		room.addDevice(deviceListAll.get(new Random().nextInt(deviceListAll.size())));
		return room;
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
