package pl.krzysztofskul.cadmdb.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerService;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.product.category.Category;
import pl.krzysztofskul.cadmdb.product.category.CategoryGenerator;
import pl.krzysztofskul.cadmdb.product.category.CategoryService;
import pl.krzysztofskul.cadmdb.product.datafinancial.DataFinancial;
import pl.krzysztofskul.cadmdb.product.mounting.MountingTypeEnum;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class ProductTestGenerator implements InitDataGenerator<Product> {

	//private List<String> demoManufacturerList = new ArrayList<String>();
	
	private CategoryGenerator categoryGenerator;
	private CategoryService categoryService;
	private ManufacturerService manufacturerService;
	private Random random;
	
	
	/**
	 * Constructor
	 */
	@Autowired
	public ProductTestGenerator(
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
	public Product initDataAndReturn() {
		Product product = new Product();
		product.setManufacturer(manufacturerService.loadRandom());
		product.setModelName(LoremIpsum.getInstance().getTitle(1));
		product.setFunctionEnum(this.randomFunction());
		product.setWeight(random.randomInt(10, 200));
		product.setHeatDissipation(random.randomInt(10, 200));
		product.setPowerConnectionValue(random.randomInt(10, 200));
		product.setMountingTypeEnum(this.randomMountingType());
		product.setDataFinancial(new DataFinancial(BigDecimal.valueOf(random.randomInt(10000, 200000)).setScale(2)));
		return product;
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
	public List<Product> initListAndReturn() {
		List<Product> productList = new ArrayList<Product>();
		for (Category category : categoryService.loadAll()) {
			Product product = this.initDataAndReturn();
			product.setCategory(category);
			productList.add(product);
		}
		return productList;
	}


	
}
