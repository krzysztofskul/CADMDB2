package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;

@Service
public class DepartmentDemoGenerator implements InitDataGenerator<Department>{

	@Autowired
	private NameStandardizedService nameStandardizedService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Override
	public Department initDataAndReturn() {
		Department department = new Department();
		return department;
	}

	@Override
	public List<Department> initListAndReturn() {
		List<Department> departmentList = new ArrayList<Department>();		
		departmentList.add(this.createDemoDepartment(nameStandardizedService.loadByCode("X-BO")));		
		departmentList.add(this.createDemoDepartment(nameStandardizedService.loadByCode("L-KARD")));
		departmentList.add(this.createDemoDepartment(nameStandardizedService.loadByCode("A-ADM")));
		return departmentList;
	}

	private Department createDemoDepartment(NameStandardized nameStandardized) {
		Department department = this.initDataAndReturn();
		department.setNameStandardized(nameStandardized);
		department = departmentService.saveAndReturn(department);
		//department = departmentService.addDefaultRooms(department);
		return department;
	}
	
	
}
