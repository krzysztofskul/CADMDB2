package pl.krzysztofskul.cadmdb.hospital.department.depcategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.init.InitDataGenerator;

@Service
public class DepCategoryTestGenerator implements InitDataGenerator<DepCategory> {
	
	private Map<String, String> depcategorynames = new HashMap<String, String>();
	
	@Override
	/**
	 *Create and return clear department category instance. *
	 */
	public DepCategory initDataAndReturn() {
		DepCategory depCategory = new DepCategory();
		return depCategory;
	}

	@Override
	public List<DepCategory> iniListAndReturn() {
		List<DepCategory> depCategoryList = new ArrayList<DepCategory>();
		for (Map.Entry<String, String> entry : depcategorynames.entrySet()) {
			DepCategory depCategory = this.initDataAndReturn();
			depCategory.setDepcategorynamePL(entry.getKey());
			depCategory.setDepcategorynameEN(entry.getValue());
			depCategoryList.add(depCategory);
		}
		return depCategoryList;
	}

	public Map<String, String> getDepCategoryNames() {
		return this.depcategorynames;
	}
	
	/**
	 * Constructor
	 */
	public DepCategoryTestGenerator() {
		depcategorynames.put("Blok Operacyjny", "Operation Theater");
		depcategorynames.put("Oddział Intensywnej Terapii", "Intesive Care Unit");
		depcategorynames.put("Szpitalny Oddział Ratunkowy", "Emergency Department");
		depcategorynames.put("Centralna Sterylizatornia", "Central Sterile Services Department");
		
		depcategorynames.put("Oddział Diagnostyki Obrazowej", "Diagnostic Imaging Department");
		depcategorynames.put("Apteka Szpitalna", "Hospital Pharmacy");
		
	    depcategorynames.put("Oddział Kardiologii", "Cardiology Department");
	    depcategorynames.put("Oddział Neurologii", "Neurology Department");
	    depcategorynames.put("Oddział Ortopedyczny", "Orthopedic Department");
	    depcategorynames.put("Oddział Chirurgii Ogólnej", "General Surgery Department");
	    depcategorynames.put("Oddział Chirurgii Naczyniowej", "Vascular Surgery Department");
	    depcategorynames.put("Oddział Onkologii", "Oncology Department");
	    depcategorynames.put("Oddział Hematologii", "Hematology Department");
	    depcategorynames.put("Oddział Pulmonologii", "Pulmonology Department");
	    depcategorynames.put("Oddział Urologii", "Urology Department");
	    depcategorynames.put("Oddział Nefrologii", "Nephrology Department");
	    depcategorynames.put("Oddział Endokrynologii", "Endocrinology Department");
	    depcategorynames.put("Oddział Gastrologii", "Gastroenterology Department");
	    depcategorynames.put("Oddział Dermatologiczny", "Dermatology Department");
	    depcategorynames.put("Oddział Laryngologii", "ENT (Otolaryngology) Department");
	    depcategorynames.put("Oddział Okulistyczny", "Ophthalmology Department");
	    depcategorynames.put("Oddział Ginekologiczny", "Gynecology Department");
	    depcategorynames.put("Oddział Ginekologiczno-Położniczy", "Gynecology and Obstetrics Department");
	    depcategorynames.put("Oddział Położniczy", "Obstetrics Department");
	    depcategorynames.put("Oddział Neonatologiczny", "Neonatology Department");
	    depcategorynames.put("Oddział Pediatryczny", "Pediatrics Department");
	    depcategorynames.put("Oddział Psychiatryczny", "Psychiatry Department");
	    depcategorynames.put("Oddział Rehabilitacyjny", "Rehabilitation Department");
	    depcategorynames.put("Oddział Geriatryczny", "Geriatrics Department");
	    depcategorynames.put("Oddział Anestezjologii", "Anesthesiology Department");
	    depcategorynames.put("Oddział Transplantologii", "Transplantology Department");
	    depcategorynames.put("Oddział Chorób Zakaźnych", "Infectious Diseases Department");
	    depcategorynames.put("Oddział Reumatologii", "Rheumatology Department");
	    
	    depcategorynames.put("Dział Administracji", "Administration Department");
	    depcategorynames.put("Archiwum Medyczne", "Medical Records Archive");
		
	}
	
	
	
}
