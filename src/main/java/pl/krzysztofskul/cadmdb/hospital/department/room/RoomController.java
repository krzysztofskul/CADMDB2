package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.hospital.department.Department;

@Controller
@RequestMapping("/rooms")
public class RoomController {

	private HealthcareFacilityService hfService;
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
			, HealthcareFacilityService hfCalcService
			) {
		super();
		this.roomService = roomService;
		this.deviceService = deviceService;
		this.hfService = hfCalcService;
	}
	
	@GetMapping("/{id}")
	public ModelAndView getRoomById(
			@PathVariable Long id
			, @RequestParam(name = "edit", required = false) boolean edit
			) {
		mav.clear();
		if (edit == true) {
			mav.addObject("edit", true);
			mav.addObject("functionEnumList", FunctionEnum.values());
		}
		mav.addObject("room", roomService.loadById(id));
		mav.setViewName("hospital/department/room/id");
		return mav;
	}
	
	@PostMapping("/{id}/savedetails")
	public ModelAndView postRoomByIdSaveDetails(
			@PathVariable Long id,
			@ModelAttribute Room room
			) {
		mav.clear();
		
		Room roomDB = roomService.loadById(id);
		roomDB.setName(room.getName());
		roomDB.setNamePL(room.getNamePL());
		roomDB.setNameEN(room.getNameEN());
		roomDB.setFunctionEnum(room.getFunctionEnum());
		roomDB.setAddress(room.getAddress());
		
		roomDB = roomService.saveAndReturn(room);
		mav.setViewName("redirect:/rooms/"+roomDB.getId());
		return mav;
	}

	@PostMapping("/{id}")
	public ModelAndView postRoomById(
				@PathVariable Long id,
				@ModelAttribute Room room
			) {
		mav.clear();
		room = roomService.saveAndReturn(room);
		mav.setViewName("redirect:/rooms/"+room.getId());
		return mav;
	}
	
	@GetMapping("/{id}/equipment")
	public ModelAndView getRoomByIdWithEquipment(
			@PathVariable Long id
			, @RequestParam(required = false) boolean edit
			) {
		mav = new ModelAndView();
		if (edit == true) {
			mav.addObject("edit", true);
			mav.addObject("room", roomService.loadById(id));
			mav.addObject("deviceList", deviceService.LoadAllActive());
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
		mav = new ModelAndView();
		Room room = roomService.loadByIdWithEquipment(Long.valueOf(roomId));
		Device device = deviceService.loadById(Long.valueOf(deviceId));
//		room.addDevice(device);
		room = hfService.addDeviceToRoom(device, room);
		room = roomService.saveAndReturn(room);
		mav.addObject("edit", false);
		mav.setViewName("redirect:/rooms/"+room.getId()+"/equipment");
		return mav;
	}
	
	@GetMapping("/{id}/equipment/{deviceId}/remove")
	public ModelAndView getRemoveEquipmentFromRoom(
			@PathVariable(name ="id") Long roomId,
			@PathVariable(name="deviceId") Long deviceId
			) {
		Room room = roomService.loadByIdWithEquipment(roomId);
		Device device = deviceService.loadById(deviceId);
//		room.removeDevice(device);
		room = hfService.removeDeviceFromRoom(device, room);
		room = roomService.saveAndReturn(room);
		mav.addObject("edit", false);
		mav.setViewName("redirect:/rooms/"+room.getId()+"/equipment");
		return mav;
	}
	
	@GetMapping("/{id}/delete")
	public ModelAndView deleteRoomById(
				@PathVariable Long id
			) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/departments/"+roomService.loadById(id).getDepartment().getId()+"/rooms");
		hfService.removeRoomByIdWithEquipment(id);	
		return mav;
	}
	
}
