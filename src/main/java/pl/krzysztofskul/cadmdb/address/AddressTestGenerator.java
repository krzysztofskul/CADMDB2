package pl.krzysztofskul.cadmdb.address;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.init.InitDataGenerator;
import pl.krzysztofskul.cadmdb.random.Random;

@Service
public class AddressTestGenerator implements InitDataGenerator<Address> {

	private LoremIpsum loremipsum = LoremIpsum.getInstance();
	@Autowired
	private Random random;
	
	@Override
	public Address initDataAndReturn() {
		Address address = new Address();
		address.setCountry(loremipsum.getCountry());
		address.setPostalcode(loremipsum.getZipCode());
		address.setCity(loremipsum.getCity());
		address.setStreetname(loremipsum.getName());
		address.setStreetno(String.valueOf(random.randomInt(1, 199)));
		return address;
	}

	@Override
	public List<Address> iniListAndReturn() {
		List<Address> addressList = new ArrayList<Address>();
		for (int i = 0; i < 2; i++) {
			addressList.add(initDataAndReturn());
		}
		return addressList;
	}

}
