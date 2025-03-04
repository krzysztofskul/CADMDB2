package pl.krzysztofskul.cadmdb.device;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceDemoGeneratorTest {

	@Autowired
	private DeviceDemoGenerator deviceDemoGenerator;


	
	@Test
	/**
	 * Test initDataAndReturn() method
	 */
	public void test01() {
		Device device = deviceDemoGenerator.initDataAndReturn();
		//System.out.println(device.getManufacturer()+" - "+device.getModelName());
		//System.out.println(device.getPrice());
		//System.out.println(device.getWeight());
		//System.out.println(device.getPowerConnectionValue());
		assertTrue(device.getManufacturer() != null && device.getManufacturer().length() > 0);
	}
	
	@Test
	/**
	 * Test iniListAndReturn() method
	 */
	public void test02() {
		//System.out.println("--test02--");
		List<Device> deviceList = deviceDemoGenerator.iniListAndReturn();
		assertTrue(deviceList.size() > 0);
		for (Device device : deviceList) {
			//System.out.println(device.getManufacturer() + " | " + device.getModelName() + " | " + device.getCategory().getNamePL());
			assertTrue(device.getCategory() != null);
		}
	}

}
