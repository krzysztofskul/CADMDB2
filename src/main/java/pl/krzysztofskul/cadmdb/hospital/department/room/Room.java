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

	private String roomNo;
	
	@ManyToOne
	private Department department;
	
	@ManyToOne
	private RoomCategory roomCategory;
	
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
	 * @param roomCategory
	 */
	public Room(RoomCategory roomCategory) {
		super();
		this.roomCategory = roomCategory;
	}

	/**
	 * @param department
	 * @param roomCategory
	 */
	public Room(Department department, RoomCategory roomCategory) {
		super();
		this.department = department;
		this.roomCategory = roomCategory;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public RoomCategory getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(RoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}
	
}
