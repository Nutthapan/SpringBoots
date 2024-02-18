package com.example.Employee.repository;

import com.example.Employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryimpl implements EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<Employee> findAllEmployees() {

            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM employees ");
            System.out.println(sql);
            return jdbcTemplate.query(sql.toString(), mapSqlParameterSource,
                    new BeanPropertyRowMapper<Employee>(Employee.class));
        }


    public Employee findEmployeeById(Long id) {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            StringBuilder sql = new StringBuilder();
            sql.append ("SELECT * FROM employees WHERE EmployeeId = :id");
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("id", id);

        System.out.println(sql);
        List<Employee> list = jdbcTemplate.query(sql.toString(), parameters, new BeanPropertyRowMapper<>(Employee.class));

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO employees (EmployeeId, FirstName, LastName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) ");
        sql.append("VALUES (:employeeId, :firstName, :lastName, :title, :reportsTo, :birthDate, :hireDate, :address, :city, :state, :country, :postalCode, :phone, :fax, :email)");

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("employeeId", employee.getEmployeeId());
        parameters.addValue("firstName", employee.getFirstName());
        parameters.addValue("lastName", employee.getLastName());
        parameters.addValue("title", employee.getTitle());
        parameters.addValue("reportsTo", employee.getReportsTo());
        parameters.addValue("birthDate", employee.getBirthDate());
        parameters.addValue("hireDate", employee.getHireDate());
        parameters.addValue("address", employee.getAddress());
        parameters.addValue("city", employee.getCity());
        parameters.addValue("state", employee.getState());
        parameters.addValue("country", employee.getCountry());
        parameters.addValue("postalCode", employee.getPostalCode());
        parameters.addValue("phone", employee.getPhone());
        parameters.addValue("fax", employee.getFax());
        parameters.addValue("email", employee.getEmail());

        jdbcTemplate.update(sql.toString(), parameters, keyHolder);


        if (keyHolder.getKey() != null) {
            Long generatedEmployeeId = keyHolder.getKey().longValue();
            employee.setEmployeeId(generatedEmployeeId);
        }

        return employee;
    }

    @Override
    public void deleteEmployee(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM employees WHERE EmployeeId = :id");
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        int rowsAffected = jdbcTemplate.update(sql.toString(), parameters);
        if (rowsAffected > 0 ){
            System.out.println("Delete successful");
        }
        else {
            System.out.println("No records found for deletion");
        }
    }
}
