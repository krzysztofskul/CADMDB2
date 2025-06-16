package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentService;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService;

@Controller
@RequestMapping("/rooms/architectural-data")
public class DataArchRoomController {

	private RoomService roomService;
	private NameStandardizedService nameStandardizedService;
	private HealthcareFacilityService healthcareFacilityService;
	private DepartmentService departmentService;
	
	/**
	 * Constructor
	 * @param roomService
	 */
	@Autowired
	public DataArchRoomController(RoomService roomService, NameStandardizedService nameStandardizedService, HealthcareFacilityService healthcareFacilityService, DepartmentService departmentService) {
		super();
		this.roomService = roomService;
		this.nameStandardizedService = nameStandardizedService;
		this.healthcareFacilityService = healthcareFacilityService;
		this.departmentService = departmentService;
	}

	@GetMapping("/{id}")
	public String getRoomsArchitecturalData(
				@RequestParam(name = "edit", required = false) boolean edit,
				@PathVariable Long id,
				Model model
			) {
		if (edit == true) {
			model.addAttribute("edit", true);
			model.addAttribute("nameStandardizedList", nameStandardizedService.loadAllByHospitalIdOrHospitalIsNull(id));
		}
		model.addAttribute("room", roomService.loadByIdWithDataArchRoom(id));
		return "hospital/department/room/idWithDataArch";
	}

	@PostMapping("/{id}")
	public String postRoomsArchitecturalData(
				@PathVariable Long id,
				@ModelAttribute Room room
			) {
		Room roomDB = roomService.loadByIdWithDataArchRoom(id);
		Department department = roomDB.getDepartment();
		department = healthcareFacilityService.calculateDepartmentArea(department, roomDB, false);
		department = healthcareFacilityService.calculateDepartmentArea(department, room, true);
		
		roomDB.setNameStandardized(room.getNameStandardized());
		roomDB.setName(room.getName());
		roomDB.setRoomNo(room.getRoomNo());
		roomDB.setDataArchRoom(room.getDataArchRoom());
		
		roomDB = roomService.saveAndReturn(roomDB);
		departmentService.save(department);
		return "redirect:/rooms/architectural-data/"+id;
	}
	
}
