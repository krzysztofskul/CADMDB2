package pl.krzysztofskul.cadmdb.healthcarefacility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentService;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;

@Service
public class HealthcareFacilityService {

	private RoomService roomService;
	private DepartmentService departmentService;
	private HospitalService hospitalService;

	/**
	 * Constructor
	 * @param roomService
	 * @param departmentService
	 */
	@Autowired
	public HealthcareFacilityService(
				RoomService roomService, 
				DepartmentService departmentService,
				HospitalService hospitalService
			) {
		super();
		this.roomService = roomService;
		this.departmentService = departmentService;
		this.hospitalService = hospitalService;
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
	    List<Device> deviceList = new ArrayList<>(room.getDeviceList());
	    for (Device device : deviceList) {
	        //room.removeDevice(device); // updates costs and removes from list
	    	room = this.removeDeviceFromRoom(device, room);
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

	public Room addDeviceToRoom (Device device, Room room) {
		room.addDevice(device);
		room.getDataFinancial().setPurCostOfDevicePlan(room.getDataFinancial().getPurCostOfDevicePlan().add(device.getDataFinancial().getPrice()));
		room.getDepartment().getDataFinancial().setPurCostOfDevicePlan(room.getDepartment().getDataFinancial().getPurCostOfDevicePlan().add(device.getDataFinancial().getPrice()));
		room.getDepartment().getHospital().getDataFinancial().setPurCostOfDevicePlan(room.getDepartment().getHospital().getDataFinancial().getPurCostOfDevicePlan().add(device.getDataFinancial().getPrice()));
		return room;
	}
	
	public Room removeDeviceFromRoom (Device device, Room room) {
		room.getDataFinancial().setPurCostOfDevicePlan(room.getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
		room.getDepartment().getDataFinancial().setPurCostOfDevicePlan(room.getDepartment().getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
		room.getDepartment().getHospital().getDataFinancial().setPurCostOfDevicePlan(room.getDepartment().getHospital().getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
		room.removeDevice(device);
		return room;
	}
	
}
