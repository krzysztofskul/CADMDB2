package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class DepartmentTestGenerator implements InitDataGenerator<Department>{

	@Override
	public Department initDataAndReturn() {
		
		return new Department(LoremIpsum.getInstance().getTitle(2) + " Department");
	}

	@Override
	public List<Department> iniListAndReturn() {		
		List<Department> departmentList = new ArrayList<Department>();
		for (int i = 0; i < Random.randomInt(2, 3); i++) {
			departmentList.add(initDataAndReturn());
		}
		return departmentList;
	}

}
