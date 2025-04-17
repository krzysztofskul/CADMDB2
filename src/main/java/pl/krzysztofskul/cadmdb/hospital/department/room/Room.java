package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.device.Device;
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
	
	@ManyToMany
    @JoinTable(
            name = "rooms_devices",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
	private List<Device> deviceList = new ArrayList<Device>();
	
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

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}
	
	/**
	 * Method that add planned device to the room with cost of planned purchase calculation
	 */
	public void addDevice(Device device) {
		this.deviceList.add(device);
		this.getDataFinancial().setPurCostOfDevicePlan(this.getDataFinancial().getPurCostOfDevicePlan().add(device.getDataFinancial().getPrice()));
		this.getDepartment().getDataFinancial().setPurCostOfDevicePlan(this.getDepartment().getDataFinancial().getPurCostOfDevicePlan().add(device.getDataFinancial().getPrice()));
		this.getDepartment().getHospital().getDataFinancial().setPurCostOfDevicePlan(this.getDepartment().getHospital().getDataFinancial().getPurCostOfDevicePlan().add(device.getDataFinancial().getPrice()));
	}
	
	/**
	 * Method that remove planned device from the room with cost of planned purchase calculation
	 */
	public void removeDevice(Device device) {
		this.deviceList.remove(device);
		this.getDataFinancial().setPurCostOfDevicePlan(this.getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
		this.getDepartment().getDataFinancial().setPurCostOfDevicePlan(this.getDepartment().getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
		this.getDepartment().getHospital().getDataFinancial().setPurCostOfDevicePlan(this.getDepartment().getHospital().getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
	}
}
