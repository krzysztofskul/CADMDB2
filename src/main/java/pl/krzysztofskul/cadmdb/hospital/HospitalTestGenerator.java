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
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class HospitalTestGenerator implements InitDataGenerator<Hospital> {

	@Autowired
	private HospitalService hospitalService;
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
