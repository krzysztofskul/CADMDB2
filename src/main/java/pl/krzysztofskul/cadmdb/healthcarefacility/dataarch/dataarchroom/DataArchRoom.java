package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.hospital.department.room.Room;

@Entity
public class DataArchRoom {

	@Id
	private Long id;
	
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private Room room;
	
	private float area;
	private float heightCeilingStructural; //structural ceiling height
	private float heightCeiligSuspend; //suspended ceiling height
	
	private String descriptionGeneral;
	private String descriptionFloor;
	private String descriptionWalls;
	private String descriptionCeilig;
	private String descriptionLight;
	
	/**
	 * Constructor
	 */
	public DataArchRoom() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DataArchRoom(Room room) {
		this.room = room;
	}

	/**
	 * Getter
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}
	/**
	 * Setter
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
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
	 * @return the area
	 */
	public float getArea() {
		return area;
	}
	/**
	 * Setter
	 * @param area the area to set
	 */
	public void setArea(float area) {
		this.area = area;
	}
	/**
	 * Getter
	 * @return the heightCeilingStructural
	 */
	public float getHeightCeilingStructural() {
		return heightCeilingStructural;
	}
	/**
	 * Setter
	 * @param heightCeilingStructural the heightCeilingStructural to set
	 */
	public void setHeightCeilingStructural(float heightCeilingStructural) {
		this.heightCeilingStructural = heightCeilingStructural;
	}
	/**
	 * Getter
	 * @return the heightCeiligSuspend
	 */
	public float getHeightCeiligSuspend() {
		return heightCeiligSuspend;
	}
	/**
	 * Setter
	 * @param heightCeiligSuspend the heightCeiligSuspend to set
	 */
	public void setHeightCeiligSuspend(float heightCeiligSuspend) {
		this.heightCeiligSuspend = heightCeiligSuspend;
	}
	/**
	 * Getter
	 * @return the descriptionGeneral
	 */
	public String getDescriptionGeneral() {
		return descriptionGeneral;
	}
	/**
	 * Setter
	 * @param descriptionGeneral the descriptionGeneral to set
	 */
	public void setDescriptionGeneral(String descriptionGeneral) {
		this.descriptionGeneral = descriptionGeneral;
	}
	/**
	 * Getter
	 * @return the descriptionFloor
	 */
	public String getDescriptionFloor() {
		return descriptionFloor;
	}
	/**
	 * Setter
	 * @param descriptionFloor the descriptionFloor to set
	 */
	public void setDescriptionFloor(String descriptionFloor) {
		this.descriptionFloor = descriptionFloor;
	}
	/**
	 * Getter
	 * @return the descriptionWalls
	 */
	public String getDescriptionWalls() {
		return descriptionWalls;
	}
	/**
	 * Setter
	 * @param descriptionWalls the descriptionWalls to set
	 */
	public void setDescriptionWalls(String descriptionWalls) {
		this.descriptionWalls = descriptionWalls;
	}
	/**
	 * Getter
	 * @return the descriptionCeilig
	 */
	public String getDescriptionCeilig() {
		return descriptionCeilig;
	}
	/**
	 * Setter
	 * @param descriptionCeilig the descriptionCeilig to set
	 */
	public void setDescriptionCeilig(String descriptionCeilig) {
		this.descriptionCeilig = descriptionCeilig;
	}
	/**
	 * Getter
	 * @return the descriptionLight
	 */
	public String getDescriptionLight() {
		return descriptionLight;
	}
	/**
	 * Setter
	 * @param descriptionLight the descriptionLight to set
	 */
	public void setDescriptionLight(String descriptionLight) {
		this.descriptionLight = descriptionLight;
	}
	
}
