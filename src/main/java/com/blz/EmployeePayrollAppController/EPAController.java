package com.blz.EmployeePayrollApp.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blz.EmployeePayrollApp.DTO.EPADTO;
import com.blz.EmployeePayrollApp.DTO.ResponseDTO;
import com.blz.EmployeePayrollApp.Model.EPAData;
import com.blz.EmployeePayrollApp.Service.IEPAService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EPAController {

	@Autowired
	private IEPAService employeePayrollService;

	@RequestMapping(value = { "", "/", "get" })
	public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
		List<EPAData> empData = null;
		empData = employeePayrollService.getEPAData();
		ResponseDTO resDTO = new ResponseDTO("Get Call Success !!!", empData);
		return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
	}

	@GetMapping("/get/{empId}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
		EPAData empData = null;
		empData = employeePayrollService.getEPADataById(empId);
		ResponseDTO resDTO = new ResponseDTO("Get Call for id Successful:", empData);
		return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
	}

	@GetMapping("/department/{department}")
	public ResponseEntity<ResponseDTO> getEmployeesByDepartment(@PathVariable("department") String department) {
		List<EPAData> employeesByDepartment = employeePayrollService.getEmployeesByDepartment(department);
		ResponseDTO responseDTO = new ResponseDTO("Get call for the department successful: ", employeesByDepartment);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createEmployeePayrollData(@Valid @RequestBody EPADTO employeePayrollDTO) {
		log.debug("Employee DTO: " + employeePayrollDTO.toString());
		EPAData empData = null;
		empData = employeePayrollService.createEPAData(employeePayrollDTO);
		ResponseDTO resDTO = new ResponseDTO("Created Employee Payroll Data Successfully:", empData);
		return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
	}

	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
			@Valid @RequestBody EPADTO employeePayrollDTO) {
		EPAData empData = null;
		empData = employeePayrollService.updateEPAData(empId, employeePayrollDTO);
		ResponseDTO resDTO = new ResponseDTO("Updated Employee Payroll Data Successfully: ", empData);
		return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
		employeePayrollService.deleteEPAData(empId);
		ResponseDTO resDTO = new ResponseDTO("Deleted Successfully", "Deleted id: " + empId);
		return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
	}
}