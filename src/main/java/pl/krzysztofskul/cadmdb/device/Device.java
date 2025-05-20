package pl.krzysztofskul.cadmdb.device;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.company.manufacturer.Manufacturer;
import pl.krzysztofskul.cadmdb.device.category.Category;
import pl.krzysztofskul.cadmdb.device.datafinancial.DataFinancial;
import pl.krzysztofskul.cadmdb.device.mounting.MountingTypeEnum;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;

@Entity
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Manufacturer manufacturer;
	
	private String modelName;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "datafinacial_id")
	private DataFinancial dataFinancial = new DataFinancial();
	
	//private DataTechnical dataTechnical;
	private int weight;
	private int heatDissipation;
	private int powerConnectionValue;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MountingTypeEnum mountingTypeEnum;
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private FunctionEnum functionEnum;
	
	@ManyToMany(mappedBy = "deviceList")
	private List<Room> roomList = new ArrayList<Room>();

	private boolean isActive = true;
	
	/**
	 * Constructor
	 */
	public Device() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param room
	 */
	public Device(List<Room> roomList) {
		super();
		this.roomList = roomList;
	}
	
	/**
	 * Getter
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the manufacturer
	 */
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * Setter
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Getter
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * Setter
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * Getter
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Setter
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Getter
	 * @return the dataFinancial
	 */
	public DataFinancial getDataFinancial() {
		return dataFinancial;
	}

	/**
	 * Setter
	 * @param dataFinancial the dataFinancial to set
	 */
	public void setDataFinancial(DataFinancial dataFinancial) {
		this.dataFinancial = dataFinancial;
	}

	/**
	 * Getter
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Setter
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Getter
	 * @return the heatDissipation
	 */
	public int getHeatDissipation() {
		return heatDissipation;
	}

	/**
	 * Setter
	 * @param heatDissipation the heatDissipation to set
	 */
	public void setHeatDissipation(int heatDissipation) {
		this.heatDissipation = heatDissipation;
	}

	/**
	 * Getter
	 * @return the powerConnectionValue
	 */
	public int getPowerConnectionValue() {
		return powerConnectionValue;
	}

	/**
	 * Setter
	 * @param powerConnectionValue the powerConnectionValue to set
	 */
	public void setPowerConnectionValue(int powerConnectionValue) {
		this.powerConnectionValue = powerConnectionValue;
	}

	/**
	 * Getter
	 * @return the mountingTypeEnum
	 */
	public MountingTypeEnum getMountingTypeEnum() {
		return mountingTypeEnum;
	}

	/**
	 * Setter
	 * @param mountingTypeEnum the mountingTypeEnum to set
	 */
	public void setMountingTypeEnum(MountingTypeEnum mountingTypeEnum) {
		this.mountingTypeEnum = mountingTypeEnum;
	}

	/**
	 * Getter
	 * @return the functionEnum
	 */
	public FunctionEnum getFunctionEnum() {
		return functionEnum;
	}

	/**
	 * Setter
	 * @param functionEnum the functionEnum to set
	 */
	public void setFunctionEnum(FunctionEnum functionEnum) {
		this.functionEnum = functionEnum;
	}

	/**
	 * Getter
	 * @return the room
	 */
	public List<Room> getRoomList() {
		return roomList;
	}

	/**
	 * Setter
	 * @param room the room to set
	 */
	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}

	/**
	 * Getter
	 * @return the isActive
	 */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * Setter
	 * @param isActive the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/*
	 * Method that adds room to the device
	 */
	public void addRoom(Room room) {
		this.roomList.add(room);
	}

	/*
	 * Method that remove room from the device
	 */
	public void removeRoom(Room room) {
		this.roomList.remove(room);
	}
}
