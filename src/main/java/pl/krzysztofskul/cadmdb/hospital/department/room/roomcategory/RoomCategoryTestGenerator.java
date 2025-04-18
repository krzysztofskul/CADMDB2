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
		
		roomcategorynames.put("Pracownia CT", "CT Scan Room");
		roomcategorynames.put("Pracownia MRI", "MRI Room");
		roomcategorynames.put("Pracownia RTG", "X‑ray Room");
		roomcategorynames.put("Pracownia Angiografii", "Angiography Suite");
		roomcategorynames.put("Pracownia PET", "PET Lab");
		roomcategorynames.put("Pracownia PET‑CT", "PET‑CT Lab");
		roomcategorynames.put("Pracownia SPECT", "SPECT Lab");
		roomcategorynames.put("Pracownia USG", "Ultrasound Room");
		roomcategorynames.put("Gabinet Lekarski", "Doctor’s Office");
		roomcategorynames.put("Gabinet Diagnostyczno‑Zabiegowy", "Diagnostic-Treatment Room");
		
		roomcategorynames.put("Sekretariat", "Secretary's Office");
		roomcategorynames.put("Poczekalnia", "Waiting Room");
		roomcategorynames.put("Rejestracja", "Registration Desk");
		
		roomcategorynames.put("Brudownik", "Soiled Utility Room");
		roomcategorynames.put("Pomieszczenie Gospodarcze", "Janitor’s Closet");
		roomcategorynames.put("Myjnia Łóżek", "Bed Washer Room");
		
		roomcategorynames.put("Magazyn Sprzętu", "Equipment Storage");
		roomcategorynames.put("Magazyn Bielizny", "Linen Storage");
		roomcategorynames.put("Magazyn Bielizny Brudnej", "Dirty Linen Storage");
		roomcategorynames.put("Magazyn Bielizny Czystej", "Clean Linen Storage");
		roomcategorynames.put("Magazyn Odpadów Medycznych", "Medical Waste Storage");
		
		roomcategorynames.put("Pomieszczenie Techniczne", "Equipment Room");
		roomcategorynames.put("Sterownia", "Control Room");
		
		roomcategorynames.put("Sala Wybudzeń", "Post-Anaesthesia Care Unit");
		roomcategorynames.put("Sala Pooperacyjna", "Post‑operative Recovery Room");
		roomcategorynames.put("Sala Resuscytacyjna", "Resuscitation Room");
		roomcategorynames.put("Sala Obserwacyjna", "Observation Room");
		roomcategorynames.put("Sala Intensywnej Terapii", "Intesive Care Unit");
		
		roomcategorynames.put("Sala Chorych", "Patient Room");
		roomcategorynames.put("Izolatka", "Isolation Room");
		roomcategorynames.put("Sala Porodowa", "Delivery Room");
		roomcategorynames.put("Sala Połogowa", "Post‑partum Room");
		roomcategorynames.put("Sala Neonatologiczna", "Neonatal Room");
	
	}
}
