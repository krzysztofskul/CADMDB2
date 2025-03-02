package pl.krzysztofskul.cadmdb.hospital;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.krzysztofskul.cadmdb.hospital.department.DepartmentTestGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
class HospitalTestGeneratorTest {

	@Autowired
	HospitalTestGenerator hospitalTestGenerator;
	
	@Test
	void testInitDataAndReturn() {
		Hospital hospital = hospitalTestGenerator.initDataAndReturn();
		assertTrue(hospital.getName() != null);
		assertTrue(hospital.getAddress() != null);
		assertTrue(hospital.getDepartmentList().size() > 0);
	}

	@Test
	void testIniListAndReturn() {
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		for (int i = 0; i < 3; i++) {
			hospitalList.add(hospitalTestGenerator.initDataAndReturn());
		}
		assertTrue(hospitalList.size() > 0);
	}

}
