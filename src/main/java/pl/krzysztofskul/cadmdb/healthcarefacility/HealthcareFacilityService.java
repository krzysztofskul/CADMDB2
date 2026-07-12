package pl.krzysztofskul.cadmdb.healthcarefacility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom.DataArchRoom;
import pl.krzysztofskul.cadmdb.healthcarefacility.datafinancial.DataFinancial;
import pl.krzysztofskul.cadmdb.healthcarefacility.simulation.Simulation;
import pl.krzysztofskul.cadmdb.healthcarefacility.simulation.SimulationService;
import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentService;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;
import pl.krzysztofskul.cadmdb.product.Product;
import pl.krzysztofskul.cadmdb.product.ProductService;
import pl.krzysztofskul.cadmdb.product.category.Category;
import pl.krzysztofskul.cadmdb.product.category.CategoryService;

@Service
public class HealthcareFacilityService {

	private RoomService roomService;
	private DepartmentService departmentService;
	private HospitalService hospitalService;
	private SimulationService simulationService;
	private CategoryService categoryService;
	private ProductService productService;

	/**
	 * Constructor
	 * @param roomService
	 * @param departmentService
	 */
	@Autowired
	public HealthcareFacilityService(
				RoomService roomService, 
				DepartmentService departmentService,
				HospitalService hospitalService,
				SimulationService simulationService,
				CategoryService categoryService,
				ProductService productService
			) {
		super();
		this.roomService = roomService;
		this.departmentService = departmentService;
		this.hospitalService = hospitalService;
		this.simulationService = simulationService;
		this.categoryService = categoryService;
		this.productService = productService;
	}

	public Department addRoomToDepartment(Room room, Department department) {
		//add room to department
		department.addRoom(room);
		//calculate area in department with recalculation of the area in the hospital	
		calculateDepartmentArea(department, room, true);
		//calculate costs in department (?)
		//save department with hospital
		departmentService.save(department);
		return department;
	}
	
	public Hospital addDeprtmentToHospital (Department department, Hospital hospital) {
		return hospitalService.addDepartment(hospital, department);
	}
	
	public Department calculateDepartmentArea(Department department, Room room, boolean trueForAddfalseForSubtract) {
		long x = 1;
		if (trueForAddfalseForSubtract == false) {
			x = -1;
		}
		department.getDataArchDepartment().setAreaTotal(
					department.getDataArchDepartment().getAreaTotal() + (x*room.getDataArchRoom().getArea())
				);
		//recalculate area in hospital
		if (department.getHospital() != null) {
		department.getHospital().getDataArchHospital().setAreaTotal(
					department.getHospital().getDataArchHospital().getAreaTotal() + (x*room.getDataArchRoom().getArea())
				);
		}
		return department;
	}
	
	public void removeRoomByIdWithEquipment(Long roomId) {
		Room room = roomService.loadById(roomId);	
	    List<Product> productList = new ArrayList<>(room.getProductList());
	    for (Product product : productList) {
	    	room = this.removeProductFromRoom(product, room);
	    }
	    //calculate area in the department and hospital
	    Department department = room.getDepartment();
	    department = this.calculateDepartmentArea(department, room, false);
		//save room
	    room = roomService.saveAndReturn(room);
		roomService.deleteById(roomId);
	};	
	
	public void removeDepartmentByIdWithRooms(Long departmentId) {
		Department department = departmentService.loadByIdWithRoomList(departmentId);
		List<Room> roomList = new ArrayList<Room>(department.getRoomList());
		for (Room room : roomList) {
			this.removeRoomByIdWithEquipment(room.getId());
		}
		department = departmentService.saveAndReturn(department);
		departmentService.deleteById(departmentId);
	}
	
	public void removeHospitalByIdWithDepartments(Long hospitalId) {
		Hospital hospital = hospitalService.loadByIdWithDepartments(hospitalId);
		List<Department> departmentList = new ArrayList<Department>(hospital.getDepartmentList());
		for (Department department : departmentList) {
			this.removeDepartmentByIdWithRooms(department.getId());
		}
		hospital = hospitalService.saveAndReturn(hospital);
		hospitalService.deleteById(hospitalId);
	}

