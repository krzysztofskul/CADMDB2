package pl.krzysztofskul.cadmdb.hospital.department.namestandardized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;

@Service("Department_NameStandardizedTestGenerator")
public class NameStandardizedTestGenerator implements InitDataGenerator<NameStandardized> {
	
	private Map<String, String> nameStandardizedMap = new HashMap<String, String>();
	
	@Override
	/**
	 *Create and return clear department category instance. *
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

	public Map<String, String> getNameStandardizedNames() {
		return this.nameStandardizedMap;
	}
	
	/**
	 * Constructor
	 */
	public NameStandardizedTestGenerator() {
		nameStandardizedMap.put("Blok Operacyjny", "Operation Theater");
		nameStandardizedMap.put("Oddział Intensywnej Terapii", "Intesive Care Unit");
		nameStandardizedMap.put("Szpitalny Oddział Ratunkowy", "Emergency Department");
		nameStandardizedMap.put("Centralna Sterylizatornia", "Central Sterile Services Department");
		
		nameStandardizedMap.put("Oddział Diagnostyki Obrazowej", "Diagnostic Imaging Department");
		nameStandardizedMap.put("Apteka Szpitalna", "Hospital Pharmacy");
		
	    nameStandardizedMap.put("Oddział Kardiologii", "Cardiology Department");
	    nameStandardizedMap.put("Oddział Neurologii", "Neurology Department");
	    nameStandardizedMap.put("Oddział Ortopedyczny", "Orthopedic Department");
	    nameStandardizedMap.put("Oddział Chirurgii Ogólnej", "General Surgery Department");
	    nameStandardizedMap.put("Oddział Chirurgii Naczyniowej", "Vascular Surgery Department");
	    nameStandardizedMap.put("Oddział Onkologii", "Oncology Department");
	    nameStandardizedMap.put("Oddział Hematologii", "Hematology Department");
	    nameStandardizedMap.put("Oddział Pulmonologii", "Pulmonology Department");
	    nameStandardizedMap.put("Oddział Urologii", "Urology Department");
	    nameStandardizedMap.put("Oddział Nefrologii", "Nephrology Department");
	    nameStandardizedMap.put("Oddział Endokrynologii", "Endocrinology Department");
	    nameStandardizedMap.put("Oddział Gastrologii", "Gastroenterology Department");
	    nameStandardizedMap.put("Oddział Dermatologiczny", "Dermatology Department");
	    nameStandardizedMap.put("Oddział Laryngologii", "ENT (Otolaryngology) Department");
	    nameStandardizedMap.put("Oddział Okulistyczny", "Ophthalmology Department");
	    nameStandardizedMap.put("Oddział Ginekologiczny", "Gynecology Department");
	    nameStandardizedMap.put("Oddział Ginekologiczno-Położniczy", "Gynecology and Obstetrics Department");
	    nameStandardizedMap.put("Oddział Położniczy", "Obstetrics Department");
	    nameStandardizedMap.put("Oddział Neonatologiczny", "Neonatology Department");
	    nameStandardizedMap.put("Oddział Pediatryczny", "Pediatrics Department");
	    nameStandardizedMap.put("Oddział Psychiatryczny", "Psychiatry Department");
	    nameStandardizedMap.put("Oddział Rehabilitacyjny", "Rehabilitation Department");
	    nameStandardizedMap.put("Oddział Geriatryczny", "Geriatrics Department");
	    nameStandardizedMap.put("Oddział Anestezjologii", "Anesthesiology Department");
	    nameStandardizedMap.put("Oddział Transplantologii", "Transplantology Department");
	    nameStandardizedMap.put("Oddział Chorób Zakaźnych", "Infectious Diseases Department");
	    nameStandardizedMap.put("Oddział Reumatologii", "Rheumatology Department");
	    
	    nameStandardizedMap.put("Dział Administracji", "Administration Department");
	    nameStandardizedMap.put("Archiwum Medyczne", "Medical Records Archive");
		
	}
	
	
	
}
