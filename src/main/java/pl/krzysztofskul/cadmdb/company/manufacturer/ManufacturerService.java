package pl.krzysztofskul.cadmdb.company.manufacturer;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class ManufacturerService {

	private ManufacturerRepo manufacturerRepo;

	/**
	 * Constructor
	 * @param manufacturerRepo
	 */
	@Autowired
	public ManufacturerService(ManufacturerRepo manufacturerRepo) {
		super();
		this.manufacturerRepo = manufacturerRepo;
	}
	
	public void save(Manufacturer manufacturer) {
		manufacturerRepo.save(manufacturer);
	}
	
	public Manufacturer saveAndReturn(Manufacturer manufacturer) {
		return manufacturerRepo.save(manufacturer);
	}
	
	public List<Manufacturer> loadAll() {
		return manufacturerRepo.findAll();
	}
	
	public Manufacturer loadById(Long id) {
		return manufacturerRepo.findById(id).get();
	}
	
	public Manufacturer loadByIdWithProducts(Long manufacturerId) {
		Manufacturer manufacturer = this.loadById(manufacturerId);
		Hibernate.initialize(manufacturer.getProductList());
		return manufacturer;
	}
	
	public Manufacturer loadRandom() {
		List<Manufacturer> manufactrurerList = manufacturerRepo.findAll();
		return manufactrurerList.get(Random.randomInt(0, manufactrurerList.size()));
	}
	
	public void deleteById(Long id) {
		manufacturerRepo.deleteById(id);
	}
	
}
