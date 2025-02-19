package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategory;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryService;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomTestGenerator;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;


@Service
public class DepartmentTestGenerator implements InitDataGenerator<Department>{

	@Autowired
	private DepCategoryService depCategoryService;
	@Autowired
	private RoomTestGenerator roomTestGenerator;
	
	@Override
	public Department initDataAndReturn() {
		
		return new Department();
	}

	@Override
	public List<Department> iniListAndReturn() {		
		List<Department> departmentList = new ArrayList<Department>();
		for (DepCategory depCategory : depCategoryService.loadAll()) {
			Department department = this.initDataAndReturn();
			department.setDepcategory(depCategory);
			department.setRoomList(roomTestGenerator.iniListAndReturn());
			departmentList.add(department);
		}
		return departmentList;
	}

}
