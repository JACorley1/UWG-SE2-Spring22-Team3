package edu.westga.edu.employee_management.viewmodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import edu.westga.edu.employee_management.model.DaySheet;
import edu.westga.edu.employee_management.model.EmployeeManager;
import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.EmployeeRequestManager;
import edu.westga.edu.employee_management.model.PayPeriod;
import edu.westga.edu.employee_management.model.TimeSheet;
import edu.westga.edu.employee_management.model.UserLogin;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class LandingPageViewModel {

	public static final int DATE_COL_INDEX = 0;
	public static final int BUTTON_COL_INDEX = 2;
	public static final int HOURS_COL_INDEX = 1;

	private static final String PROFILE_NOT_FOUND = "Profile could not be found," + System.lineSeparator()
			+ "showing sample profile.";
	private BooleanProperty clockInDisableProperty;
	private BooleanProperty clockOutDisableProperty;
	private BooleanProperty hrViewButtonVisibleProperty;
	private StringProperty employeeNameProperty;
	private StringProperty idProperty;
	private StringProperty firstNameProperty;
	private StringProperty emailProperty;
	private StringProperty phoneProperty;
	private StringProperty middleNameProperty;
	private StringProperty lastNameProperty;
	private StringProperty payPeriodTextProperty;
	private List<ListProperty<Object>> timeProperty;
	private StringProperty numberOfRequestsProperty;
	private StringProperty profileErrorProperty;

	private EmployeeProfile user;
	private PayPeriod currentPayPeriod;
	private TimeSheet currentTimeSheet;
	private EmployeeManager manager;
	private EmployeeRequestManager requestManager;
	private UserLogin login;

	/**
	 * Creates new instance of LandingPageViewModel
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 * 
	 * @param login the
	 */
	public LandingPageViewModel(UserLogin login) {
		this.clockInDisableProperty = new SimpleBooleanProperty();
		this.clockOutDisableProperty = new SimpleBooleanProperty();
		this.employeeNameProperty = new SimpleStringProperty();
		this.hrViewButtonVisibleProperty = new SimpleBooleanProperty();
		this.idProperty = new SimpleStringProperty();
		this.firstNameProperty = new SimpleStringProperty();
		this.emailProperty = new SimpleStringProperty();
		this.phoneProperty = new SimpleStringProperty();
		this.middleNameProperty = new SimpleStringProperty();
		this.lastNameProperty = new SimpleStringProperty();
		this.payPeriodTextProperty = new SimpleStringProperty();
		this.timeProperty = new ArrayList<ListProperty<Object>>();
		this.numberOfRequestsProperty = new SimpleStringProperty();
		this.profileErrorProperty = new SimpleStringProperty();

		this.login = login;
		this.manager = EmployeeManager.getInstance();
		this.requestManager = EmployeeRequestManager.getInstance();

		this.setUser();
	}
	
	/**
	 * Updates user profile data to match the properties
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	public void setProfile() {
		this.user.setMiddleName(this.middleNameProperty.getValue());
		this.user.setEmail(this.emailProperty.getValue());
		this.user.setPhone(this.phoneProperty.getValue());
	}

	/**
	 * Resets properties to model data
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	public void resetProfileValues() {
		this.updateUserInfo();
	}

	/**
	 * Clocks in user
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	public void clockIn() {
		this.user.getTimeSheet(LocalDate.now()).clockIn();
		this.updateHours();
		this.updateClockButtons();
	}
	
	/**
	 * Clocks out
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	public void clockOut() {
		this.user.getTimeSheet(LocalDate.now()).clockOut();
		this.updateHours();
		this.updateClockButtons();
	}

	/**
	 * Gets User Data for opening daily time window
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param rowIndex the index to the row
	 * @return the user data
	 */
	public DaySheet getDailyTimeUserData(int rowIndex) {
		return this.currentTimeSheet.daySheets().get(rowIndex);
	}

	private void setUser() {
		EmployeeProfile profile = this.getProfile();
		if (profile != null) {
			this.user = profile;
		} else {
			this.user = new EmployeeProfile(1, "Sophie", "", "Atelier", "example@gmail.com", "123-456-7890", true,
					"user name", "password");
			this.profileErrorProperty.setValue(PROFILE_NOT_FOUND);
		}

		this.initializeUserInfo();
	}

	private EmployeeProfile getProfile() {
		String userName = this.login.getUsername();
		String pass = this.login.getPassword();
		for (EmployeeProfile currProfile : this.manager.getProfiles()) {
			if (currProfile.getUserName().equalsIgnoreCase(userName)
					|| currProfile.getPassword().equalsIgnoreCase(pass)) {
				return currProfile;
			}
		}
		return null;
	}

	private void initializeUserInfo() {
		this.updateUserInfo();

		LocalDate today = LocalDate.now();
		this.currentPayPeriod = new PayPeriod(today);
		this.currentTimeSheet = this.user.getTimeSheet(today);

		this.payPeriodTextProperty.setValue(this.currentPayPeriod.toString());

		this.initializeTimeSheet();
	}

	private void initializeTimeSheet() {
		this.updateClockButtons();
		this.initializePeriodDates();
		this.initializeHours();

	}

	private void initializeHours() {
		Map<Integer, DaySheet> timeSheet = this.currentTimeSheet.getTimeSheet();
		Collection<DaySheet> daySheets = timeSheet.values();

		List<DoubleProperty> timeProperties = new ArrayList<DoubleProperty>();
		List<BooleanProperty> disabledProperties = new ArrayList<BooleanProperty>();

		for (DaySheet sheet : daySheets) {
			double hours = sheet.getHoursWorked();
			timeProperties.add(new SimpleDoubleProperty(hours));
			disabledProperties.add(new SimpleBooleanProperty(this.isTimeButtonDisabled(sheet)));
		}

		ListProperty<Object> hoursList = new SimpleListProperty<Object>(
				FXCollections.observableArrayList(timeProperties));
		ListProperty<Object> disabledList = new SimpleListProperty<Object>(
				FXCollections.observableArrayList(disabledProperties));
		this.timeProperty.add(hoursList);
		this.timeProperty.add(disabledList);
	}

	private void initializePeriodDates() {
		List<LocalDate> days = this.currentPayPeriod.toList();
		List<ObjectProperty<LocalDate>> dayProperties = new ArrayList<ObjectProperty<LocalDate>>();

		for (LocalDate day : days) {
			dayProperties.add(new SimpleObjectProperty<LocalDate>(day));
		}

		ListProperty<Object> list = new SimpleListProperty<Object>(FXCollections.observableArrayList(dayProperties));
		this.timeProperty.add(list);
	}

	private void updateUserInfo() {
		this.hrViewButtonVisibleProperty.set(this.user.isHR());
		this.idProperty.setValue(String.valueOf(this.user.getID()));
		this.firstNameProperty.setValue(this.user.getFirstName());
		this.emailProperty.setValue(this.user.getEmail());
		this.phoneProperty.setValue(this.user.getPhone());
		this.middleNameProperty.setValue(this.user.getMiddleName());
		this.lastNameProperty.setValue(this.user.getLastName());
		this.employeeNameProperty.setValue(this.user.getFirstName() + " " + this.user.getLastName());
		this.numberOfRequestsProperty.setValue(String.valueOf(this.requestManager.getNumberOfRequests()));
	}

	private void updatePeriodDates() {
		List<LocalDate> days = this.currentPayPeriod.toList();
		ListProperty<Object> dates = this.timeProperty.get(0);
		for (int i = 0; i < dates.getSize(); i++) {
			Object object = dates.get(i);
			ObjectProperty<LocalDate> date = (ObjectProperty<LocalDate>) object;
			date.setValue(days.get(i));
		}
	}

	private void updateHours() {
		Map<Integer, DaySheet> timeSheet = this.currentTimeSheet.getTimeSheet();
		List<DaySheet> daySheets = new ArrayList<DaySheet>(timeSheet.values());
		ListProperty<Object> hours = this.timeProperty.get(1);
		ListProperty<Object> disabled = this.timeProperty.get(2);
		
		for (int i = 0; i < hours.getSize(); i++) {
			DoubleProperty hour = (DoubleProperty) hours.get(i);
			BooleanProperty isDisable = (BooleanProperty) disabled.get(i);
			
			DaySheet sheet = daySheets.get(i);
			double hoursWorked = sheet.getHoursWorked();
			hour.setValue(hoursWorked);
			isDisable.set(this.isTimeButtonDisabled(sheet));
		}
	}

	private void updateClockButtons() {
		boolean hasOpenTime = this.currentTimeSheet.hasOpenTime();
		this.clockInDisableProperty.setValue(hasOpenTime);
		this.clockOutDisableProperty.setValue(!hasOpenTime);
	}

	private boolean isTimeButtonDisabled(DaySheet sheet) {
		double hoursWorked = sheet.getHoursWorked();
		return !(hoursWorked > 0 || sheet.size() > 0);
	}

	/**
	 * Gets the clockInEnabledProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the clockInEnabledProperty
	 */
	public BooleanProperty getClockInDisabledProperty() {
		return this.clockInDisableProperty;
	}

	/**
	 * Gets the clockOutEnabledProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the clockOutEnabledProperty
	 */
	public BooleanProperty getClockOutDisabledProperty() {
		return this.clockOutDisableProperty;
	}

	/**
	 * Gets the hrViewVisibleProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the hrViewVisibleProperty
	 */
	public BooleanProperty getHrViewButtonVisibleProperty() {
		return this.hrViewButtonVisibleProperty;
	}

	/**
	 * Gets the employeeNameProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the employeeNameProperty
	 */
	public StringProperty getEmployeeNameProperty() {
		return this.employeeNameProperty;
	}

	/**
	 * Gets the idProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the idProperty
	 */
	public StringProperty getIdProperty() {
		return this.idProperty;
	}

	/**
	 * Gets the firstNameProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the firstNameProperty
	 */
	public StringProperty getFirstNameProperty() {
		return this.firstNameProperty;
	}

	/**
	 * Gets the emailProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the emailProperty
	 */
	public StringProperty getEmailProperty() {
		return this.emailProperty;
	}

	/**
	 * Gets the phoneProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the phoneProperty
	 */
	public StringProperty getPhoneProperty() {
		return this.phoneProperty;
	}

	/**
	 * Gets the middleNameProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the middleNameProperty
	 */
	public StringProperty getMiddleNameProperty() {
		return this.middleNameProperty;
	}

	/**
	 * Gets the lastNameProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the lastNameProperty
	 */
	public StringProperty getLastNameProperty() {
		return this.lastNameProperty;
	}

	/**
	 * Gets the payPeriodProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the payPeriodProperty
	 */
	public StringProperty getPayPeriodTextProperty() {
		return this.payPeriodTextProperty;
	}

	/**
	 * Gets the timeProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the timeProperty
	 */
	public List<ListProperty<Object>> getTimeProperty() {
		return this.timeProperty;
	}

	/**
	 * Gets the numberOfRequestsProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the numberOfRequestsProperty
	 */
	public StringProperty getNumberOfRequestsProperty() {
		return this.numberOfRequestsProperty;
	}

	/**
	 * Gets the profileErrorProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the profileErrorProperty
	 */
	public StringProperty getProfileErrorProperty() {
		return this.profileErrorProperty;
	}


}
