package pl.krzysztofskul.cadmdb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.company.manufacturer.Manufacturer;
import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerService;
import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerTestGenerator;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalDemoGenerator;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.HospitalTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentDemoGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentService;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardizedTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomDefaultGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomServiceEquipment;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomTestGenerator;
import pl.krzysztofskul.cadmdb.product.Product;
import pl.krzysztofskul.cadmdb.product.ProductDefaultGenerator;
import pl.krzysztofskul.cadmdb.product.ProductService;
import pl.krzysztofskul.cadmdb.product.ProductTestGenerator;
import pl.krzysztofskul.cadmdb.product.category.Category;
import pl.krzysztofskul.cadmdb.product.category.CategoryGenerator;
import pl.krzysztofskul.cadmdb.product.category.CategoryService;
import pl.krzysztofskul.cadmdb.random.Random;
import pl.krzysztofskul.cadmdb.user.UserService;


@Service
public class HomeService {

	private UserService userService;
	private HealthcareFacilityService healthcareFacilityService;
	private HospitalTestGenerator hospitalTestGenerator;
	private HospitalDemoGenerator hospitalDemoGenerator;
	private DepartmentTestGenerator departmentTestGenerator;
	private DepartmentDemoGenerator departmentDemoGenerator;
	private RoomTestGenerator roomTestGenerator;
	private RoomServiceEquipment roomServiceEquipment;
	private HospitalService hospitalService;
	private DepartmentService departmentService;
	private RoomService roomService;
	private RoomDefaultGenerator roomDefaultGenerator;
	private NameStandardizedService department_nameStandardizedService;
	private NameStandardizedTestGenerator department_nameStandardizedTestGenerator;
	private pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService room_nameStandardizedService;
	private pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedTestGenerator room_nameStandardizedTestGenerator;
	private CategoryService categoryService;
	private CategoryGenerator categoryGenerator;
	private ProductTestGenerator productTestGenerator;
	private ProductService productService;
	private ProductDefaultGenerator productDefaultGenerator;
	private ManufacturerTestGenerator manufacturerTestGenerator;
	private ManufacturerService manufacturerService;
	
	private boolean isEssentailDataInit = false;
	private boolean isTestDataInit = false;
	private boolean isDemoDataInit = false;
	
	/**
	 * Constructor
	 */
	@Autowired
	public HomeService(UserService userService, HealthcareFacilityService healthcareFacilityService, HospitalTestGenerator hospitalTestGenerator, HospitalDemoGenerator hospitalDemoGenerator, HospitalService hospitalService,
			NameStandardizedService department_nameStandardizedService, NameStandardizedTestGenerator department_nameStandardizedTestGenerator,
			pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService room_nameStandardizedService, pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedTestGenerator room_nameStandardizedTestGenerator,
			CategoryService categoryService, CategoryGenerator categoryGenerator, ProductTestGenerator productTestGenerator, ProductService productService, ProductDefaultGenerator productDefaultGenerator, ManufacturerTestGenerator manufacturerTestGenerator, ManufacturerService manufacturerService,
			DepartmentTestGenerator departmentTestGenerator, 
			DepartmentDemoGenerator departmentDemoGenerator,
			DepartmentService departmentService, 
			RoomTestGenerator roomTestGenerator, RoomService roomService, RoomDefaultGenerator roomDefaultGenerator, RoomServiceEquipment roomServiceEquipment) {
		super();
		this.userService = userService;
		this.healthcareFacilityService = healthcareFacilityService;
		this.hospitalTestGenerator = hospitalTestGenerator;
		this.hospitalDemoGenerator = hospitalDemoGenerator;
		this.departmentTestGenerator = departmentTestGenerator;
		this.departmentDemoGenerator = departmentDemoGenerator;
		this.roomTestGenerator = roomTestGenerator;
		this.hospitalService = hospitalService;
		this.departmentService = departmentService;
		this.roomService = roomService;
		this.roomDefaultGenerator = roomDefaultGenerator;
		this.department_nameStandardizedService = department_nameStandardizedService;
		this.department_nameStandardizedTestGenerator = department_nameStandardizedTestGenerator;
		this.room_nameStandardizedService = room_nameStandardizedService;
		this.room_nameStandardizedTestGenerator = room_nameStandardizedTestGenerator;
		this.roomServiceEquipment = roomServiceEquipment;
		this.categoryService = categoryService;
		this.categoryGenerator = categoryGenerator;
		this.productTestGenerator = productTestGenerator;
		this.productService = productService;
		this.productDefaultGenerator = productDefaultGenerator;
		this.manufacturerTestGenerator = manufacturerTestGenerator;
		this.manufacturerService = manufacturerService;
	}

