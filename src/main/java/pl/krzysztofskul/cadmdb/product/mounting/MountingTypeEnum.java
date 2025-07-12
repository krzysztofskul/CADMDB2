package pl.krzysztofskul.cadmdb.product.mounting;

public enum MountingTypeEnum {
	
		FLOOR("PODŁOGOWY", "FLOOR"), 
		MOBILE("JEZDNY", "MOBILE"), 
		CEILING("SUFITOWY", "CEILING"), 
		WALL("ŚCIENNY", "WALL"), 
		BENCHTOP("NABLATOWY", "BENCHTOP"), 
		PORTABLE("PRZENOŚNY", "PORTABLE"), 
		STANDALONE("WOLNOSTOJĄCY", "STANDALONE");
		
		private final String namePL;
		private final String nameEN;
		
		/**
		 * Constructor
		 * @param namePL
		 * @param nameEN
		 */
		private MountingTypeEnum(String namePL, String nameEN) {
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
