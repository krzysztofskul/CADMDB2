package pl.krzysztofskul.cadmdb.hospital.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom.DataArchRoom;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

	private HealthcareFacilityService hfService;
	private DepartmentService departmentService;
	private NameStandardizedService nameStandardizedService;

	/**
	 * @param departmentService
	 */
	@Autowired
	public DepartmentController(
			DepartmentService departmentService,
			NameStandardizedService nameStandardizedService,
			HealthcareFacilityService hfService
			) {
		super();
		this.departmentService = departmentService;
		this.nameStandardizedService = nameStandardizedService;
		this.hfService = hfService;
	}
	
	@GetMapping("/{id}")
	public ModelAndView getDepartmentById(
			@PathVariable Long id
			, @RequestParam(name = "edit", required = false) boolean edit
			) {
		Department department = departmentService.loadById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("department", department);
		if (edit == true) {
			mav.addObject("edit", true);
		}
		mav.setViewName("hospital/department/id");
		return mav;
	}
	
	@PostMapping("/{id}")
	public ModelAndView postDepartmentById(
			@PathVariable Long id,	
			@ModelAttribute Department departmnet
			) {
		ModelAndView mav = new ModelAndView();
		departmentService.save(departmnet);
		mav.setViewName("redirect:/departments/"+departmnet.getId());
		return mav;
	}

	@GetMapping("/{id}/rooms")
	public ModelAndView getRoomsByDepartmentId(
			@PathVariable Long id,
			@RequestParam(required = false) boolean edit
			
			) {
		Department department = departmentService.loadByIdWithRoomList(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hospital/department/idWithRooms");
		mav.addObject("department", department);
		
		if (edit == true) {
			mav.addObject("edit", true);
			mav.setViewName("hospital/department/idAddRoom");
			mav.addObject("nameStandardizedList", nameStandardizedService.loadAllByHospitalIdOrHospitalIsNull(department.getHospital().getId()));
			Room room = new Room(department);
			DataArchRoom dataArchRoom = new DataArchRoom(room);
			room.setDataArchRoom(dataArchRoom);
			mav.addObject("room", room);
		}
		mav.addObject("edit", edit);
		return mav;
	}
	
	@PostMapping("/{id}/rooms")
	public ModelAndView postAddRoomToDepartment(
				@PathVariable(name = "id") Long departmentId,
				@RequestParam(required = false, name = "backToPage") String backToPage,
				@ModelAttribute Room room
			) {
		Department department = departmentService.loadByIdWithRoomList(departmentId);
		
		if (room.getAddress() == null) {
			room.setAddress(room.getDepartment().getAddress());
		} else {
			if (room.getAddress().getCountry() == null || room.getAddress().getCountry().length() == 0) {
				room.getAddress().setCountry(department.getAddress().getCountry());
			}
			if (room.getAddress().getPostalcode() == null || room.getAddress().getPostalcode().length() == 0) {
				room.getAddress().setPostalcode(department.getAddress().getPostalcode());
			}
			if (room.getAddress().getCity() == null || room.getAddress().getCity().length() == 0) {
				room.getAddress().setCity(department.getAddress().getCity());
			}
			if (room.getAddress().getStreetname() == null || room.getAddress().getStreetname().length() == 0) {
				room.getAddress().setStreetname(department.getAddress().getStreetname());
			}
			if (room.getAddress().getStreetno() == null || room.getAddress().getStreetno().length() == 0) {
				room.getAddress().setStreetno(department.getAddress().getStreetno());
			}
			if (room.getAddress().getFlatno() == null || room.getAddress().getFlatno().length() == 0) {
				room.getAddress().setFlatno(department.getAddress().getFlatno());
			}
		}
		//department = departmentService.addRoom(department, room);
		//departmentService.save(department);
		hfService.addRoomToDepartment(room, department);
		ModelAndView mav = new ModelAndView();
		if (backToPage == null) {
			mav.setViewName("redirect:/departments/"+department.getId()+"/rooms");
		} else {
			mav.setViewName(backToPage);
		}
		mav.addObject("edit", false);
		return mav;
	}
	
	@GetMapping("/{id}/delete")
	public ModelAndView departmentDeleteById(
				@PathVariable Long id
			) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/hospitals/"+departmentService.loadById(id).getHospital().getId()+"/departments");
		hfService.removeDepartmentByIdWithRooms(id);
		return mav;
	}
	
}
