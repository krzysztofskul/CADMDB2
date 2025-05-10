package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DataArch {

	@Id
	private Long id;
	
	private float areaTotal;
	private float areaMed;
	private float areaOffice;
	private float areaTech;
	
	private float volumeTotal;
	private float volumeMed;
	private float volumeOffice;
	private float volumeTech;
	
	/**
	 * Constructor
	 */
	public DataArch() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the areaTotal
	 */
	public float getAreaTotal() {
		return areaTotal;
	}
	/**
	 * Setter
	 * @param areaTotal the areaTotal to set
	 */
	public void setAreaTotal(float areaTotal) {
		this.areaTotal = areaTotal;
	}
	/**
	 * Getter
	 * @return the areaMed
	 */
	public float getAreaMed() {
		return areaMed;
	}
	/**
	 * Setter
	 * @param areaMed the areaMed to set
	 */
	public void setAreaMed(float areaMed) {
		this.areaMed = areaMed;
	}
	/**
	 * Getter
	 * @return the areaOffice
	 */
	public float getAreaOffice() {
		return areaOffice;
	}
	/**
	 * Setter
	 * @param areaOffice the areaOffice to set
	 */
	public void setAreaOffice(float areaOffice) {
		this.areaOffice = areaOffice;
	}
	/**
	 * Getter
	 * @return the areaTech
	 */
	public float getAreaTech() {
		return areaTech;
	}
	/**
	 * Setter
	 * @param areaTech the areaTech to set
	 */
	public void setAreaTech(float areaTech) {
		this.areaTech = areaTech;
	}
	/**
	 * Getter
	 * @return the volumeTotal
	 */
	public float getVolumeTotal() {
		return volumeTotal;
	}
	/**
	 * Setter
	 * @param volumeTotal the volumeTotal to set
	 */
	public void setVolumeTotal(float volumeTotal) {
		this.volumeTotal = volumeTotal;
	}
	/**
	 * Getter
	 * @return the volumeMed
	 */
	public float getVolumeMed() {
		return volumeMed;
	}
	/**
	 * Setter
	 * @param volumeMed the volumeMed to set
	 */
	public void setVolumeMed(float volumeMed) {
		this.volumeMed = volumeMed;
	}
	/**
	 * Getter
	 * @return the volumeOffice
	 */
	public float getVolumeOffice() {
		return volumeOffice;
	}
	/**
	 * Setter
	 * @param volumeOffice the volumeOffice to set
	 */
	public void setVolumeOffice(float volumeOffice) {
		this.volumeOffice = volumeOffice;
	}
	/**
	 * Getter
	 * @return the volumeTech
	 */
	public float getVolumeTech() {
		return volumeTech;
	}
	/**
	 * Setter
	 * @param volumeTech the volumeTech to set
	 */
	public void setVolumeTech(float volumeTech) {
		this.volumeTech = volumeTech;
	}
	
	
	
}
