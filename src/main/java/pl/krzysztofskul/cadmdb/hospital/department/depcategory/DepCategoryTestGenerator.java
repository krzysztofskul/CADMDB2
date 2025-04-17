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
		
	}
	
	
	
}
