package pl.krzysztofskul.cadmdb.device.category;

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
	public List<Category> iniListAndReturn() {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(new Category("", "Łóżko szpitalne", "Hospital bed", "Łóżka szpitalne", "Hospital beds"));
		categoryList.add(new Category("", "Stół operacyjny", "Surgical table", "Stoły operacyjne", "Surgical tables"));
		categoryList.add(new Category("", "Stół diagnostyczno-zabiegowy", "Examinaton table", "Stoły diagnostyczno-zabiegowe", "Examination tables"));
		categoryList.add(new Category("", "Lampa operacyjna", "Sugical light", "Lampy operayjne", "Surgical lights"));
		categoryList.add(new Category("", "Lampa diagnostyczno-zabiegowe", "Examination lights", "Lampy diagnostyczno-zabiegowe", "Examination lights"));
		categoryList.add(new Category("", "Aparat anestezjologiczny", "Anaesthetic device", "Aparaty anestezjologiczne", "Anaesthetic devices"));
		categoryList.add(new Category("", "Aparat MR", "MRI scanner", "Aparaty MR", "MRI scanners"));
		categoryList.add(new Category("", "Aparat CT", "CT scanner", "Aparaty CT", "CT scanners"));
		categoryList.add(new Category("", "Aparat RTG", "X-RAY scanner", "Aparaty RTG", "X-RAY scanners"));
		categoryList.add(new Category("", "Aparat SPECT", "SPECT scanner", "Aparaty SPECT", "SPECT scanners"));
		categoryList.add(new Category("", "Aparat PET", "PET scanner", "Aparaty PET", "PET scanners"));
		categoryList.add(new Category("", "Aparat PET-CT", "PET-CT scanner", "Aparaty PET-CT", "PET-CT scanners"));
		categoryList.add(new Category("", "Aparat mammograficzny", "Mammography scanner", "Aparaty mammograficzne", "Mammography scanners"));
		categoryList.add(new Category("", "Aparat angiograficzny", "Angiogrpahy scanner", "Aparaty angiograficzne", "Angiography scanners"));
		return categoryList;
	}

	
}
