package pl.krzysztofskul.cadmdb.device;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.device.category.Category;
import pl.krzysztofskul.cadmdb.device.category.CategoryGenerator;
import pl.krzysztofskul.cadmdb.device.category.CategoryService;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class DeviceDemoGenerator implements InitDataGenerator<Device> {

	private List<String> demoManufacturerList = new ArrayList<String>();
	
	private CategoryGenerator categoryGenerator;
	private CategoryService categoryService;
	private Random random;
	
	
	/**
	 * Constructor
	 */
	@Autowired
	public DeviceDemoGenerator(
			Random random 
			, CategoryGenerator categoryGenerator
			, CategoryService categoryService
			) {
		this.categoryGenerator = categoryGenerator;
		this.random = random;
		this.categoryService = categoryService;
		this.createDemoManufacturers();
	}

	private void createDemoManufacturers() {
		demoManufacturerList.add(LoremIpsum.getInstance().getTitle(1)+" Inc.");
		demoManufacturerList.add(LoremIpsum.getInstance().getTitle(1)+" Ltd.");
		demoManufacturerList.add(LoremIpsum.getInstance().getTitle(1)+" GmbH");
		demoManufacturerList.add(LoremIpsum.getInstance().getTitle(1)+" Sp. z o.o.");	
	}

	@Override
	public Device initDataAndReturn() {
		Device device = new Device();
		device.setManufacturer(demoManufacturerList.get(random.nextInt(demoManufacturerList.size())));
		device.setModelName(LoremIpsum.getInstance().getTitle(1));
		device.setWeight(random.randomInt(10, 200));
		device.setHeatDissipation(random.randomInt(10, 200));
		device.setPowerConnectionValue(random.randomInt(10, 200));
		device.setPrice(BigDecimal.valueOf(random.randomInt(10000, 200000)).setScale(2));
		return device;
	}

	@Override
	public List<Device> iniListAndReturn() {
		List<Device> deviceList = new ArrayList<Device>();
		for (Category category : categoryService.loadAll()) {
			Device device = this.initDataAndReturn();
			device.setCategory(category);
			deviceList.add(device);
		}
		return deviceList;
	}


	
}
