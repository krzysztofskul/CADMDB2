package pl.krzysztofskul.cadmdb.device;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepo extends JpaRepository<Device, Long>{

	List<Device> findAllByIsActive(boolean isActive);

}
