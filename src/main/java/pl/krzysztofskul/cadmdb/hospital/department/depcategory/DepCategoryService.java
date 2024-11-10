package pl.krzysztofskul.cadmdb.hospital.department.depcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
}