	public Room addProductToRoom (Product product, Room room) {
		room.addProduct(product);
		//calc pur cost in room
		room = calcCostInRoomWhenAddProduct(product, room);
		//calc pur cots in department
		room = calcCostInDepartmentWhenAddProductToRoom(product, room);
		//calc pur cost in hospital
		room = calcCostInHospitalWhenAddProductToRoom(product, room);
		return room;
	}
	
	/**
	 * Equips the specified room with the default equipment assigned
	 * to its standardized room category.
	 *
	 * @param room the room to equip
	 * @return the equipped room
	 */
	public Room equipRoomByDefaultProducts(Room room) {
		//equip operating theater
		if (room.getNameStandardized().getCode().equals("X-SO")) {
			Category category;
			
			//add patient table
			category = categoryService.loadByCategoryCode("AB10");
			//room.addProduct(productService.loadRandomByCategory(category));
			room = this.addProductToRoom(productService.loadRandomByCategory(category), room);			
			
			//add operating light
			category = categoryService.loadByCategoryCode("AC20");
			//room.addProduct(productService.loadRandomByCategory(category));
			room = this.addProductToRoom(productService.loadRandomByCategory(category), room);	
			
			//add anesthesia machine
			category = categoryService.loadByCategoryCode("AH10");
			//room.addProduct(productService.loadRandomByCategory(category));
			room = this.addProductToRoom(productService.loadRandomByCategory(category), room);	

		}
		//equip doctors' preparation room
		if (room.getNameStandardized().getCode().equals("X-PL")) {
			Category category;
			//add surgical scrub sink
			category = categoryService.loadByCategoryCode("JJ00");
			//room.addProduct(productService.loadRandomByCategory(category));	
			room = this.addProductToRoom(productService.loadRandomByCategory(category), room);	
		}
		//equip patents' preparation room
		if (room.getNameStandardized().getCode().equals("X-PP")) {
			Category category;
			//add clinical cabinets system
			category = categoryService.loadByCategoryCode("BY00");
			//room.addProduct(productService.loadRandomByCategory(category));
			room = this.addProductToRoom(productService.loadRandomByCategory(category), room);	
		}
		//equip patients' bed room
		if (room.getNameStandardized().getCode().equals("L-SCH")) {
			Category category;
			//add patient beds
			for (int i = 0; i < 3; i++) {
				category = categoryService.loadByCategoryCode("AA50");
				//room.addProduct(productService.loadRandomByCategory(category));	
				room = this.addProductToRoom(productService.loadRandomByCategory(category), room);	
			}
		}
		//equip isolation patient room
		if (room.getNameStandardized().getCode().equals("L-I")) {
			Category category;
			//add patient bed
			category = categoryService.loadByCategoryCode("AA50");
			//room.addProduct(productService.loadRandomByCategory(category));
			room = this.addProductToRoom(productService.loadRandomByCategory(category), room);	
		}
		//equip dirty linen room
		if (room.getNameStandardized().getCode().equals("H-BRUD")) {
			Category category;
			//add washer/disnifector for bedpans and urinals
			category = categoryService.loadByCategoryCode("JA03");
			//room.addProduct(productService.loadRandomByCategory(category));
			room = this.addProductToRoom(productService.loadRandomByCategory(category), room);	
		}
		//return equipped room
		return room;
	}

	public Room removeProductFromRoom (Product product, Room room) {
		
		Simulation simulation = room.getDepartment().getHospital().getSimulation();
		
		room.getDataFinancial().setPurCostOfProductPlan(room.getDataFinancial().getPurCostOfProductPlan().subtract(product.getDataFinancial().getPrice()));
		//calc maintenance cost in room
		BigDecimal maintCostOfProductPlan = room.getDataFinancial().getMaintCostOfProductPlan();
		BigDecimal maintCostPerOneYear = product.getDataFinancial().getMaintenanceCost();
		BigDecimal maintCostOverYears = maintCostPerOneYear.multiply(BigDecimal.valueOf(simulation.getTime()));
	 	maintCostOfProductPlan = maintCostOfProductPlan.subtract(maintCostOverYears);
		room.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);			
		
