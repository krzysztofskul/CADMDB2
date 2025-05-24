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

@Controller
@RequestMapping("/devices")
public class DeviceController {

	private DeviceService deviceService;
	
	/**
	 * Constructor
	 * @param deviceService
	 */
	@Autowired
	public DeviceController(DeviceService deviceService) {
		super();
		this.deviceService = deviceService;
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
		device = deviceService.saveAndReturn(device);
		return "redirect:/devices/"+device.getId();
	}
	
}
