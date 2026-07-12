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
			department.setFunctionEnum(FunctionEnum.OTHER);
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
	
	public List<Department> initDemoListAndReturn() {
		// init demo department list
		List<Department> departmentList = new ArrayList<Department>();
		Department newDepartment = new Department();
		
		//create new department
		newDepartment = this.initDataAndReturn();
		newDepartment.setNameStandardized(nameStandardizedService.loadByCode("X-BO"));
		newDepartment.setFunctionEnum(FunctionEnum.MEDICAL);
		DataArchDepartment dataArchDepartment = new DataArchDepartment();
		newDepartment.setDataArchDepartment(dataArchDepartment);				
		//add new dpartment to the list
		departmentList.add(newDepartment);

		//create new department
		newDepartment = this.initDataAndReturn();
		newDepartment.setNameStandardized(nameStandardizedService.loadByCode("L-KARD"));
		newDepartment.setFunctionEnum(FunctionEnum.MEDICAL);
		dataArchDepartment = new DataArchDepartment();
		newDepartment.setDataArchDepartment(dataArchDepartment);				
		//add new department to the list
		departmentList.add(newDepartment);

		//create new department
		newDepartment = this.initDataAndReturn();
		newDepartment.setNameStandardized(nameStandardizedService.loadByCode("D-ZZY"));
		newDepartment.setFunctionEnum(FunctionEnum.MEDICAL);
		dataArchDepartment = new DataArchDepartment();
		newDepartment.setDataArchDepartment(dataArchDepartment);				
		//add new department to the list
		departmentList.add(newDepartment);

		//create new department
		newDepartment = this.initDataAndReturn();
		newDepartment.setNameStandardized(nameStandardizedService.loadByCode("A-ADM"));
		newDepartment.setFunctionEnum(FunctionEnum.OFFICE);
		dataArchDepartment = new DataArchDepartment();
		newDepartment.setDataArchDepartment(dataArchDepartment);				
		//add new department to the list
		departmentList.add(newDepartment);
		
		return departmentList;
	}

}
