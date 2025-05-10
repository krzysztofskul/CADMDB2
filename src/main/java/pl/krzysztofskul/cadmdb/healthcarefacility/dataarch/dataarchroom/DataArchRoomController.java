package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;

@Controller
@RequestMapping("/rooms/architectural-data")
public class DataArchRoomController {

	private RoomService roomService;
	
	/**
	 * Constructor
	 * @param roomService
	 */
	@Autowired
	public DataArchRoomController(RoomService roomService) {
		super();
		this.roomService = roomService;
	}

	@GetMapping("/{id}")
	public String getRoomsArchitecturalData(
				@PathVariable Long id,
				Model model
			) {
		model.addAttribute("room", roomService.loadByIdWithDataArchRoom(id));
		return "hospital/department/room/idWithDataArch";
	}

	
}
