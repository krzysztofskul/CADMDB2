package pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.init.InitDataGenerator;

@Service("Room_NameStandardizedTestGenerators")
public class NameStandardizedTestGenerator implements InitDataGenerator<NameStandardized> {
	
	private Map<String, String> nameStandardizedMap = new HashMap<String, String>();
	
	@Override
	/**
	 *Create and return clear room category instance. *
	 */
	public NameStandardized initDataAndReturn() {
		NameStandardized nameStandardized = new NameStandardized();
		return nameStandardized;
	}

	@Override
	public List<NameStandardized> iniListAndReturn() {
		List<NameStandardized> nameStandardizedList = new ArrayList<NameStandardized>();
		for (Map.Entry<String, String> entry : nameStandardizedMap.entrySet()) {
			NameStandardized nameStandardized = this.initDataAndReturn();
			nameStandardized.setNameStandardizedPl(entry.getKey());
			nameStandardized.setNameStandardizedEn(entry.getValue());
			nameStandardizedList.add(nameStandardized);
		}
		return nameStandardizedList;
	}

	public Map<String, String> getRoomCategoryNames() {
		return this.nameStandardizedMap;
	}
	
	/**
	 * Constructor
	 */
	public NameStandardizedTestGenerator() {
		nameStandardizedMap.put("Sala Operacyjna", "Operating Room");
		nameStandardizedMap.put("Pomieszczenie Przygotowania Pacjenta", "Patient Preparation Room");
		nameStandardizedMap.put("Pomieszczenie Przygotowania Lekarzy", "Doctor Preparation Room");
		
		nameStandardizedMap.put("Pracownia CT", "CT Scan Room");
		nameStandardizedMap.put("Pracownia MRI", "MRI Room");
		nameStandardizedMap.put("Pracownia RTG", "X‑ray Room");
		nameStandardizedMap.put("Pracownia Angiografii", "Angiography Suite");
		nameStandardizedMap.put("Pracownia PET", "PET Lab");
		nameStandardizedMap.put("Pracownia PET‑CT", "PET‑CT Lab");
		nameStandardizedMap.put("Pracownia SPECT", "SPECT Lab");
		nameStandardizedMap.put("Pracownia USG", "Ultrasound Room");
		nameStandardizedMap.put("Gabinet Lekarski", "Doctor’s Office");
		nameStandardizedMap.put("Gabinet Diagnostyczno‑Zabiegowy", "Diagnostic-Treatment Room");
		
		nameStandardizedMap.put("Sekretariat", "Secretary's Office");
		nameStandardizedMap.put("Poczekalnia", "Waiting Room");
		nameStandardizedMap.put("Rejestracja", "Registration Desk");
		
		nameStandardizedMap.put("Brudownik", "Soiled Utility Room");
		nameStandardizedMap.put("Pomieszczenie Gospodarcze", "Janitor’s Closet");
		nameStandardizedMap.put("Myjnia Łóżek", "Bed Washer Room");
		
		nameStandardizedMap.put("Magazyn Sprzętu", "Equipment Storage");
		nameStandardizedMap.put("Magazyn Bielizny", "Linen Storage");
		nameStandardizedMap.put("Magazyn Bielizny Brudnej", "Dirty Linen Storage");
		nameStandardizedMap.put("Magazyn Bielizny Czystej", "Clean Linen Storage");
		nameStandardizedMap.put("Magazyn Odpadów Medycznych", "Medical Waste Storage");
		
		nameStandardizedMap.put("Pomieszczenie Techniczne", "Equipment Room");
		nameStandardizedMap.put("Sterownia", "Control Room");
		
		nameStandardizedMap.put("Sala Wybudzeń", "Post-Anaesthesia Care Unit");
		nameStandardizedMap.put("Sala Pooperacyjna", "Post‑operative Recovery Room");
		nameStandardizedMap.put("Sala Resuscytacyjna", "Resuscitation Room");
		nameStandardizedMap.put("Sala Obserwacyjna", "Observation Room");
		nameStandardizedMap.put("Sala Intensywnej Terapii", "Intesive Care Unit");
		
		nameStandardizedMap.put("Sala Chorych", "Patient Room");
		nameStandardizedMap.put("Izolatka", "Isolation Room");
		nameStandardizedMap.put("Sala Porodowa", "Delivery Room");
		nameStandardizedMap.put("Sala Połogowa", "Post‑partum Room");
		nameStandardizedMap.put("Sala Neonatologiczna", "Neonatal Room");
	
	}
}
