package pl.krzysztofskul.cadmdb;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.company.manufacturer.Manufacturer;
import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerService;
import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerTestGenerator;
import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceTestGenerator;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.device.category.Category;
import pl.krzysztofskul.cadmdb.device.category.CategoryGenerator;
import pl.krzysztofskul.cadmdb.device.category.CategoryService;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.HospitalTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentService;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomTestGenerator;
import pl.krzysztofskul.cadmdb.random.Random;


@Service
public class HomeService {

	private HealthcareFacilityService healthcareFacilityService;
	private HospitalTestGenerator hospitalTestGenerator;
	private DepartmentTestGenerator departmentTestGenerator;
	private RoomTestGenerator roomTestGenerator;
	private HospitalService hospitalService;
	private DepartmentService departmentService;
	private RoomService roomService;
	private NameStandardizedService department_nameStandardizedService;
	private NameStandardizedTestGenerator department_nameStandardizedTestGenerator;
	private pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService room_nameStandardizedService;
	private pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedTestGenerator room_nameStandardizedTestGenerator;
	private CategoryService categoryService;
	private CategoryGenerator categoryGenerator;
	private DeviceTestGenerator deviceTestGenerator;
	private DeviceService deviceService;
	private ManufacturerTestGenerator manufacturerTestGenerator;
	private ManufacturerService manufacturerService;
	
	private boolean isEssentailDataInit = false;
	private boolean isDemoDataInit = false;
	
	/**
	 * Constructor
	 */
	@Autowired
	public HomeService(HealthcareFacilityService healthcareFacilityService, HospitalTestGenerator hospitalTestGenerator, HospitalService hospitalService,
			NameStandardizedService department_nameStandardizedService, NameStandardizedTestGenerator department_nameStandardizedTestGenerator,
			pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService room_nameStandardizedService, pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedTestGenerator room_nameStandardizedTestGenerator,
			CategoryService categoryService, CategoryGenerator categoryGenerator, DeviceTestGenerator deviceTestGenerator, DeviceService deviceService, ManufacturerTestGenerator manufacturerTestGenerator, ManufacturerService manufacturerService,
			DepartmentTestGenerator departmentTestGenerator, DepartmentService departmentService, RoomTestGenerator roomTestGenerator, RoomService roomService) {
		super();
		this.healthcareFacilityService = healthcareFacilityService;
		this.hospitalTestGenerator = hospitalTestGenerator;
		this.departmentTestGenerator = departmentTestGenerator;
		this.roomTestGenerator = roomTestGenerator;
		this.hospitalService = hospitalService;
		this.departmentService = departmentService;
		this.roomService = roomService;
		this.department_nameStandardizedService = department_nameStandardizedService;
		this.department_nameStandardizedTestGenerator = department_nameStandardizedTestGenerator;
		this.room_nameStandardizedService = room_nameStandardizedService;
		this.room_nameStandardizedTestGenerator = room_nameStandardizedTestGenerator;
		this.categoryService = categoryService;
		this.categoryGenerator = categoryGenerator;
		this.deviceTestGenerator = deviceTestGenerator;
		this.deviceService = deviceService;
		this.manufacturerTestGenerator = manufacturerTestGenerator;
		this.manufacturerService = manufacturerService;
	}

	public void initialDbEssentials() {
		if (isEssentailDataInit == false) {
			//init device categories
			for (Category category : categoryGenerator.initListAndReturn()) {
				categoryService.save(category);
			}
			//init room categories
			for (pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardized roomcategory : room_nameStandardizedTestGenerator.initListAndReturn()) {
				room_nameStandardizedService.save(roomcategory);
			}
			//init department categories
			for (NameStandardized depcategory : department_nameStandardizedTestGenerator.initListAndReturn()) {
				department_nameStandardizedService.save(depcategory);
			}
			//set essential data initialized
			isEssentailDataInit = true;
		}
		
	}
	
	public void initDbTest() {
		if (isDemoDataInit == false) {
			//init test manufcturers
			for (Manufacturer manufacturer : manufacturerTestGenerator.initListAndReturn()) {
				manufacturerService.save(manufacturer);
			}
			//init test devices
			for (Device device : deviceTestGenerator.initListAndReturn()) {
				deviceService.save(device);
			}
			;
			//init test hospitals
			for (Hospital hospital : hospitalTestGenerator.initListAndReturn()) {
				hospitalService.save(hospital);
			}
			//init and add test departments to hospitals
			for (Hospital hospital : hospitalService.loadAll()) {
				List<Department> departmentList = departmentTestGenerator.initListAndReturn();
				for (Department department : departmentList) {
					hospital.addDepartment(department);
					hospitalService.save(hospital);
				}
			}
			//init and add test rooms to departments
			for (Department department : departmentService.loadAll()) {
				List<Room> roomList = roomTestGenerator.initListAndReturn();
				for (Room room : roomList) {
					healthcareFacilityService.addRoomToDepartment(room, department);
				}
			}
			//init and add test equipment to rooms
			for (Room room : roomService.loadAll()) {
				for (int i = 0; i < Random.randomInt(2, 3); i++) {
					room = healthcareFacilityService.addDeviceToRoom(deviceService.loadRandom(), room);
				}
				roomService.saveAndReturn(room);
			}
			//set demo data initialized
			isDemoDataInit = true;
		}
	}
	
}
