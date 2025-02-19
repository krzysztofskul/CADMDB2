package pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.init.InitDataGenerator;

@Service
public class RoomCategoryTestGenerator implements InitDataGenerator<RoomCategory> {
	
	private Map<String, String> roomcategorynames = new HashMap<String, String>();
	
	@Override
	/**
	 *Create and return clear room category instance. *
	 */
	public RoomCategory initDataAndReturn() {
		RoomCategory roomCategory = new RoomCategory();
		return roomCategory;
	}

	@Override
	public List<RoomCategory> iniListAndReturn() {
		List<RoomCategory> roomCategoryList = new ArrayList<RoomCategory>();
		for (Map.Entry<String, String> entry : roomcategorynames.entrySet()) {
			RoomCategory roomCategory = this.initDataAndReturn();
			roomCategory.setRoomcategorynamePL(entry.getKey());
			roomCategory.setRoomcategorynameEN(entry.getValue());
			roomCategoryList.add(roomCategory);
		}
		return roomCategoryList;
	}

	public Map<String, String> getRoomCategoryNames() {
		return this.roomcategorynames;
	}
	
	/**
	 * Constructor
	 */
	public RoomCategoryTestGenerator() {
		roomcategorynames.put("Sala Operacyjna", "Operating Room");
		roomcategorynames.put("Pomieszczenie Przygotowania Pacjenta", "Patient Preparation Room");
		roomcategorynames.put("Pomieszczenie Przygotowania Lekarzy", "Doctor Preparation Room");
		roomcategorynames.put("Sekretariat", "Secretary's Office");
		roomcategorynames.put("Brudownik", "Soiled Utility Room");
		roomcategorynames.put("Pomieszczenie Techniczne", "Equipment Room");
	}
}
