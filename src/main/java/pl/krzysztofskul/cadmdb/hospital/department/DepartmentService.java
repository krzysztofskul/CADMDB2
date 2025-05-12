package pl.krzysztofskul.cadmdb.hospital.department;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.hospital.department.room.Room;

@Service
public class DepartmentService {

	private DepartmentRepo departmentRepo;

	/**
	 * @param departmentRepo
	 */
	@Autowired
	public DepartmentService(DepartmentRepo departmentRepo) {
		super();
		this.departmentRepo = departmentRepo;
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
	public void depeteById(Long id) {
		departmentRepo.deleteById(id);
	}

	/**
	 * Adds a new room to the specified department and updates the department’s total area.
	 * <p>
	 * This method performs two steps:
	 * <ol>
	 *   <li>Attaches the given {@code room} to the {@code department} via {@code department.addRoom(room)}.</li>
	 *   <li>Retrieves the room’s area from its {@code DataArchRoom} and adds it to the department’s current
	 *       total area in {@code DataArchDepartment}.</li>
	 * </ol>
	 *
	 * @param department the department to which the room will be added
	 * @param room       the room whose area should contribute to the department’s total
	 * @return the same {@code Department} instance, now containing the new room and updated total area
	 */
	public Department addRoom(Department department, Room room) {
		department.addRoom(room);
		department.getDataArchDepartment().setAreaTotal(
					Float.sum(department.getDataArchDepartment().getAreaTotal(), room.getDataArchRoom().getArea())
				);
		return department;
		
	}
	
	/**
	 * Removes a room from the given department and updates the department’s financial
	 * and architectural totals accordingly.
	 * <p>
	 * This method performs three steps:
	 * <ol>
	 *   <li>Subtracts the room’s planned device purchase cost from the department’s
	 *       {@code DataFinancial.purCostOfDevicePlan}.</li>
	 *   <li>Decreases the department’s {@code DataArchDepartment.areaTotal} by the
	 *       area value stored in the room’s {@code DataArchRoom}.</li>
	 *   <li>Detaches the {@code room} from the department’s room list.</li>
	 * </ol>
	 *
	 * @param department the Department from which the room will be removed
	 * @param room       the Room whose cost and area will be deducted and then removed
	 * @return the same Department instance, now without the specified room and with
	 *         updated financial and area totals
	 */
	public Department removeRoom(Department department, Room room) {
		department.getDataFinancial().setPurCostOfDevicePlan(
					department.getDataFinancial().getPurCostOfDevicePlan().subtract(room.getDataFinancial().getPurCostOfDevicePlan())
				);
		department.getDataArchDepartment().setAreaTotal(
					Float.sum(department.getDataArchDepartment().getAreaTotal(), -room.getDataArchRoom().getArea())
				);
		
		department.removeRoom(room);
		return department;
		
	}
}
