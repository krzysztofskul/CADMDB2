package pl.krzysztofskul.cadmdb.healthcarefacility.simulation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepo extends JpaRepository<Simulation, Long> {

}
