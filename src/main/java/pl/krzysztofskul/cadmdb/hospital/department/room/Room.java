package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom.DataArchRoom;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardized;

@Entity
public class Room extends HealthcareFacility {

	private String roomNo;
	
	@ManyToOne
	private Department department;
	
    @OneToOne(
            mappedBy    = "room",
            cascade     = CascadeType.ALL,
            orphanRemoval = true,
            fetch       = FetchType.LAZY
        )
    private DataArchRoom dataArchRoom;
	
	@ManyToOne
	private NameStandardized nameStandardized;
	
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
	 * @param nameStandardized
	 */
	public Room(NameStandardized nameStandardized) {
		super();
		this.nameStandardized = nameStandardized;
	}

	/**
	 * @param department
	 * @param nameStandardized
	 */
	public Room(Department department, NameStandardized nameStandardized) {
		super();
		this.department = department;
		this.nameStandardized = nameStandardized;
	}

	/**
	 * Getter
	 * @return the dataArchRoom
	 */
	public DataArchRoom getDataArchRoom() {
		return dataArchRoom;
	}

	/**
	 * Setter
	 * @param dataArchRoom the dataArchRoom to set
	 */
	public void setDataArchRoom(DataArchRoom dataArchRoom) {
		this.dataArchRoom = dataArchRoom;
		dataArchRoom.setRoom(this);
	}

	/**
	 * Getter
	 * @return the roomNo
	 */
	public String getRoomNo() {
		return roomNo;
	}

	/**
	 * Setter
	 * @param roomNo the roomNo to set
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	/**
	 * Getter
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Setter
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Getter
	 * @return the nameStandardized
	 */
	public NameStandardized getNameStandardized() {
		return nameStandardized;
	}

	/**
	 * Setter
	 * @param nameStandardized the nameStandardized to set
	 */
	public void setNameStandardized(NameStandardized nameStandardized) {
		this.nameStandardized = nameStandardized;
	}

	/**
	 * Getter
	 * @return the deviceList
	 */
	public List<Device> getDeviceList() {
		return deviceList;
	}

	/**
	 * Setter
	 * @param deviceList the deviceList to set
	 */
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
		this.getDataFinancial().setPurCostOfDevicePlan(this.getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
		this.getDepartment().getDataFinancial().setPurCostOfDevicePlan(this.getDepartment().getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
		this.getDepartment().getHospital().getDataFinancial().setPurCostOfDevicePlan(this.getDepartment().getHospital().getDataFinancial().getPurCostOfDevicePlan().subtract(device.getDataFinancial().getPrice()));
		this.deviceList.remove(device);
	}
}
