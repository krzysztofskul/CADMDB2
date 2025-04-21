package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;


@Service
public class RoomTestGenerator implements InitDataGenerator<Room>{

	@Autowired
	private NameStandardizedService nameStandardizedService;
	
	@Override
	public Room initDataAndReturn() {
		Room room = new Room();
		return room;
	}

	@Override
	public List<Room> iniListAndReturn() {		
		List<Room> roomList = new ArrayList<Room>();
		for (NameStandardized nameStandardized : nameStandardizedService.loadRandomList(Random.randomInt(5, 7))) {
			Room room = this.initDataAndReturn();
			room.setRoomNo(String.valueOf(new Random().nextInt(200)));
			room.setNameStandardized(nameStandardized);
			if (nameStandardized.getNameStandardizedPl().equals("Sala Operacyjna")) {
				room.setName("Sala operacyjna im. Prof. "+LoremIpsum.getInstance().getName());
			} else {
				room.setName(nameStandardized.getNameStandardizedPl());
			}
			roomList.add(room);
		}
		return roomList;
	}

}
