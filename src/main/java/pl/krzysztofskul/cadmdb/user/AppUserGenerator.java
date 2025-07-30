package pl.krzysztofskul.cadmdb.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.init.InitDataGenerator;

@Service
public class AppUserGenerator implements InitDataGenerator<AppUser> {

	private List<AppUser> appUserTestList = new ArrayList<AppUser>();
	
	@Override
	public AppUser initDataAndReturn() {
		return new AppUser();
	}

	@Override
	public List<AppUser> initListAndReturn() {
		
		if (appUserTestList == null || appUserTestList.size() == 0) {
			//create user with ROLE_USER_GUEST_INVESTOR
			appUserTestList.add(this.newUserGuestInvestor());
			//create user with ROLE_USER_GUEST_HOSPITAL
			appUserTestList.add(this.newUserGuestHospital());
			//create user with ROLE_USER_GUEST_MANUFACTURER
			appUserTestList.add(this.newUserGuestManufacturer());
			//create user with ROLE_USER_GUEST_DESIGNER
			appUserTestList.add(this.newUserGuestDesigner());
		}
		return this.appUserTestList;
	}

	private AppUser newUserGuestInvestor() {
		AppUser user = this.initDataAndReturn();
		user.setUsername("userguestinvestor");
		user.setPassword("password");
		user.setRole("ROLE_USER_GUEST_INVESTOR");
		return user;
	}
	
	private AppUser newUserGuestHospital() {
		AppUser user = this.initDataAndReturn();
		user.setUsername("userguesthospital");
		user.setPassword("password");
		user.setRole("ROLE_USER_GUEST_HOSPITAL");
		return user;
	}

	private AppUser newUserGuestManufacturer() {
		AppUser user = this.initDataAndReturn();
		user.setUsername("userguestmanufacturer");
		user.setPassword("password");
		user.setRole("ROLE_USER_GUEST_MANUFACTURER");
		return user;
	}
	private AppUser newUserGuestDesigner() {
		AppUser user = this.initDataAndReturn();
		user.setUsername("userguestdesigner");
		user.setPassword("password");
		user.setRole("ROLE_USER_GUEST_DESIGNER");
		return user;
	}

}
