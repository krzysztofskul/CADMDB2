package pl.krzysztofskul.cadmdb.product;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.company.manufacturer.ManufacturerService;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.dataarchroom.DataArchRoom;
import pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized.NameStandardizedService;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.product.category.Category;
import pl.krzysztofskul.cadmdb.product.category.CategoryService;
import pl.krzysztofskul.cadmdb.product.datafinancial.DataFinancial;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class ProductDefaultGenerator {
	
	private List<Product> defaultProductList = new ArrayList<Product>();
	
	private CategoryService categoryService;
	private ManufacturerService manufacturerService;
	
	@Autowired
	public ProductDefaultGenerator(
				CategoryService categoryService,
				ManufacturerService manufacturerService
			) {
		this.categoryService = categoryService;
		this.manufacturerService = manufacturerService;
	}
	
	private Product initDataAndReturn() {
		Product product = new Product();
		return product;
	}

	public List<Product> initListAndReturn() {
		if (defaultProductList.size() == 0 ) {
			for (Category category : categoryService.loadAll()) {
				Product product = this.initDataAndReturn();
				product.setCategory(category);
				product.setModelName("-");
				product = this.setDefaultFinancialData(product);
				product.setManufacturer(manufacturerService.loadOneDefault());
				defaultProductList.add(product);
			}	
		}
		
		return defaultProductList;
	}

	private Product setDefaultFinancialData(Product product) {
		product.setDataFinancial(new DataFinancial());
		if (product.getCategory().getCode() == "AA50") {
			product.getDataFinancial().setPrice(BigDecimal.valueOf(60000));
		} else if (product.getCategory().getCode() == "AA10") {
			product.getDataFinancial().setPrice(BigDecimal.valueOf(120000));
		} else if (product.getCategory().getCode() == "AA20") {
			product.getDataFinancial().setPrice(BigDecimal.valueOf(85000));
		} else if (product.getCategory().getCode() == "AH10") {
			product.getDataFinancial().setPrice(BigDecimal.valueOf(70000));
		} else {
			product.getDataFinancial().setPrice(BigDecimal.valueOf(10000));
		}
		product.getDataFinancial().setMaintenanceCost(product.getDataFinancial().getPrice().divide(BigDecimal.valueOf(100)));
		return product;
	}
	
}
