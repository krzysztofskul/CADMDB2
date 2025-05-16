package pl.krzysztofskul.cadmdb.company.manufacturer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.address.AddressTestGenerator;
import pl.krzysztofskul.cadmdb.init.InitDataGenerator;

@Service
public class ManufacturerTestGenerator implements InitDataGenerator<Manufacturer> {

	private AddressTestGenerator addressTestGenerator;
	
	/**
	 * Constructor
	 * @param addressTestGenerator
	 */
	@Autowired
	public ManufacturerTestGenerator(
			AddressTestGenerator addressTestGenerator
			) {
		super();
		this.addressTestGenerator = addressTestGenerator;
	}

	@Override
	public Manufacturer initDataAndReturn() {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(LoremIpsum.getInstance().getTitle(1)+" sp. z o.o.");
		manufacturer.setAddress(addressTestGenerator.initDataAndReturn());
		return manufacturer;
	}

	@Override
	public List<Manufacturer> initListAndReturn() {
		List<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();
		for (int i = 0; i < 5; i++) {
			manufacturerList.add(this.initDataAndReturn());
		}
		return manufacturerList;
	}

}
