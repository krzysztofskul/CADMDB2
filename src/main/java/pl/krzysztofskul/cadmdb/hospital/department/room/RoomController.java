package pl.krzysztofskul.cadmdb.hospital.department.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

	private RoomService roomService;
	private DeviceService deviceService;
	private ModelAndView mav = new ModelAndView();
	
	/**
	 * @param roomService
	 */
	@Autowired
	public RoomController(
			RoomService roomService
			, DeviceService deviceService
			) {
		super();
		this.roomService = roomService;
		this.deviceService = deviceService;
	}
	
	@GetMapping("/{id}")
	public ModelAndView getRoomById(@PathVariable Long id) {
		roomService.loadById(id);
		mav.addObject("room", roomService.loadById(id));
		mav.setViewName("hospital/department/room/id");
		return mav;
	}
	
	@GetMapping("/{id}/equipment")
	public ModelAndView getRoomByIdWithEquipment(
			@PathVariable Long id
			, @RequestParam(required = false) boolean edit
			) {
		
		if (edit == true) {
			mav.addObject("edit", true);
			mav.addObject("room", roomService.loadById(id));
			mav.addObject("deviceList", deviceService.loadAll());
			mav.setViewName("hospital/department/room/idAddEquipment");
			
		} else {
			mav.addObject("room", roomService.loadByIdWithEquipment(id));
			mav.setViewName("hospital/department/room/idWithEquipment");			
		}
		return mav;
	}
	
	@PostMapping("/{id}/equipment")
	public ModelAndView postEquipmentToRoom(
			@RequestParam String roomId,
			@RequestParam String deviceId
			) {
		Room room = roomService.loadByIdWithEquipment(Long.valueOf(roomId));
		Device device = deviceService.loadById(Long.valueOf(deviceId));
		room.addDevice(device);
		room = roomService.saveAndReturn(room);
		mav.addObject("edit", false);
		mav.setViewName("redirect:/rooms/"+room.getId()+"/equipment");
		
		return mav;
	}
	
}
