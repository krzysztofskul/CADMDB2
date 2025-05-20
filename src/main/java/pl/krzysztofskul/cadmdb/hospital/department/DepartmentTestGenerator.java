package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.DataArchDepartment;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;


@Service
public class DepartmentTestGenerator implements InitDataGenerator<Department>{

	@Autowired
	HealthcareFacilityService hfService;
	@Autowired
	private NameStandardizedService nameStandardizedService;
	
	@Override
	public Department initDataAndReturn() {
		return new Department();
	}

	@Override
	public List<Department> initListAndReturn() {		
		List<Department> departmentList = new ArrayList<Department>();
		List<NameStandardized> depCategoryList = nameStandardizedService.loadRandomList(Random.randomInt(3, 5));
		for (NameStandardized nameStandardized : depCategoryList) {
			Department department = this.initDataAndReturn();
			department.setNameStandardized(nameStandardized);
			department.setFunctionEnum(FunctionEnum.OTHERS);
			DataArchDepartment dataArchDepartment = new DataArchDepartment();
			department.setDataArchDepartment(dataArchDepartment);
			if (nameStandardized.getNameStandardizedPl().equals("Blok Operacyjny")) {
				department.setName(nameStandardized.getNameStandardizedPl()+" im. Prof. "+LoremIpsum.getInstance().getName());
			} else {
				department.setName(nameStandardized.getNameStandardizedPl());
			}
			departmentList.add(department);
		}
		return departmentList;
	}

}
