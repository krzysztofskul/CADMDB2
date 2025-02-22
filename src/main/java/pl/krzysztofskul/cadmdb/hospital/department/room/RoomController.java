package pl.krzysztofskul.cadmdb.hospital.department.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rooms")
public class RoomController {

	private RoomService roomService;
	private ModelAndView mav = new ModelAndView();
	
	/**
	 * @param roomService
	 */
	@Autowired
	public RoomController(
			RoomService roomService
			) {
		super();
		this.roomService = roomService;
	}
	
	@GetMapping("/{id}")
	public ModelAndView getRoomById(@PathVariable Long id) {
		roomService.loadById(id);
		mav.addObject("room", roomService.loadById(id));
		mav.setViewName("hospital/department/room/id");
		return mav;
	}
	
	@GetMapping("/{id}/equipment")
	public ModelAndView getRoomByIdWithEquipment(@PathVariable Long id) {
		roomService.loadByIdWithEquipment(id);
		return mav;
	}
	
}
