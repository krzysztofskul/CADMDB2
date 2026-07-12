package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom.DataArchRoom;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class RoomDefaultGenerator {

	@Autowired
	private NameStandardizedService nameStandardizedService;
	
	private List<Room> defaultRoomList = new ArrayList<Room>();
	
	private Room initDataAndReturn() {
		Room room = new Room(true);
		return room;
	}
	
	private Room initDataAndReturn(String nameStandardizedCode) {
		Room room = this.initDataAndReturn();
		room.setNameStandardized(nameStandardizedService.loadByCode(nameStandardizedCode));
		DataArchRoom dataArchRoom = new DataArchRoom();
		if (nameStandardizedCode == "X-SO") {
			dataArchRoom.setArea(40);
		}
		if (nameStandardizedCode == "X-PP") {
			dataArchRoom.setArea(20);
		}
		if (nameStandardizedCode == "X-PL") {
			dataArchRoom.setArea(8);
		}
		if (nameStandardizedCode == "X-ZAB") {
			dataArchRoom.setArea(20);
		}
		if (nameStandardizedCode == "L-PIEL") {
			dataArchRoom.setArea(8);
		}
		if (nameStandardizedCode == "L-SCH") {
			dataArchRoom.setArea(25);
		}
		if (nameStandardizedCode == "L-I") {
			dataArchRoom.setArea(10);
		}
		if (nameStandardizedCode == "H-SHS") {
			dataArchRoom.setArea(8);
		}
		if (nameStandardizedCode == "H-SP") {
			dataArchRoom.setArea(9);
		}
		if (nameStandardizedCode == "X-SNP") {
			dataArchRoom.setArea(6);
		}
		if (nameStandardizedCode == "H-BRUD") {
			dataArchRoom.setArea(8);
		}	
		if (nameStandardizedCode == "A-SOC") {
			dataArchRoom.setArea(12);
		}		
		if (nameStandardizedCode == "A-POKK") {
			dataArchRoom.setArea(16);
		}		
		if (nameStandardizedCode == "A-POKO") {
			dataArchRoom.setArea(16);
		}		
		if (nameStandardizedCode == "A-POKD") {
			dataArchRoom.setArea(16);
		}		
		if (nameStandardizedCode == "A-POKPN") {
			dataArchRoom.setArea(16);
		}		
		if (nameStandardizedCode == "A-POKP") {
			dataArchRoom.setArea(14);
		}		
		if (nameStandardizedCode == "A-POKL") {
			dataArchRoom.setArea(14);
		}		
		if (nameStandardizedCode == "A-SEKR") {
			dataArchRoom.setArea(12);
		}		
//		if (nameStandardizedCode == "D-RTG") {
//			dataArchRoom.setArea(20);
//		}		
//		if (nameStandardizedCode == "D-MRI") {
//			dataArchRoom.setArea(25);
//		}		
//		if (nameStandardizedCode == "D-CT") {
//			dataArchRoom.setArea(18);
//		}		
//		if (nameStandardizedCode == "D-ANGIO") {
//			dataArchRoom.setArea(36);
//		}		
//		if (nameStandardizedCode == "D-USG") {
//			dataArchRoom.setArea(12);
//		}		
//		if (nameStandardizedCode == "D-STER") {
//			dataArchRoom.setArea(8);
//		}		
//		if (nameStandardizedCode == "T-TECH") {
//			dataArchRoom.setArea(8);
//		}		
//		if (nameStandardizedCode == "A-REJ") {
//			dataArchRoom.setArea(12);
//		}		
//		if (nameStandardizedCode == "S-POCZ") {
//			dataArchRoom.setArea(30);
//		}		
//		if (nameStandardizedCode == "S-SP") {
//			dataArchRoom.setArea(6);
//		}		
//		if (nameStandardizedCode == "S-KP") {
//			dataArchRoom.setArea(4);
//		}				
		room.setDataArchRoom(dataArchRoom);
		return room;
	}

	public List<Room> initListAndReturn() {		
		List<Room> roomList = this.defaultRoomList;
		roomList.add(this.initDataAndReturn("X-SO"));
		roomList.add(this.initDataAndReturn("X-PP"));
		roomList.add(this.initDataAndReturn("X-PL"));
		roomList.add(this.initDataAndReturn("X-ZAB"));
		roomList.add(this.initDataAndReturn("L-PIEL"));
		roomList.add(this.initDataAndReturn("L-SCH"));
		roomList.add(this.initDataAndReturn("L-I"));
		roomList.add(this.initDataAndReturn("H-SHS"));
		roomList.add(this.initDataAndReturn("H-SP"));
		roomList.add(this.initDataAndReturn("X-SNP"));
		roomList.add(this.initDataAndReturn("H-BRUD"));
		roomList.add(this.initDataAndReturn("A-SOC"));
		roomList.add(this.initDataAndReturn("A-POKK"));
		roomList.add(this.initDataAndReturn("A-POKO"));
		roomList.add(this.initDataAndReturn("A-POKD"));
		roomList.add(this.initDataAndReturn("A-POKPN"));
		roomList.add(this.initDataAndReturn("A-POKP"));
		roomList.add(this.initDataAndReturn("A-POKL"));
		roomList.add(this.initDataAndReturn("A-SEKR"));
//		roomList.add(this.initDataAndReturn("D-RTG"));
//		roomList.add(this.initDataAndReturn("D-MRI"));
//		roomList.add(this.initDataAndReturn("D-CT"));
//		roomList.add(this.initDataAndReturn("D-ANGIO"));
//		roomList.add(this.initDataAndReturn("D-USG"));
//		roomList.add(this.initDataAndReturn("D-STER"));
//		roomList.add(this.initDataAndReturn("T-TECH"));
//		roomList.add(this.initDataAndReturn("A-REJ"));
//		roomList.add(this.initDataAndReturn("S-POCZ"));
//		roomList.add(this.initDataAndReturn("S-SP"));
//		roomList.add(this.initDataAndReturn("S-KP"));	
		return roomList;
	}
	
}
