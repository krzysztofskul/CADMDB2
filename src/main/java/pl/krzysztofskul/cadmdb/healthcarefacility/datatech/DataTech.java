package pl.krzysztofskul.cadmdb.healthcarefacility.datatech;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "hfdatatech")
@Table(name = "hfdatatech")
public class DataTech {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int weightTotal;
	private int powerConnectionValueTotal;
	private int heatDissipationTotal;
	
	/**
	 * Constructor
	 */
	public DataTech() {
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
	 * @return the weightTotal
	 */
	public int getWeightTotal() {
		return weightTotal;
	}
	/**
	 * Setter
	 * @param weightTotal the weightTotal to set
	 */
	public void setWeightTotal(int weightTotal) {
		this.weightTotal = weightTotal;
	}
	/**
	 * Getter
	 * @return the powerConnectionValueTotal
	 */
	public int getPowerConnectionValueTotal() {
		return powerConnectionValueTotal;
	}
	/**
	 * Setter
	 * @param powerConnectionValueTotal the powerConnectionValueTotal to set
	 */
	public void setPowerConnectionValueTotal(int powerConnectionValueTotal) {
		this.powerConnectionValueTotal = powerConnectionValueTotal;
	}
	/**
	 * Getter
	 * @return the heatDissipationTotal
	 */
	public int getHeatDissipationTotal() {
		return heatDissipationTotal;
	}
	/**
	 * Setter
	 * @param heatDissipationTotal the heatDissipationTotal to set
	 */
	public void setHeatDissipationTotal(int heatDissipationTotal) {
		this.heatDissipationTotal = heatDissipationTotal;
	}
	
}
