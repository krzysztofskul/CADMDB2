package pl.krzysztofskul.cadmdb.healthcarefacility.simulation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pl.krzysztofskul.cadmdb.hospital.Hospital;

@Entity
public class Simulation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "simulation")
	@JsonIgnore
	private Hospital hospital;
	
	private int time = 2;

	/**
	 * Constructor
	 */
	public Simulation() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	/**
	 * Constructor
	 * @param hospital
	 */
	public Simulation(Hospital hospital) {
		super();
		this.hospital = hospital;
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
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Setter
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
}
