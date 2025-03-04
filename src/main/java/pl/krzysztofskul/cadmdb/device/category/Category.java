package pl.krzysztofskul.cadmdb.device.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import pl.krzysztofskul.cadmdb.device.Device;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;

	private String namePL;
	private String nameEN;
	
	private String namePLplural;
	private String nameENplural;
	
	@OneToMany(mappedBy = "category")
	private List<Device> deviceList = new ArrayList<Device>();

	/**
	 * Constructor
	 */
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param code
	 * @param namePL
	 * @param nameEN
	 * @param namePLplural
	 * @param nameENplural
	 */
	public Category(String code, String namePL, String nameEN, String namePLplural, String nameENplural) {
		super();
		this.code = code;
		this.namePL = namePL;
		this.nameEN = nameEN;
		this.namePLplural = namePLplural;
		this.nameENplural = nameENplural;
	}

	/*
	 * Getters and setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamePL() {
		return namePL;
	}

	public void setNamePL(String namePL) {
		this.namePL = namePL;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNamePLplural() {
		return namePLplural;
	}

	public void setNamePLplural(String namePLplural) {
		this.namePLplural = namePLplural;
	}

	public String getNameENplural() {
		return nameENplural;
	}

	public void setNameENplural(String nameENplural) {
		this.nameENplural = nameENplural;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}
	
	/*
	 * Methods
	 */
	
	public void addDevice(Device device) {
		this.deviceList.add(device);
		device.setCategory(this);
	}
	
	public void removeDevice(Device device) {
		this.deviceList.remove(device);
		device.setCategory(null);
	}
}
