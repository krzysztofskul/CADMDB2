package pl.krzysztofskul.cadmdb.company.manufacturer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.company.Company;
import pl.krzysztofskul.cadmdb.device.Device;

@Entity
public class Manufacturer extends Company {

	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
	private List<Device> deviceList = new ArrayList<Device>();

	/**
	 * Constructor
	 */
	public Manufacturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param name
	 * @param address
	 * @param contactDetails
	 */
	public Manufacturer(String name, Address address, String contactDetails) {
		super(name, address, contactDetails);
		// TODO Auto-generated constructor stub
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
	
	public void addDevice(Device device) {
		this.deviceList.add(device);
		device.setManufacturer(this);
	}
	
	public void removeDevice(Device device) {
		this.deviceList.remove(device);
		device.setManufacturer(null);
	}
	
}
