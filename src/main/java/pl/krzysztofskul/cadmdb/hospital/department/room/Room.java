package pl.krzysztofskul.cadmdb.hospital.department.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom.DataArchRoom;
import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.product.Product;

@Entity
public class Room extends HealthcareFacility {

	private String roomNo;
	
	@ManyToOne
	private Department department;
	
    @OneToOne(
            mappedBy    = "room",
            cascade     = CascadeType.ALL,
            orphanRemoval = true,
            fetch       = FetchType.EAGER
        )
    private DataArchRoom dataArchRoom;
	
	@ManyToOne
	private NameStandardized nameStandardized;
	
	@ManyToMany
    @JoinTable(
            name = "rooms_products",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
	private List<Product> productList = new ArrayList<Product>();
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private FunctionEnum functionEnum;
	
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
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * Setter
	 * @param productList the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
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
	 * Method that add planned product to the room with cost of planned purchase calculation
	 */
	public void addProduct(Product product) {
		this.productList.add(product);
		product.addRoom(this);
	}
	
	/**
	 * Method that remove planned product from the room with cost of planned purchase calculation
	 */
	public void removeProduct(Product product) {
		this.productList.remove(product);
		product.removeRoom(this);
	}
}
