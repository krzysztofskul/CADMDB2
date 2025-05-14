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

	public void removeRoomByIdWithEquipment(Long roomId) {
		Room room = roomService.loadById(roomId);	
	    List<Device> deviceList = new ArrayList<>(room.getDeviceList());
	    for (Device device : deviceList) {
	        room.removeDevice(device); // updates costs and removes from list
	    }
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
}
