package pl.krzysztofskul.cadmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceDemoGenerator;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.device.category.Category;
import pl.krzysztofskul.cadmdb.device.category.CategoryGenerator;
import pl.krzysztofskul.cadmdb.device.category.CategoryService;
import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.HospitalTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategory;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryService;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategory;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategoryService;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategoryTestGenerator;

@Service
public class HomeService {

	private HospitalTestGenerator hospitalTestGenerator;
	private HospitalService hospitalService;
	private DepCategoryService depCategoryService;
	private DepCategoryTestGenerator depCategoryTestGenerator;
	private RoomCategoryService roomCategoryService;
	private RoomCategoryTestGenerator roomCategoryTestGenerator;
	private CategoryService categoryService;
	private CategoryGenerator categoryGenerator;
	private DeviceDemoGenerator deviceDemoGenerator;
	private DeviceService deviceService;
	
	private boolean isEssentailDataInit = false;
	private boolean isDemoDataInit = false;
	
	/**
	 * Constructor
	 */
	@Autowired
	public HomeService(HospitalTestGenerator hospitalTestGenerator, HospitalService hospitalService,
			DepCategoryService depCategoryService, DepCategoryTestGenerator depCategoryTestGenerator,
			RoomCategoryService roomCategoryService, RoomCategoryTestGenerator roomCategoryTestGenerator,
			CategoryService categoryService, CategoryGenerator categoryGenerator, DeviceDemoGenerator deviceDemoGenerator, DeviceService deviceService) {
		super();
		this.hospitalTestGenerator = hospitalTestGenerator;
		this.hospitalService = hospitalService;
		this.depCategoryService = depCategoryService;
		this.depCategoryTestGenerator = depCategoryTestGenerator;
		this.roomCategoryService = roomCategoryService;
		this.roomCategoryTestGenerator = roomCategoryTestGenerator;
		this.categoryService = categoryService;
		this.categoryGenerator = categoryGenerator;
		this.deviceDemoGenerator = deviceDemoGenerator;
		this.deviceService = deviceService;
	}

	public void initialDbEssentials() {
		if (isEssentailDataInit == false) {
			//init device categories
			for (Category category : categoryGenerator.iniListAndReturn()) {
				categoryService.save(category);
			}
			//init room categories
			for (RoomCategory roomcategory : roomCategoryTestGenerator.iniListAndReturn()) {
				roomCategoryService.save(roomcategory);
			}
			//init department categories
			for (DepCategory depcategory : depCategoryTestGenerator.iniListAndReturn()) {
				depCategoryService.save(depcategory);
			}
			//set essential data initialized
			isEssentailDataInit = true;
		}
		
	}
	
	public void initDbDemo() {
		if (isDemoDataInit == false) {
			//init demo devices
			for (Device device : deviceDemoGenerator.iniListAndReturn()) {
				deviceService.save(device);
			}
			;
			//init demo hospitals
			for (Hospital hospital : hospitalTestGenerator.iniListAndReturn()) {
				hospitalService.save(hospital);
			}
			//set demo data initialized
			isDemoDataInit = true;
		}
	}
	
}
