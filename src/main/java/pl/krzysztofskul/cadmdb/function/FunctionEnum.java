package pl.krzysztofskul.cadmdb.function;

public enum FunctionEnum {

	MEDICAL ("MEDYCZNA", "MEDICAL"),
//	MEDICAL_CONSULTATION,
//	MEDICAL_EXAMINATION,
//	MEDICAL_TREATMENT,
//	MEDICAL_OPERATION,
//	MEDICAL_PREPARATION,
//	MEDICAL_CARE_STANDARD,
//	MEDCIAL_CARE_INTENSIVE,
	LABORATORY ("LABORATORYJNA", "LABORATORY"),
	SANITARY ("SANITARNA", "SANITARY"),
	TECHNICAL ("TECHNICZNA", "TACHNICAL"),
//	STAFF_AREA,
//	PATIENT_AREA,
//	PUBLIC_AREA,
	OFFICE ("BIUROWA", "OFFICE"),
	OTHERS ("INNA", "OTHERS")
	;
	
	private final String namePL;
	private final String nameEN;
	/**
	 * Constructor
	 * @param namePL
	 * @param nameEN
	 */
	private FunctionEnum(String namePL, String nameEN) {
		this.namePL = namePL;
		this.nameEN = nameEN;
	}
	/**
	 * Getter
	 * @return the namePL
	 */
	public String getNamePL() {
		return namePL;
	}
	/**
	 * Getter
	 * @return the nameEN
	 */
	public String getNameEN() {
		return nameEN;
	}
	
	
	
}
