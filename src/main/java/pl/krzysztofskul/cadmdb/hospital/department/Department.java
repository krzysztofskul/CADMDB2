package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.DataArchDepartment;
import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.department.namestandardized.NameStandardized;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;

@Entity
public class Department extends HealthcareFacility {

	@ManyToOne
	private Hospital hospital;
	
	@ManyToOne
	private NameStandardized nameStandardized;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Room> roomList = new ArrayList<Room>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dataarchdepartment_id")
    private DataArchDepartment dataArchDepartment;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private FunctionEnum functionEnum;

    @PrePersist
    private void ensureDataArchDepartment() {
        if (this.dataArchDepartment == null) {
            this.dataArchDepartment = new DataArchDepartment(this);
        	//DataArchDepartment dArch = new DataArchDepartment();
            //this.dataArchDepartment = dArch;
        }
    }
	
	/**
	 * Constructor
	 */
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param hospital
	 */
	public Department(Hospital hospital) {
		super();
		this.hospital = hospital;
	}



	/**
	 * @param name
	 * @param address
	 * @param contactdetails
	 */
	public Department(String name, Address address, String contactdetails) {
		super(name, address, contactdetails);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public Department(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param nameStandardized
	 */
	public Department(NameStandardized nameStandardized) {
		super();
		this.nameStandardized = nameStandardized;
	}

	/**
	 * @param hospital
	 * @param nameStandardized
	 */
	public Department(Hospital hospital, NameStandardized nameStandardized) {
		super();
		this.hospital = hospital;
		this.nameStandardized = nameStandardized;
	}

	/**
	 * Getter
	 * @return the hospital
	 */
	public Hospital getHospital() {
		return hospital;
	}

	/**
	 * Setter
	 * @param hospital the hospital to set
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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
	 * @return the roomList
	 */
	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		for (Room room : roomList) {
			room.setDepartment(this);
		}
		this.roomList = roomList;
	}
	
	/**
	 * Getter
	 * @return the dataArchDepartment
	 */
	public DataArchDepartment getDataArchDepartment() {
		return dataArchDepartment;
	}

	/**
	 * Setter
	 * @param dataArchDepartment the dataArchDepartment to set
	 */
	public void setDataArchDepartment(DataArchDepartment dataArchDepartment) {
		this.dataArchDepartment = dataArchDepartment;
		dataArchDepartment.setDepartment(this);
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

	public void addRoom(Room room) {
		this.roomList.add(room);
		room.setDepartment(this);
	}

	public void removeRoom(Room room) {
		this.roomList.remove(room);
		room.setDepartment(null);		
	}
	
}
