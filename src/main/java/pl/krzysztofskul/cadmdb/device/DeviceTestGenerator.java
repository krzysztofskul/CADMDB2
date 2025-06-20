package pl.krzysztofskul.cadmdb.device;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerService;
import pl.krzysztofskul.cadmdb.device.category.Category;
import pl.krzysztofskul.cadmdb.device.category.CategoryGenerator;
import pl.krzysztofskul.cadmdb.device.category.CategoryService;
import pl.krzysztofskul.cadmdb.device.datafinancial.DataFinancial;
import pl.krzysztofskul.cadmdb.device.mounting.MountingTypeEnum;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class DeviceTestGenerator implements InitDataGenerator<Device> {

	//private List<String> demoManufacturerList = new ArrayList<String>();
	
	private CategoryGenerator categoryGenerator;
	private CategoryService categoryService;
	private ManufacturerService manufacturerService;
	private Random random;
	
	
	/**
	 * Constructor
	 */
	@Autowired
	public DeviceTestGenerator(
			Random random 
			, CategoryGenerator categoryGenerator
			, CategoryService categoryService
			, ManufacturerService manufacturerService
			) {
		this.categoryGenerator = categoryGenerator;
		this.random = random;
		this.categoryService = categoryService;
		this.manufacturerService = manufacturerService;
		this.createDemoManufacturers();
	}

	private void createDemoManufacturers() {
		
		//demoManufacturerList.add(LoremIpsum.getInstance().getTitle(1)+" Inc.");
		//demoManufacturerList.add(LoremIpsum.getInstance().getTitle(1)+" Ltd.");
		//demoManufacturerList.add(LoremIpsum.getInstance().getTitle(1)+" GmbH");
		//demoManufacturerList.add(LoremIpsum.getInstance().getTitle(1)+" Sp. z o.o.");	
	}

	@Override
	public Device initDataAndReturn() {
		Device device = new Device();
		device.setManufacturer(manufacturerService.loadRandom());
		device.setModelName(LoremIpsum.getInstance().getTitle(1));
		device.setFunctionEnum(this.randomFunction());
		device.setWeight(random.randomInt(10, 200));
		device.setHeatDissipation(random.randomInt(10, 200));
		device.setPowerConnectionValue(random.randomInt(10, 200));
		device.setMountingTypeEnum(this.randomMountingType());
		device.setDataFinancial(new DataFinancial(BigDecimal.valueOf(random.randomInt(10000, 200000)).setScale(2)));
		return device;
	}

	private MountingTypeEnum randomMountingType() {
		MountingTypeEnum[] rTable= MountingTypeEnum.values();
		int mteTableLengt = rTable.length;
		return rTable[random.nextInt(mteTableLengt)];
	}
	private FunctionEnum randomFunction() {
		FunctionEnum[] rTable = FunctionEnum.values();
		int rTableLengt = rTable.length;
		return rTable[random.nextInt(rTableLengt)];
	}

	@Override
	public List<Device> initListAndReturn() {
		List<Device> deviceList = new ArrayList<Device>();
		for (Category category : categoryService.loadAll()) {
			Device device = this.initDataAndReturn();
			device.setCategory(category);
			deviceList.add(device);
		}
		return deviceList;
	}


	
}
