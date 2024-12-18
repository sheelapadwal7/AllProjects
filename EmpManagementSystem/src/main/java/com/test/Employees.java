package com.test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Employees {
	public static void main(String[] args) {

		List<Employee> employeeList = Arrays.asList(
				new Employee(1, "Aniket", 35,  "Male", "IT", 2020, 50000),
				new Employee(2, "Rasika", 40,  "Female", "HR", 2018, 60000),
				new Employee(3, "Sameer", 25,  "Male", "Finance", 2019, 45000),
				new Employee(4, "Ankita", 35,  "Female", "IT", 2021, 70000),
				new Employee(5, "Raj",    38,   "Male", "Marketing", 2022, 80000),
				new Employee(6, "Sateesh", 39, "Male", "IT", 2021, 70000),
				new Employee(7, "Sam",     40,  "Male", "Sales", 2021, 70000),
				new Employee(8, "Pratham", 42, "Male", "Product Development", 2017, 75000));

		System.out.println("All Employees:");
		employeeList.stream().forEach(System.out::println);

		// 1. Count of male and female in the organization.
		long maleCount = employeeList.stream().filter(e -> "Male".equalsIgnoreCase(e.getGender())).count();
		long femaleCount = employeeList.stream().filter(e -> "Female".equalsIgnoreCase(e.getGender())).count();

		System.out.println("\nNumber of Male Employees: " + maleCount);
		System.out.println("Number of Female Employees: " + femaleCount);

		// 2. Print all departments in the organization.
		System.out.println("\nDepartments:");
		employeeList.stream().map(Employee::getDepartment)
		.distinct().forEach(System.out::println);

		// 3. Calculate and print the average age for male and female employees.
		double avgAgeMale = employeeList.stream().
				filter(e -> "Male".equalsIgnoreCase(e.getGender()))
				.mapToInt(Employee::getAge).average().orElse(0);
		double avgAgeFemale = employeeList.stream().
				filter(e -> "Female".equalsIgnoreCase(e.getGender()))
				.mapToInt(Employee::getAge).average().orElse(0);

		System.out.println("\nAverage age of Male Employees: " + avgAgeMale);
		System.out.println("Average age of Female Employees: " + avgAgeFemale);

		// 4. Calculate the highest paid employee in the organization.
		Employee highestPaidEmployee = employeeList.stream().
				                       max(Comparator.comparingDouble(Employee::getSalary))
				                      .orElse(null);
		if (highestPaidEmployee != null) {
			System.out.println("\nHighest Paid Employee: " + highestPaidEmployee);
		} else {
			System.out.println("No employees found.");
		}

		// 5. Get names of employees who joined after 2015.
		List<String> emp = employeeList.stream().
				 filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName)
				.collect(Collectors.toList());
		System.out.println("\nEmployees who joined after 2015:");
		emp.forEach(System.out::println);

		// 6. Count the number of employees in each department.
		Map<String, Long> departmentCount = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println("\nNumber of employees in each department:");
		departmentCount.forEach((department, count) -> System.out.println(department + ": " + count));

		// 7. Count the average salary of each department.
		Map<String, Double> averageSalaryByDepartment = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, 
			    Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("\nAverage salary in each department:");
		averageSalaryByDepartment.forEach((department, avgSalary) -> System.out.println(department + ": " + avgSalary));

		// 8. Get the details of the youngest male employee in Product Development
		// Department.
		Optional<Employee> youngestMale = employeeList.stream().
				 filter(e -> e.getGender().equals("Male"))
				.filter(e -> e.getDepartment().equals("Product Development"))
				.min(Comparator.comparingInt(Employee::getAge));
		youngestMale.ifPresent(
				employee -> System.out.println("\nYoungest Male in Product Development Department: " + employee));

		// 9. Get the employee with the most experience.
		Optional<Employee> mostExperiencedEmployee = employeeList.stream()
				.max(Comparator.comparingInt(e -> LocalDate.now().getYear() - e.getYearOfJoining()));
		mostExperiencedEmployee
				.ifPresent(employee -> System.out.println("\nEmployee with most experience: " + employee));

		// 10. Group employees by gender in the Sales and Marketing department and
		// count.
		Map<String, Long> genderCountInSalesMarketing = employeeList.stream()
				.filter(e -> e.getDepartment().equals("Marketing") || e.getDepartment().equals("Sales"))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		genderCountInSalesMarketing.forEach((gender, count) -> System.out.println(gender + ": " + count));

		// 11. Group by gender and calculate average salary for each gender.
		Map<String, Double> avgSalaryByGender = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
		avgSalaryByGender.forEach(
				(gender, avgSalary) -> System.out.println("\nAverage salary for " + gender + ": " + avgSalary));

		// 12. Group by department and collect employee names in each department.
		Map<String, List<String>> employeesByDepartment = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
		employeesByDepartment.forEach((department, names) -> System.out.println(department + ": " + names));

		
		
		// 13. What is the average salary and total salary of the whole organization?
		double totalSalary = employeeList.stream().mapToDouble(Employee::getSalary).sum();
		OptionalDouble averageSalary = employeeList.stream().mapToDouble(Employee::getSalary).average();
		System.out.println("\nTotal Salary: " + totalSalary);
		averageSalary.ifPresent(avg -> System.out.println("Average Salary: " + avg));

		
		
		
		// 14. Separate employees who are younger or equal to 25 years from those who
		// are older than 25 years.
		Map<Boolean, List<Employee>> separatedByAge = employeeList.stream()
				.collect(Collectors.partitioningBy(employee -> employee.getAge() <= 25));
		List<Employee> youngerOrEqualTo25 = separatedByAge.get(true);
		List<Employee> olderThan25 = separatedByAge.get(false);
		System.out.println("Employees younger or equal to 25 years:");
		youngerOrEqualTo25.forEach(employee -> System.out.println(employee.getName()));
		System.out.println("\nEmployees older than 25 years:");
		olderThan25.forEach(employee -> System.out.println(employee.getName()));

		
		
		
		// 15. Who is the oldest employee in the organization? What is his age, and
		// which department does he belong to?
		Optional<Employee> oldestEmployee = employeeList.stream().max(Comparator.comparingInt(Employee::getAge));
		oldestEmployee.ifPresent(employee -> {
			System.out.println("\nOldest Employee: " + employee);
			System.out.println("Age: " + employee.getAge());
			System.out.println("Department: " + employee.getDepartment());
		});
	}

	
}
