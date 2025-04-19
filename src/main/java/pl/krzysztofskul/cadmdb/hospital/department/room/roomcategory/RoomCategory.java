package pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.hospital.department.room.Room;

/*
 * STADARDIZED NAME OF THE ROOM
 */
@Entity
public class RoomCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String roomcategorynamePL;
	private String roomcategorynameEN;

	@OneToMany(mappedBy = "roomCategory")
	private List<Room> roomList = new ArrayList<Room>();
	
	/**
	 * 
	 */
	public RoomCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param roomcategorynamePL
	 * @param roomcategorynameEN
	 */
	public RoomCategory(String roomcategorynamePL, String roomcategorynameEN) {
		super();
		this.roomcategorynamePL = roomcategorynamePL;
		this.roomcategorynameEN = roomcategorynameEN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomcategorynamePL() {
		return roomcategorynamePL;
	}

	public void setRoomcategorynamePL(String roomcategorynamePL) {
		this.roomcategorynamePL = roomcategorynamePL;
	}

	public String getRoomcategorynameEN() {
		return roomcategorynameEN;
	}

	public void setRoomcategorynameEN(String roomcategorynameEN) {
		this.roomcategorynameEN = roomcategorynameEN;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}

}
