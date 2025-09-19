package pl.krzysztofskul.cadmdb.hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.address.AddressTestGenerator;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.DataArchHospital;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.product.Product;
import pl.krzysztofskul.cadmdb.product.ProductService;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class HospitalDemoGenerator implements InitDataGenerator<Hospital> {

	private Map<String, String> demoHospitalNames = new HashedMap<String, String>();
	private int iteration = 0;
	
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private DepartmentTestGenerator departmentTestGenerator;
	@Autowired
	private AddressTestGenerator addressTestGenerator;
	@Autowired
	private ProductService productService;
		
	/**
	 * Constructor
	 */
	public HospitalDemoGenerator() {
		demoHospitalNames.put("Szpital Testowy", "Test Hospital");
		demoHospitalNames.put("Szpital Demonstracyjny", "Demo Hospital");
		demoHospitalNames.put("Szpital Przykładowy", "Example Hospital");
	}

	@Override
	public Hospital initDataAndReturn() {
		Hospital hospital = new Hospital();
		if (iteration == 0) {
			hospital.setNamePL("Szpital Testowy");
			hospital.setNameEN(demoHospitalNames.get("Szpital Testowy"));
			hospital.setAddress(new Address("Poland", "Warszawa", "09-909", "Testowa", "9", ""));
		}
		if (iteration == 1) {
			hospital.setNamePL("Szpital Demonstracyjny");
			hospital.setNameEN(demoHospitalNames.get("Szpital Demonstracyjny"));
			hospital.setAddress(new Address("Poland", "Kraków", "07-707", "Demonstracyjna", "7", ""));
		}
		if (iteration == 2) {
			hospital.setNamePL("Szpital Przykładowy");
			hospital.setNameEN(demoHospitalNames.get("Szpital Przykładowy"));
			hospital.setAddress(new Address("Poland", "Wrocław", "05-907", "Przykładowa", "97", ""));
		}
		iteration++;
		
		hospital.setName(hospital.getNamePL()+" / "+hospital.getNameEN());

		DataArchHospital dataArchHospital = new DataArchHospital();
		hospital.setDataArchHospital(dataArchHospital);
		return hospital;
	}

	@Override
	public List<Hospital> initListAndReturn() {
		
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		for (int i = 0; i < 3 ; i++) {
			hospitalList.add(this.initDataAndReturn());			
		}
		return hospitalList;
	}

	
	
}
