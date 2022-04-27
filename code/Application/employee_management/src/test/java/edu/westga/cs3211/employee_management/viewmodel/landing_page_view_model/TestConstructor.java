package edu.westga.cs3211.employee_management.viewmodel.landing_page_view_model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.viewmodel.LandingPageViewModel;

class TestConstructor {

	@Test
	void testValidConstruction() {
		EmployeeProfile profile = new EmployeeProfile(1, "Sophie", "", "Atelier", "example@gmail.com", "123-456-7890", true,
				"user name", "password");
		LandingPageViewModel viewmodel = new LandingPageViewModel(profile);
		
		assertAll(() -> assertEquals(profile.getEmail(), viewmodel.getEmailProperty().get()),
						() -> assertEquals(profile.getFirstName(), viewmodel.getFirstNameProperty().get()),
						() -> assertEquals(profile.getLastName(), viewmodel.getLastNameProperty().get()), 
						() -> assertEquals(profile.getID(), Integer.valueOf(viewmodel.getIdProperty().get())),
						() -> assertEquals(profile.getMiddleName(), viewmodel.getMiddleNameProperty().get()),
						() -> assertEquals(profile.isHR(), viewmodel.getHrViewButtonVisibleProperty().get()),
						() -> assertFalse(viewmodel.getClockInDisabledProperty().get()),
						() -> assertTrue(viewmodel.getClockOutDisabledProperty().get()),
						() -> assertEquals(profile.getPhone(), viewmodel.getPhoneProperty().get()));
	}

}
