package edu.westga.edu.employee_management.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee manager class
 * 
 * @author Miguel Campos
 * @version 2/17/22
 */
 public class EmployeeManager {
	 private List<EmployeeProfile> profiles;
	 
	 /** Create a new instance of EmployeeManager
	 * 
	 * @precondition none
	 * @postcondition this.profiles != null
	 * 
	 */
	 public EmployeeManager() {
		 this.profiles = new ArrayList<EmployeeProfile>();
	 }
	 
	 /** Returns a list of the names for all the employees
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return list of the names for employees
	 */
	 public List<String> getEmployeesName() {
		 List<String> names = new ArrayList<String>();
		 
		 for (EmployeeProfile current : this.profiles) {
			 names.add(current.getFirstName() + " " + current.getLastName());
		 }
		 return names;
	 }
	 
	 /** Add a new system with the specified credentials to the system
	 * 
	 * @precondition title != null && !title.isEmpty() &&
	 * 				 firstName != null && !firstName.isEmpty() &&
	 * 				 midName != null && !midName.isEmpty() &&
	 * 				 lasName != null && !lasName.isEmpty() &&
	 * 				 email != null && !email.isEmpty() &&
	 * 				 phone != null && !phone.isEmpty()
	 * @postcondition getSystemNames().contains(firstName + " " + lasName)
	 * 
	 * @param title the employee's title
	 * @param firstName the employee's first name
	 * @param midName the employee's middle name
	 * @param lastName the employee's last name
	 * @param email the employee's email
	 * @param phone the employee's phone
	 * 
	 * @return true  if employee added successfully
	 * 		   false if employee not added successfully
	 */
	 public boolean addNewEmployee(String title, String firstName, String midName, String lastName, String email, String phone) {
		 if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (midName == null || midName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (phone == null || phone.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		//Check for duplicate
		return this.profiles.add(new EmployeeProfile(title, firstName, midName, lastName, email, phone));
	 }
	 
	 /** Update an existing profile with the specified credentials to the system
	 * 
	 * @precondition title != null && !title.isEmpty() &&
	 * 				 firstName != null && !firstName.isEmpty() &&
	 * 				 midName != null && !midName.isEmpty() &&
	 * 				 lasName != null && !lasName.isEmpty() &&
	 * 				 email != null && !email.isEmpty() &&
	 * 				 phone != null && !phone.isEmpty() &&
	 * 				 getEmployeeNames().contains(firstName + " " + lasName)
	 * @postcondition getEmployeeNames().contains(firstName + " " + lasName)
	 * 
	 * @param title the employee's title
	 * @param firstName the employee's first name
	 * @param midName the employee's middle name
	 * @param lastName the employee's last name
	 * @param email the employee's email
	 * @param phone the employee's phone
	 * 
	 * @return true  if profile updated successfully
	 * 		   false if profile not updated successfully
	 */
	 public boolean updateProfile(String title, String firstName, String midName, String lastName, String email, String phone) {
		 if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (midName == null || midName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (phone == null || phone.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		EmployeeProfile newProfile = this.getProfile(firstName, lastName);
		
		if(newProfile == null) {
			throw new IllegalStateException("That profile does not exist");
		} else if(!this.profiles.remove(newProfile)) {
			return false;
		} else {
			return this.profiles.add(new EmployeeProfile(title, firstName, midName, lastName, email, phone));
		}
	 }
	 
	 /** Remove a profile with the specified name
	 * 
	 * @precondition firstName != null && !firstName.isEmpty() &&
	 * 				 lasName != null && !lasName.isEmpty()
	 * @postcondition !getEmployeeNames().contains(firstName + " " + lasName)
	 * 			
	 * 
	 * @param firstName the employee's first name
	 * @param lastName the employee's last name
	 * 
	 * @return true  if profile removed successfully
	 * 		   false if profile not removed successfully
	 */
	 public boolean removeProfile(String firstName, String lastName) {
		 if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		
		EmployeeProfile newProfile = this.getProfile(firstName, lastName);
		if(newProfile == null) {
			throw new IllegalStateException("That profile does not exist");
		} else {
			return this.profiles.remove(newProfile);
		}
	 }
	 	
	 private EmployeeProfile getProfile(String firstName, String lasName) {
		 EmployeeProfile profile = null;
		 
		 for (EmployeeProfile current : this.profiles) {
			 if (current.getFirstName().equals(firstName) && current.getLastName().equals(lasName)) {
				 profile = current;
			 }
		 }
		 return profile;
	 }
	 
 }	