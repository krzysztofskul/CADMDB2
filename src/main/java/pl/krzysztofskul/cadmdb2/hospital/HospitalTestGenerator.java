package pl.krzysztofskul.cadmdb2.hospital;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb2.hospital.department.DepartmentTestGenerator;
import pl.krzysztofskul.cadmdb2.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb2.random.Random;

@Service
public class HospitalTestGenerator implements InitDataGenerator<Hospital> {

	@Autowired
	private DepartmentTestGenerator departmentTestGenerator;
	
	@Override
	public Hospital initDataAndReturn() {
		Hospital hospital = new Hospital();
		hospital.setName(LoremIpsum.getInstance().getTitle(1, 3) + " Hospital");
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
