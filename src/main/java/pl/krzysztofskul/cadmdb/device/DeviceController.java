package pl.krzysztofskul.cadmdb.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.krzysztofskul.cadmdb.device.mounting.MountingTypeEnum;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;

@Controller
@RequestMapping("/devices")
public class DeviceController {

	private DeviceService deviceService;
	private RoomService roomService;
	private HealthcareFacilityService healthcareFacilityService;
	
	/**
	 * Constructor
	 * @param deviceService
	 */
	@Autowired
	public DeviceController(DeviceService deviceService, RoomService roomService, HealthcareFacilityService healthcareFacilityService) {
		super();
		this.deviceService = deviceService;
		this.roomService = roomService;
		this.healthcareFacilityService = healthcareFacilityService;
	}

	@GetMapping("{deviceId}")
	public String getDeviceById(
				@PathVariable Long deviceId
				, Model model
				, @RequestParam(name = "edit", required = false) String edit
			) {
		Device product = deviceService.loadById(deviceId);
		model.addAttribute("product", product);
		model.addAttribute("mountingTypeEnumList", MountingTypeEnum.values());
		model.addAttribute("functionEnumList", FunctionEnum.values());
		model.addAttribute("manufacturer", product.getManufacturer());
		if (edit == null) {
			edit = "false";
		}
		model.addAttribute("edit", edit);
		return "product/id";
	}
	
	@PostMapping("/{deviceId}")
	public String postDeviceById(
				@ModelAttribute Device device
			) {
		//Load all room where device is planned
		List<Room> roomList = roomService.loadAllByDeviceList_Id(device.getId());
 		//Remove old device from all rooms
		Device deviceDB = deviceService.loadById(device.getId());
		for (Room room : roomList) {
			healthcareFacilityService.removeDeviceFromRoom(deviceDB, room);
		}
		//Updated device in DB
		device = deviceService.saveAndReturn(device);
		//add device to all rooms and save rooms
		for (Room room : roomList) {
			room = healthcareFacilityService.addDeviceToRoom(device, room);
			roomService.saveAndReturn(room);
		}
		return "redirect:/devices/"+device.getId();
	}
	
}
