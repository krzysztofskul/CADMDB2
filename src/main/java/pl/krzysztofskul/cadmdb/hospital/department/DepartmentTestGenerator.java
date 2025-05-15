package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedTestGenerator;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomTestGenerator;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;


@Service
public class DepartmentTestGenerator implements InitDataGenerator<Department>{

	@Autowired
	HealthcareFacilityService hfService;
	@Autowired
	private NameStandardizedService nameStandardizedService;
	@Autowired
	private RoomTestGenerator roomTestGenerator;
	@Autowired
	private DepartmentService departmentService;
	
	@Override
	public Department initDataAndReturn() {
		
		return new Department();
	}

	@Override
	public List<Department> iniListAndReturn() {		
		List<Department> departmentList = new ArrayList<Department>();
		List<NameStandardized> depCategoryList = nameStandardizedService.loadRandomList(Random.randomInt(3, 5));
		for (NameStandardized nameStandardized : depCategoryList) {
			Department department = this.initDataAndReturn();
			department.setNameStandardized(nameStandardized);
			if (nameStandardized.getNameStandardizedPl().equals("Blok Operacyjny")) {
				department.setName(nameStandardized.getNameStandardizedPl()+" im. Prof. "+LoremIpsum.getInstance().getName());
			} else {
				department.setName(nameStandardized.getNameStandardizedPl());
			}
			
			List<Room> roomList = roomTestGenerator.iniListAndReturn();
			for (Room room : roomList) {
				//department = departmentService.addRoom(department, room);
				department = hfService.addRoomToDepartment(room, department);
			}
			
			
			departmentList.add(department);
		}
		return departmentList;
	}

}
