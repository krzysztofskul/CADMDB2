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

import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService;

@Controller
@RequestMapping("/rooms/architectural-data")
public class DataArchRoomController {

	private RoomService roomService;
	private NameStandardizedService nameStandardizedService;
	
	/**
	 * Constructor
	 * @param roomService
	 */
	@Autowired
	public DataArchRoomController(RoomService roomService, NameStandardizedService nameStandardizedService) {
		super();
		this.roomService = roomService;
		this.nameStandardizedService = nameStandardizedService;
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
		roomDB.setNameStandardized(room.getNameStandardized());
		roomDB.setName(room.getName());
		roomDB.setRoomNo(room.getRoomNo());
		roomDB.setDataArchRoom(room.getDataArchRoom());
		roomDB = roomService.saveAndReturn(roomDB);
		return "redirect:/rooms/architectural-data/"+id;
	}
	
}
