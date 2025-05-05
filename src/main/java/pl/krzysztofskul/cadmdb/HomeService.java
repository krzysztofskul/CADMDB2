package pl.krzysztofskul.cadmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.company.manufacturer.Manufacturer;
import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerService;
import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerTestGenerator;
import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceDemoGenerator;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.device.category.Category;
import pl.krzysztofskul.cadmdb.device.category.CategoryGenerator;
import pl.krzysztofskul.cadmdb.device.category.CategoryService;
import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.HospitalTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedTestGenerator;


@Service
public class HomeService {

	private HospitalTestGenerator hospitalTestGenerator;
	private HospitalService hospitalService;
	private NameStandardizedService department_nameStandardizedService;
	private NameStandardizedTestGenerator department_nameStandardizedTestGenerator;
	private pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService room_nameStandardizedService;
	private pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedTestGenerator room_nameStandardizedTestGenerator;
	private CategoryService categoryService;
	private CategoryGenerator categoryGenerator;
	private DeviceDemoGenerator deviceDemoGenerator;
	private DeviceService deviceService;
	private ManufacturerTestGenerator manufacturerTestGenerator;
	private ManufacturerService manufacturerService;
	
	private boolean isEssentailDataInit = false;
	private boolean isDemoDataInit = false;
	
	/**
	 * Constructor
	 */
	@Autowired
	public HomeService(HospitalTestGenerator hospitalTestGenerator, HospitalService hospitalService,
			NameStandardizedService department_nameStandardizedService, NameStandardizedTestGenerator department_nameStandardizedTestGenerator,
			pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService room_nameStandardizedService, pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedTestGenerator room_nameStandardizedTestGenerator,
			CategoryService categoryService, CategoryGenerator categoryGenerator, DeviceDemoGenerator deviceDemoGenerator, DeviceService deviceService, ManufacturerTestGenerator manufacturerTestGenerator, ManufacturerService manufacturerService) {
		super();
		this.hospitalTestGenerator = hospitalTestGenerator;
		this.hospitalService = hospitalService;
		this.department_nameStandardizedService = department_nameStandardizedService;
		this.department_nameStandardizedTestGenerator = department_nameStandardizedTestGenerator;
		this.room_nameStandardizedService = room_nameStandardizedService;
		this.room_nameStandardizedTestGenerator = room_nameStandardizedTestGenerator;
		this.categoryService = categoryService;
		this.categoryGenerator = categoryGenerator;
		this.deviceDemoGenerator = deviceDemoGenerator;
		this.deviceService = deviceService;
		this.manufacturerTestGenerator = manufacturerTestGenerator;
		this.manufacturerService = manufacturerService;
	}

	public void initialDbEssentials() {
		if (isEssentailDataInit == false) {
			//init device categories
			for (Category category : categoryGenerator.iniListAndReturn()) {
				categoryService.save(category);
			}
			//init room categories
			for (pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardized roomcategory : room_nameStandardizedTestGenerator.iniListAndReturn()) {
				room_nameStandardizedService.save(roomcategory);
			}
			//init department categories
			for (NameStandardized depcategory : department_nameStandardizedTestGenerator.iniListAndReturn()) {
				department_nameStandardizedService.save(depcategory);
			}
			//set essential data initialized
			isEssentailDataInit = true;
		}
		
	}
	
	public void initDbDemo() {
		if (isDemoDataInit == false) {
			//init demo manufcturers
			for (Manufacturer manufacturer : manufacturerTestGenerator.iniListAndReturn()) {
				manufacturerService.save(manufacturer);
			}
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
