package pl.krzysztofskul.cadmdb.device;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import pl.krzysztofskul.cadmdb.device.category.Category;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;

@Entity
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String manufacturer;
	
	private String modelName;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;
	
	//private DataSales dataSales;
	private BigDecimal price = new BigDecimal(0);
	
	//private DataTechnical dataTechnical;
	private int weight;
	private int heatDissipation;
	private int powerConnectionValue;
	
	@ManyToMany(mappedBy = "deviceList", cascade = CascadeType.ALL)
	private List<Room> room = new ArrayList<Room>();

	/**
	 * Constructor
	 */
	public Device() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeatDissipation() {
		return heatDissipation;
	}

	public void setHeatDissipation(int heatDissipation) {
		this.heatDissipation = heatDissipation;
	}

	public int getPowerConnectionValue() {
		return powerConnectionValue;
	}

	public void setPowerConnectionValue(int powerConnectionValue) {
		this.powerConnectionValue = powerConnectionValue;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}
	
}
