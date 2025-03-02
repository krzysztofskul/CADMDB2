package pl.krzysztofskul.cadmdb.hospital;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thedeanda.lorem.LoremIpsum;

import pl.krzysztofskul.cadmdb.hospital.department.DepartmentTestGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
class HospitalServiceTest {

	@Autowired
	HospitalRepo hospitalRepo;
	@Autowired
	HospitalService hospitalService;
	@Autowired
	HospitalTestGenerator hospitalTestGenerator;
	@Autowired
	DepartmentTestGenerator departmentTestGenerator;
	
	Hospital hospital;
	List<Hospital> hospitalList;
	
	@Test
	void testSaveAndReturn1() {
		hospital = new Hospital();
		hospital.setName(LoremIpsum.getInstance().getName());
		hospital = hospitalRepo.save(hospital);
		assertTrue(hospital.getId() != null && hospital.getId() != 0);
	}
	
	@Test
	void testSaveAndReturn2() {
		hospitalList = hospitalTestGenerator.iniListAndReturn();
		for (Hospital hospital : hospitalList) {
			hospital = hospitalService.saveAndReturn(hospital);
			assertTrue(hospital.getId() != null && hospital.getId() != 0);
		}
	}
	
	@Test
	void testLoadAll() {
		List<Hospital> hospitalListFromDB = hospitalService.loadAll();
		assertTrue(hospitalListFromDB != null && hospitalListFromDB.size() > 0);
		for (Hospital hospital : hospitalListFromDB) {
			assertTrue(hospital.getId() > 0); 
		}
	}
	
	@Test
	void test() {
		Hospital hospital = hospitalService.saveAndReturn(hospitalTestGenerator.initDataAndReturn());
		Hospital hospitalFromDb = hospitalService.loadByIdWithDepartments(hospital.getId());
		assertTrue(hospitalFromDb.getDepartmentList().size() > 0);
		//assertTrue(hospitalFromDb.getAddress() != null);
	}
	

}