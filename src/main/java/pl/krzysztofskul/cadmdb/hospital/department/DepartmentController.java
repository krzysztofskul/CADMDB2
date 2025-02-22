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

import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategoryService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

	private DepartmentService departmentService;
	private RoomCategoryService roomCategoryService;
	private ModelAndView mav = new ModelAndView();

	/**
	 * @param departmentService
	 */
	@Autowired
	public DepartmentController(
			DepartmentService departmentService,
			RoomCategoryService roomCategoryService
			) {
		super();
		this.departmentService = departmentService;
		this.roomCategoryService = roomCategoryService;
	}
	
	@GetMapping("/{id}")
	public ModelAndView getDepartmentById(@PathVariable Long id) {
		Department department = departmentService.loadById(id);
		mav.addObject("department", department);
		mav.setViewName("hospital/department/id");
		return mav;
	}
	
	@GetMapping("/{id}/rooms")
	public ModelAndView getRoomsByDepartmentId(
			@PathVariable Long id,
			@RequestParam(required = false) boolean edit
			
			) {
		Department department = departmentService.loadByIdWithRoomList(id);
		mav.setViewName("hospital/department/idWithRooms");
		mav.addObject("department", department);
		
		if (edit == true) {
			mav.addObject("edit", true);
			mav.setViewName("hospital/department/idAddRoom");
			mav.addObject("roomCategoryList", roomCategoryService.loadAll());
			mav.addObject("room", new Room(departmentService.loadById(id)));
		}
		mav.addObject("edit", edit);
		return mav;
	}
	
	@PostMapping("/{departmentId}/rooms")
	public ModelAndView postAddRoomToDepartment(
				@RequestParam(required = false, name = "backToPage") String backToPage,
				@ModelAttribute Room room
			) {
		Department department = departmentService.loadById(room.getDepartment().getId());
		department.addRoom(room);
		departmentService.save(department);
		
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
		Long hospitalId = departmentService.loadById(id).getHospital().getId();
		departmentService.depeteById(id);
		mav.setViewName("redirect:/hospitals/"+hospitalId+"/departments");
		return mav;
	}
	
}
