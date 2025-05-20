package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom.DataArchRoom;
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
		for (NameStandardized nameStandardized : nameStandardizedService.loadRandomList(Random.randomInt(5, 7))) {
			room.setRoomNo(String.valueOf(new Random().nextInt(200)));
			room.setNameStandardized(nameStandardized);
			room.setFunctionEnum(FunctionEnum.OTHERS);
			if (nameStandardized.getNameStandardizedPl().equals("Sala Operacyjna")) {
				room.setName("Sala operacyjna im. Prof. "+LoremIpsum.getInstance().getName());
			} else {
				room.setName(nameStandardized.getNameStandardizedPl());
			}
			DataArchRoom dataArchRoom = new DataArchRoom();
			dataArchRoom.setArea(new Random().nextFloat(100));
			room.setDataArchRoom(dataArchRoom);
		}
		return room;
	}

	@Override
	public List<Room> initListAndReturn() {		
		List<Room> roomList = new ArrayList<Room>();
		for (int i = 0; i < Random.randomInt(2, 3); i++) {
			roomList.add(this.initDataAndReturn());			
		}

		return roomList;
	}

}