		room.getDepartment().getDataFinancial().setPurCostOfProductPlan(room.getDepartment().getDataFinancial().getPurCostOfProductPlan().subtract(product.getDataFinancial().getPrice()));
		//calc maintenance cost in department when remove product to room
		Department department = room.getDepartment();
		maintCostOfProductPlan = department.getDataFinancial().getMaintCostOfProductPlan();
		maintCostPerOneYear = product.getDataFinancial().getMaintenanceCost();
		maintCostOverYears = maintCostPerOneYear.multiply(BigDecimal.valueOf(simulation.getTime()));
	 	maintCostOfProductPlan = maintCostOfProductPlan.subtract(maintCostOverYears);
		department.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);		
			
		room.getDepartment().getHospital().getDataFinancial().setPurCostOfProductPlan(room.getDepartment().getHospital().getDataFinancial().getPurCostOfProductPlan().subtract(product.getDataFinancial().getPrice()));
		//calc maintenance cost in hospital when remove product to room
		Hospital hospital = department.getHospital();
		maintCostOfProductPlan = hospital.getDataFinancial().getMaintCostOfProductPlan();
		maintCostPerOneYear = product.getDataFinancial().getMaintenanceCost();
		maintCostOverYears = maintCostPerOneYear.multiply(BigDecimal.valueOf(simulation.getTime()));
		maintCostOfProductPlan = maintCostOfProductPlan.subtract(maintCostOverYears);
		hospital.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);
		
		room.removeProduct(product);
		
		return room;
	}

	public Room calcCostInRoomWhenAddProduct(Product product, Room room) {
		Simulation simulation = room.getDepartment().getHospital().getSimulation();
		//calc pur cost in room
		room.getDataFinancial().setPurCostOfProductPlan(room.getDataFinancial().getPurCostOfProductPlan().add(product.getDataFinancial().getPrice()));
		//calc maintenance cost in room
		BigDecimal maintCostOfProductPlan = room.getDataFinancial().getMaintCostOfProductPlan();
		BigDecimal maintCostPerOneYear = product.getDataFinancial().getMaintenanceCost();
		BigDecimal maintCostOverYears = maintCostPerOneYear.multiply(BigDecimal.valueOf(simulation.getTime()));
	 	maintCostOfProductPlan = maintCostOfProductPlan.add(maintCostOverYears);
		room.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);	
		return room;
	}
	
	private Room calcCostInDepartmentWhenAddProductToRoom(Product product, Room room) {
		Simulation simulation = room.getDepartment().getHospital().getSimulation();
		Department department = room.getDepartment();
		//calc pur cost in department when add product to room
		department.getDataFinancial().setPurCostOfProductPlan(room.getDepartment().getDataFinancial().getPurCostOfProductPlan().add(product.getDataFinancial().getPrice()));
		//calc maintenance cost in department when add product to room
		BigDecimal maintCostOfProductPlan = department.getDataFinancial().getMaintCostOfProductPlan();
		BigDecimal maintCostPerOneYear = product.getDataFinancial().getMaintenanceCost();
		BigDecimal maintCostOverYears = maintCostPerOneYear.multiply(BigDecimal.valueOf(simulation.getTime()));
	 	maintCostOfProductPlan = maintCostOfProductPlan.add(maintCostOverYears);
		department.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);
		return room;
	}

	private Room calcCostInHospitalWhenAddProductToRoom(Product product, Room room) {
		Simulation simulation = room.getDepartment().getHospital().getSimulation();
		Hospital hospital = room.getDepartment().getHospital();
		//calc pur cost in hospital when add product to room
		hospital.getDataFinancial().setPurCostOfProductPlan(room.getDepartment().getHospital().getDataFinancial().getPurCostOfProductPlan().add(product.getDataFinancial().getPrice()));
		//calc maintenance cost in hospital when add product to room
		BigDecimal maintCostOfProductPlan = hospital.getDataFinancial().getMaintCostOfProductPlan();
		BigDecimal maintCostPerOneYear = product.getDataFinancial().getMaintenanceCost();
		BigDecimal maintCostOverYears = maintCostPerOneYear.multiply(BigDecimal.valueOf(simulation.getTime()));
		maintCostOfProductPlan = maintCostOfProductPlan.add(maintCostOverYears);
		hospital.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);
		return room;
	}
		
	public Hospital recalculateMaintenaceCostInHospital(Long hospitalId, Long simulationId) {
		Hospital hospital = hospitalService.loadByIdWithDepartments(hospitalId);
		BigDecimal maintCostOfProductPlan  = hospital.getDataFinancial().getMaintCostOfProductPlan();
		maintCostOfProductPlan = BigDecimal.ZERO;
		
		for (Department department : hospital.getDepartmentList()) {
			department = recalculateMaintenaceCostInDepartment(department.getId(), simulationId);
			maintCostOfProductPlan = maintCostOfProductPlan.add(department.getDataFinancial().getMaintCostOfProductPlan());
		}
		
		hospital.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);
		
		hospital = hospitalService.saveAndReturn(hospital);
		
		return hospital;
	}
	
	public Department recalculateMaintenaceCostInDepartment(Long departmentId, Long simulationId) {
		Department department = departmentService.loadByIdWithRoomList(departmentId);
		BigDecimal maintCostOfProductPlan  = department.getDataFinancial().getMaintCostOfProductPlan();
		maintCostOfProductPlan = BigDecimal.ZERO;
		
		for (Room room : department.getRoomList()) {
			room = recalculateMaintenanceCostInRoom(room.getId(), simulationId);
			maintCostOfProductPlan = maintCostOfProductPlan.add(room.getDataFinancial().getMaintCostOfProductPlan());
		}
		
		department.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);
	
		department = departmentService.saveAndReturn(department);
		
		return department;
	}
	
	public Room recalculateMaintenanceCostInRoom(Long roomId, Long simulationId) {
		// load room and simulation data
		Room room = roomService.loadByIdWithEquipment(roomId);
		Simulation simulation = simulationService.loadById(simulationId);
		//calculate maintenance costs over years
		BigDecimal maintCostOfProductPlan = room.getDataFinancial().getMaintCostOfProductPlan();
		maintCostOfProductPlan = BigDecimal.ZERO;
		for (Product product : room.getProductList()) {
			BigDecimal maintCostPerOneYear = product.getDataFinancial().getMaintenanceCost();
			BigDecimal maintCostOverYears = maintCostPerOneYear.multiply(BigDecimal.valueOf(simulation.getTime()));
			maintCostOfProductPlan = maintCostOfProductPlan.add(maintCostOverYears);
		}
		//update room data
		room.getDataFinancial().setMaintCostOfProductPlan(maintCostOfProductPlan);
		//save and return room with calculated maintenance costs
		room = roomService.saveAndReturn(room);
		return room;
	}
	
	public Department addDefaultRooms(Department department) {
		if (department.getNameStandardized().getCode().equals("X-BO")) {		
			this.addRoomToDepartment(getRoomFromDefault("X-SO"), department);
			this.addRoomToDepartment(getRoomFromDefault("X-PP"), department);
			this.addRoomToDepartment(getRoomFromDefault("X-PL"), department);
		}
		if (department.getNameStandardized().getCode().equals("L-KARD")) {
			this.addRoomToDepartment(getRoomFromDefault("L-SCH"), department);
			this.addRoomToDepartment(getRoomFromDefault("L-SCH"), department);
			this.addRoomToDepartment(getRoomFromDefault("L-SCH"), department);
			this.addRoomToDepartment(getRoomFromDefault("L-I"), department);
			this.addRoomToDepartment(getRoomFromDefault("H-SHS"), department);
			this.addRoomToDepartment(getRoomFromDefault("H-BRUD"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-POKL"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-SOC"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-POKP"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-SEKR"), department);
		}
		if (department.getNameStandardized().getCode().equals("A-ADM")) {
			this.addRoomToDepartment(getRoomFromDefault("A-SEKR"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-SOC"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-POKD"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-POKO"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-POKK"), department);
			this.addRoomToDepartment(getRoomFromDefault("A-POKPN"), department);
		}
		return department;
	}
	
	private Room getRoomFromDefault(String code) {
		Room roomDefault = roomService.loadDefaultByNameStandardizedCode(code);
		Room newRoom = new Room();
		
		newRoom.setDefault(false);
		newRoom.setNameStandardized(roomDefault.getNameStandardized());
		DataArchRoom newDataArchRoom = new DataArchRoom();
		newDataArchRoom.setArea(roomDefault.getDataArchRoom().getArea());
		newRoom.setDataArchRoom(newDataArchRoom);
		
		return newRoom;
	}
	
}
