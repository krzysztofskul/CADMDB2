package pl.krzysztofskul.cadmdb.product.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.init.InitDataGenerator;

@Service
public class CategoryGenerator implements InitDataGenerator<Category> {

	@Override
	/**
	 * Return new Category();
	 */
	public Category initDataAndReturn() {
		return new Category();
	}

	@Override
	public List<Category> initListAndReturn() {
		
		List<Category> categoryList = new ArrayList<Category>();
		
		// Cat. A
		Category categoryA = new Category("A", "Sprzęt medyczny", "Medical device", "Sprzęt medyczny", "Medical devices");
		
		Category categoryAA = new Category("AA", "Łóżko szpitalne", "Hospital bed", "Łóżka szpitalne", "Hospital beds");
		categoryAA.addChildCategory(new Category("AA20", "Łóżeczko niemowlęce", "Hospital bed infant", "Łóżeczka niemowolęce", "Hospital beds infant"));
		categoryAA.addChildCategory(new Category("AA30", "Łóżko dziecięce", "Hospital bed pediatric", "Łóżka dziecięce", "Hospital beds pediatric"));
		categoryAA.addChildCategory(new Category("AA50", "Łóżko szpitalne dla dorosłych", "Hospital bed for adults", "Łóżka szpitalne dla dorosłych", "Hospital beds for adults"));
		//categoryList.add(categoryAA);
		
		Category categoryAB = new Category("AB", "Stół/fotel diagnostyczny/zabiegowy/operacyjny", "Examinaton/Surgical table/chair", "Stoły/fotele diagnostyczne/zabiegowe/operacyjne", "Examination/Surgical tables/chairs");		
		categoryAB.addChildCategory(new Category("AB10", "Stół operacyjny", "Surgical table", "Stoły operacyjne", "Surgical tables"));
		categoryAB.addChildCategory(new Category("AB20", "Stół diagnostyczno-zabiegowy", "Examinaton table", "Stoły diagnostyczno-zabiegowe", "Examination tables"));
		//categoryList.add(categoryAB);
		
		Category categoryAC = new Category("AC", "Lampa operacyjna", "Sugical light", "Lampy operayjne", "Surgical lights");		
		categoryAC.addChildCategory(new Category("AC20", "Lampa operacyjna", "Sugical light", "Lampy operayjne", "Surgical lights"));
		categoryAC.addChildCategory(new Category("AC10", "Lampa diagnostyczno-zabiegowe", "Examination lights", "Lampy diagnostyczno-zabiegowe", "Examination lights"));
		//categoryList.add(categoryAC);
		
		Category categoryAH = new Category("AH", "Sprzęt anestezjologiczny/ratowniczy/reanimacyjny", "Anesthesia/emergency/resuscitation device", "Sprzęt anestezjologiczny/ratowniczy/reanimacyjny", "Anesthesia/emergency/resuscitation devices");
		categoryAH.addChildCategory(new Category("AH10", "Aparat anestezjologiczny", "Anesthesia device", "Aparaty anestezjologiczne", "Anesthesia devices"));
		categoryAH.addChildCategory(new Category("AH20", "Respirator", "Ventilator", "Respiratory", "Ventilators"));
		categoryAH.addChildCategory(new Category("AH40", "Defibrylator", "Defibrillator", "Defibrlatory", "Defibrillators"));
		//categoryList.add(categoryAH);
		
		Category categoryAE = new Category("AE", "Aparat do diagnostyki obrazowej", "Imaging diagnostic device", "Aparaty do diagnostyki obrazowej", "Imaging diagnostic devices");
		categoryAE.addChildCategory(new Category("AE10", "Aparat MR", "MRI scanner", "Aparaty MR", "MRI scanners"));
		categoryAE.addChildCategory(new Category("AE3001", "Aparat CT", "CT scanner", "Aparaty CT", "CT scanners"));
		categoryAE.addChildCategory(new Category("AE30", "Aparat RTG", "X-RAY scanner", "Aparaty RTG", "X-RAY scanners"));
		categoryAE.addChildCategory(new Category("AE2010", "Aparat SPECT", "SPECT scanner", "Aparaty SPECT", "SPECT scanners"));
		categoryAE.addChildCategory(new Category("AE2020", "Aparat PET", "PET scanner", "Aparaty PET", "PET scanners"));
		categoryAE.addChildCategory(new Category("AE9020", "Aparat PET-CT", "PET-CT scanner", "Aparaty PET-CT", "PET-CT scanners"));
		categoryAE.addChildCategory(new Category("AE3023", "Aparat mammograficzny", "Mammography scanner", "Aparaty mammograficzne", "Mammography scanners"));
		categoryAE.addChildCategory(new Category("AE3011", "Aparat angiograficzny", "Angiography scanner", "Aparaty angiograficzne", "Angiography scanners"));
		//categoryList.add(categoryAE);
		
		categoryA.addChildCategory(categoryAA);
		categoryA.addChildCategory(categoryAB);
		categoryA.addChildCategory(categoryAC);
		categoryA.addChildCategory(categoryAH);
		categoryA.addChildCategory(categoryAE);
		
		//Cat. J
		Category categoryJ = new Category("J", "Sprzęt do dezynfekcji", "Disinfection device", "Sprzęt do dezynfekcji", "Disinfection devices");
		
		Category categoryJA = new Category("JA", "Myjnia/dezynfektor", "Washer/disinfector", "Myjnie/dezynfektory", "Washers/disinfectors");
		categoryJA.addChildCategory(new Category("JA1010", "Myjnia/dezynfektor do narzędzi", "Washer/disinfector for medical instruments", "Myjnie/dezynfektory do narzędzi", "Washers/disinfectors for medical instruments"));
		categoryJA.addChildCategory(new Category("JA1030", "Myjnia/dezynfektor do łóżek i wózków", "Washer/disinfector for beds and trolleys", "Myjnie/dezynfektory do łóżek i wózków", "Washers/disinfectors for beds and trolleys"));
		//categoryList.add(categoryJA);

		Category categoryJB = new Category("JB", "Sterylizator", "Sterilizer", "Sterylizatory", "Sterilizers");
		categoryJB.addChildCategory(new Category("JB10", "Sterylizator parowy", "Steam sterilizer", "Sterylizatory parowe", "Steam sterilizers"));
		categoryJB.addChildCategory(new Category("JB20", "Sterylizator plazmowy", "Plasma sterilizer", "Sterylizatory plazmowe", "Plasma sterilizers"));
		//categoryList.add(categoryJB);
		
		categoryJ.addChildCategory(categoryJA);
		categoryJ.addChildCategory(categoryJB);
		
		//update list
		categoryList.add(categoryA);
		categoryList.add(categoryJ);
		
		return categoryList;
	}

	
}