	public void initialDbEssentials() {
		if (isEssentailDataInit == false) {
			//init default manufacturer
			createDemoManufacturers();		
			//init product categories
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
			//init default products/equipment
			for (Product product : productDefaultGenerator.initListAndReturn()) {
				productService.saveAndReturn(product);
			}
			//init default rooms
			for (Room room : roomDefaultGenerator.initListAndReturn()) {
				roomService.saveAndReturn(room);
			}
			//set essential data initialized
			isEssentailDataInit = true;
		}
		
	}
	
	public void initDbTest(String type) {
		if (isEssentailDataInit == true) {
			if (isTestDataInit == false & isDemoDataInit == false) {
				//init test users
				userService.createAndSaveTestUsers();
				//init test manufacturers
				List<Manufacturer> manufacturerList = manufacturerTestGenerator.initListAndReturn();
				for (Manufacturer manufacturer : manufacturerList) {
					manufacturerService.save(manufacturer);
				}
				//init test products
				List<Product> productList = new ArrayList<Product>();
				if (type == "test"){
					productList = productTestGenerator.initListAndReturn();
				}
				for (Product product : productList) {
					productService.save(product);
				}
				;
				//init test hospitals
				if (type == "test") {
					for (Hospital hospital : hospitalTestGenerator.initListAndReturn()) {
						hospitalService.save(hospital);
					}					
				}
				//init and add test departments to hospitals
				if (type == "test") {
					for (Hospital hospital : hospitalService.loadAll()) {
						List<Department> departmentList = null;
						if (type == "demo") {
							departmentList = departmentTestGenerator.initDemoListAndReturn();	
						}else if (type == "test") {
							departmentList = departmentTestGenerator.initListAndReturn();
						}else {
							departmentList = departmentTestGenerator.initListAndReturn();
						}
						for (Department department : departmentList) {
							hospital.addDepartment(department);
							hospitalService.save(hospital);
						}
					}
				}
				if (type == "test") {
				List<Room> roomList = roomTestGenerator.initListAndReturn();
				List<Department> departmentList = departmentService.loadAll();
					for (Department department : departmentList) {
						if (department.getNameStandardized().getCode().equals("X-BO")) {
							department.addRoom(roomService.loadByNameStandardizedCode("X-SO"));
							department.addRoom(roomService.loadByNameStandardizedCode("X-PL"));
							department.addRoom(roomService.loadByNameStandardizedCode("X-PP"));
						}
					}
				}		
				
				//init and add test equipment to rooms
				if (type == "test") {
					for (Room room : roomService.loadAll()) {
						for (int i = 0; i < Random.randomInt(2, 3); i++) {
							room = healthcareFacilityService.addProductToRoom(productService.loadRandom(), room);
						}
						roomService.saveAndReturn(room);
					}
				}
				
				//init demo db
				if (type == "demo") {
					this.initDbDemo();
				}
				
				//set test data initialized
				isTestDataInit = true;
				//set demo data initialized
				isDemoDataInit = true;
			}
		}
	}

	/*
	 * Creates demo hospitals and save.
	 * For each hospital creates demo departments, save and add to the hospital.
	 * For each department creates demo rooms, save and add to the department.
	 * For each room creates demo products, save and add to the room. 
	 */
	private void initDbDemo() {
		
		/*
		 * Create and iterate demo hospitals
		 */
		for (Hospital hospital : hospitalDemoGenerator.initDemoListAndReturn()) {			
			/*
			 * Save demo hospitals
			 */
			hospital = hospitalService.saveAndReturn(hospital);
			/*
			 * Create demo departments
			 */
			List<Department> departmentList = departmentDemoGenerator.initListAndReturn();
			/*
			 * Iterate demo departments
			 */
			for (Department department : departmentList) {
				/*
				 * Add department to the hospital
				 */
				hospital = healthcareFacilityService.addDeprtmentToHospital(department, hospital);
				/*
				 * Save hospital with departments
				 */
				hospital = hospitalService.saveAndReturn(hospital);
				/*
				 * Create default rooms for department 
				 */
				department = healthcareFacilityService.addDefaultRooms(department);
				/*
				 * Save department with demo rooms
				 */
				department = departmentService.saveAndReturn(department);

				/*
				 * Iterate room
				 */
				for (Room room : department.getRoomList()) {
					/*
					 * Equip room with default products
					 */
					room = healthcareFacilityService.equipRoomByDefaultProducts(room);
					room = roomService.saveAndReturn(room);
				}
	
			}		
		}
	}

	//TODO: 2026-07-11 1244
	private void createDemoManufacturers() {
		Manufacturer demoManufacturer = new Manufacturer("-", new Address("-", "-", "-", "-", "-", "-"), "-");
		demoManufacturer.setDefault(true);
		manufacturerService.save(demoManufacturer);
	}
	
}
