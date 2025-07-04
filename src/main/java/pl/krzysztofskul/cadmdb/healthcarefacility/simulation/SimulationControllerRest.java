package pl.krzysztofskul.cadmdb.healthcarefacility.simulation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationControllerRest {

	private SimulationService simulationService;
	
	/**
	 * Constructor
	 * @param simulationService
	 */
	@Autowired
	public SimulationControllerRest(SimulationService simulationService) {
		super();
		this.simulationService = simulationService;
	}

	@PostMapping("/rest/simulations/{simulationId}")
	public ResponseEntity<String> getRestSimulationsId(
				@PathVariable String simulationId,
				@RequestBody Map<String, String> payload
			) {
		Simulation simulation = simulationService.loadById(Long.parseLong(simulationId));
		simulation.setTime(Integer.parseInt(payload.get("time")));
		simulation = simulationService.save(simulation);
		return ResponseEntity.ok(simulation.toString());
	}
	
}
