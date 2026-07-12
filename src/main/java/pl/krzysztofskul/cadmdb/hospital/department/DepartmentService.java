package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom.DataArchRoom;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomDefaultGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomServiceEquipment;

@Service
public class DepartmentService {

	private DepartmentRepo departmentRepo;
	private RoomService roomService;
	private RoomServiceEquipment roomServiceEquipment;
	private RoomDefaultGenerator roomDefaultGenerator;

	/**
	 * @param departmentRepo
	 */
	@Autowired
	public DepartmentService(
			DepartmentRepo departmentRepo
			, RoomService roomService
			, RoomServiceEquipment roomServiceEquipment
			, RoomDefaultGenerator roomDefaultGenerator
			) {
		super();
		this.departmentRepo = departmentRepo;
		this.roomService = roomService;
		this.roomServiceEquipment = roomServiceEquipment;
		this.roomDefaultGenerator = roomDefaultGenerator;
	}
	
	public DepartmentService() {
		// TODO Auto-generated constructor stub
	}
	
	public void save(Department department) {
		departmentRepo.save(department);
	}
	
	public Department saveAndReturn(Department department) {
		return departmentRepo.save(department);
	}
	
	public List<Department> loadAll() {
		return departmentRepo.findAll();
	}
	
	public List<Department> loadAllByHospitalId(Long hospitalId) {
		return departmentRepo.findAllByHospitalId(hospitalId);
	}
	
	public Department loadById(Long id) {
		return departmentRepo.findById(id).get();
	}

	public Department loadByIdWithRoomList(Long id) {
		Department department = this.loadById(id);
		Hibernate.initialize(department.getRoomList());
		return department;
	}
	public Department loadByIdWithDataArchDepartment(Long id) {
		Department department = this.loadById(id);
		Hibernate.initialize(department.getDataArchDepartment());
		return department;
	}	
	public void deleteById(Long id) {
		departmentRepo.deleteById(id);
	}





}
