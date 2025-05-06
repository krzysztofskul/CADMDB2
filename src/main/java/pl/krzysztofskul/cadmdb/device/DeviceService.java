package pl.krzysztofskul.cadmdb.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

	private DeviceRepo deviceRepo;

	/**
	 * @param deviceRepo
	 */
	@Autowired
	public DeviceService(DeviceRepo deviceRepo) {
		super();
		this.deviceRepo = deviceRepo;
	}
	
	public void save(Device device) {
		deviceRepo.save(device);
	}
	
	public Device saveAndReturn(Device device) {
		return deviceRepo.save(device);
	}
	
	public List<Device> loadAll() {
		return deviceRepo.findAll();
	}
	
	public List<Device> LoadAllActive() {
		return deviceRepo.findAllByIsActive(true);
	}
	
	public Device loadById(Long id) {
		return deviceRepo.findById(id).get();
	}
	
	public void deleteById(Long id) {
		deviceRepo.deleteById(id);
	}
	
}
