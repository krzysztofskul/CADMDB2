package pl.krzysztofskul.cadmdb.hospital.department.depcategory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class DepCategoryService {

	private DepCategoryRepo depCategoryRepo;

	/**
	 * @param depCategoryRepo
	 */
	@Autowired
	public DepCategoryService(DepCategoryRepo depCategoryRepo) {
		super();
		this.depCategoryRepo = depCategoryRepo;
	}

	public void save(DepCategory depcategory) {
		depCategoryRepo.save(depcategory);
	}
	
	public List<DepCategory> loadAll() {
		return depCategoryRepo.findAll();
	}
	
	public List<DepCategory> loadRandomList(int amount) {
		List<DepCategory> depCategoryList = this.loadAll();
		List<DepCategory> depCategorylistAllowed = new ArrayList<DepCategory>();
		for (int i = 0; i < amount; i++) {
			depCategorylistAllowed.add(depCategoryList.get(Random.randomInt(0, depCategoryList.size())));
		}
		depCategoryList.retainAll(depCategorylistAllowed);
		return depCategoryList;
	}
	
}
