package pl.krzysztofskul.cadmdb.healthcarefacility.simulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SimulationService {

	private SimulationRepo simulationRepo;

	/**
	 * Constructor
	 * @param simulationRepo
	 */
	@Autowired
	public SimulationService(SimulationRepo simulationRepo) {
		super();
		this.simulationRepo = simulationRepo;
	}
	
	public Simulation save(Simulation simulation) {
		return simulationRepo.save(simulation);
	}
	
	public Simulation loadById(Long id) {
		return simulationRepo.findById(id).get();
	}
	
}
