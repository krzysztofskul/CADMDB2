package pl.krzysztofskul.cadmdb.device.category;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private CategoryRepo categoryRepo;

	/**
	 * @param categoryRepo
	 */
	@Autowired
	public CategoryService(CategoryRepo categoryRepo) {
		super();
		this.categoryRepo = categoryRepo;
	}
	
	public Category saveAndReturn(Category category) {
		return categoryRepo.save(category);
	}

	public void save(Category category) {
		categoryRepo.save(category);
	}
	
	public List<Category> loadAll() {
		return categoryRepo.findAll();
	}
	
	public Category loadById(Long id) {
		return categoryRepo.findById(id).get();
	}
	
	public Category loadByCategoryCode(String code) {
		return categoryRepo.findByCode(code);
	}
	
	public Category loadByCategoryCodeWithDeviceList(String code) {
		Category category = this.loadByCategoryCode(code);
		Hibernate.initialize(category.getDeviceList());
		return category;
	}
	
	public List<Category> loadAllByCategoryCode(String code) {
		return categoryRepo.findAllByCode(code);
	}

	public List<Category> loadByCategoryCodeStartingWith(String prefix) {
		List<Category> categoryList = categoryRepo.findAllByCodeStartingWith(prefix);
		for (Category category : categoryList) {
			Hibernate.initialize(category.getCategoryChildren());
		}
		return categoryList;
	}
	
	public Category loadByCategoryNamePL(String namePL) {
		return categoryRepo.findByNamePL(namePL);
	}

	public Category loadByCategoryNameEN(String nameEN) {
		return categoryRepo.findByNameEN(nameEN);
	}
	
	public void deleteCategoryById(Long id) {
		categoryRepo.deleteById(id);
	}

}
