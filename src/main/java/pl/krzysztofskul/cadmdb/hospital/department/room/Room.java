package pl.krzysztofskul.cadmdb.hospital.department.room;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategory;

@Entity
public class Room extends HealthcareFacility {

	@ManyToOne
	private Department department;
	
	@ManyToOne
	private RoomCategory roomcategory;
	
	/**
	 * 
	 */
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param department
	 */
	public Room(Department department) {
		super();
		this.department = department;
	}



	/**
	 * @param name
	 * @param address
	 * @param contactdetails
	 */
	public Room(String name, Address address, String contactdetails) {
		super(name, address, contactdetails);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public Room(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param roomcategory
	 */
	public Room(RoomCategory roomcategory) {
		super();
		this.roomcategory = roomcategory;
	}

	/**
	 * @param department
	 * @param roomcategory
	 */
	public Room(Department department, RoomCategory roomcategory) {
		super();
		this.department = department;
		this.roomcategory = roomcategory;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public RoomCategory getDepcategory() {
		return roomcategory;
	}

	public void setRoomCategory(RoomCategory roomcategory) {
		this.roomcategory = roomcategory;
	}
	
	
	
}
