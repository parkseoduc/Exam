package com.fpt.employees;

import com.fpt.employees.model.Employee;
import com.fpt.employees.repository.EmployeeRepository;
import com.fpt.employees.service.EmployeeNotFoundException;
import com.fpt.employees.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(EmployeeService.class)
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void addEmployeesStoresNewEmployee() {
        Employee employee = new Employee(null, "Le Minh", new BigDecimal("1800.00"));

        Employee saved = employeeService.addEmployees(employee);

        assertThat(saved.getId()).isNotNull();
        assertThat(employeeRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void getEmployeesReturnsAllEmployees() {
        employeeService.addEmployees(new Employee(null, "Pham Linh", new BigDecimal("1400.00")));

        assertThat(employeeService.getEmployees()).isNotEmpty();
    }

    @Test
    void updateEmployeeModifiesExistingEmployee() {
        Employee saved = employeeService.addEmployees(new Employee(null, "Old Name", new BigDecimal("900.00")));
        saved.setName("New Name");
        saved.setSalary(new BigDecimal("1100.00"));

        Employee updated = employeeService.updateEmployee(saved);

        assertThat(updated.getName()).isEqualTo("New Name");
        assertThat(updated.getSalary()).isEqualByComparingTo("1100.00");
    }

    @Test
    void updateEmployeeFailsWhenEmployeeDoesNotExist() {
        Employee missing = new Employee(999L, "Missing", new BigDecimal("1000.00"));

        assertThatThrownBy(() -> employeeService.updateEmployee(missing))
                .isInstanceOf(EmployeeNotFoundException.class);
    }
}
