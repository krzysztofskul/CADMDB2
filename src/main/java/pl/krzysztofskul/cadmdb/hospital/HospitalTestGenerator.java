package pl.krzysztofskul.cadmdb.hospital;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.address.AddressTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategory;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryTestGenerator;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class HospitalTestGenerator implements InitDataGenerator<Hospital> {

	@Autowired
	private DepartmentTestGenerator departmentTestGenerator;
	@Autowired
	private AddressTestGenerator addressTestGenerator;
	
	@Override
	public Hospital initDataAndReturn() {
		Hospital hospital = new Hospital();
		hospital.setName(LoremIpsum.getInstance().getTitle(1, 3) + " Hospital");
		hospital.setAddress(addressTestGenerator.initDataAndReturn());
		hospital.setDepartmentList(departmentTestGenerator.iniListAndReturn());
		return hospital;
	}

	@Override
	public List<Hospital> iniListAndReturn() {
		
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		for (int i = 0; i < Random.randomInt(5, 10) ; i++) {
			hospitalList.add(this.initDataAndReturn());			
		}		
		return hospitalList;
	}

	
	
}
