package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.hospital.Hospital;

@Entity
public class DataArchHospital extends DataArch {

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Hospital hospital;

	/**
	 * Constructor
	 */
	public DataArchHospital() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor
	 * @param hospital
	 */
	public DataArchHospital(Hospital hospital) {
		super();
		this.hospital = hospital;
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
	
    
    
}
