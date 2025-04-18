package pl.krzysztofskul.cadmdb.hospital;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.address.AddressTestGenerator;
import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategory;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class HospitalTestGenerator implements InitDataGenerator<Hospital> {

	@Autowired
	private DepartmentTestGenerator departmentTestGenerator;
	@Autowired
	private AddressTestGenerator addressTestGenerator;
	@Autowired
	private DeviceService deviceService;
	
	@Override
	public Hospital initDataAndReturn() {
		Hospital hospital = new Hospital();
		hospital.setName(LoremIpsum.getInstance().getTitle(1, 3));
		hospital.setNamePL("Szpital " + hospital.getName());
		hospital.setNameEN(hospital.getName()+ " Hospital");
		hospital.setAddress(addressTestGenerator.initDataAndReturn());
		hospital.setDepartmentList(departmentTestGenerator.iniListAndReturn());
		for (Department department : hospital.getDepartmentList()) {
			department.setAddress(hospital.getAddress());
			for (Room room : department.getRoomList()) {
				room.setAddress(department.getAddress());
			}
		}
		return hospital;
	}

	@Override
	public List<Hospital> iniListAndReturn() {
		
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		for (int i = 0; i < Random.randomInt(3, 5) ; i++) {
			hospitalList.add(this.initDataAndReturn());			
		}
		for (Hospital hospital : hospitalList) {
			for (Department department : hospital.getDepartmentList()) {
				for (Room room : department.getRoomList()) {
					List<Device> deviceListAll = deviceService.loadAll();
					room.addDevice(deviceListAll.get(new Random().nextInt(deviceListAll.size())));
					room.addDevice(deviceListAll.get(new Random().nextInt(deviceListAll.size())));
					room.addDevice(deviceListAll.get(new Random().nextInt(deviceListAll.size())));
				}
			}
		}
		return hospitalList;
	}

	
	
}
