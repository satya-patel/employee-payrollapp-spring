package com.blz.EmployeePayrollAppController;

import java.util.List;

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

import com.blz.EmployeePayrollAppDTO.EPADTO;
import com.blz.EmployeePayrollAppDTO.ResponseDTO;
import com.blz.EmployeePayrollAppModel.EPAData;
import com.blz.EmployeePayrollAppService.IEPAService;

@RestController
@RequestMapping("/employeepayrollservice")
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

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createEmployeePayrollData(@RequestBody EPADTO employeePayrollDTO) {
		EPAData empData = null;
		empData = employeePayrollService.createEPAData(employeePayrollDTO);
		ResponseDTO resDTO = new ResponseDTO("Created Employee Payroll Data Successfully:", empData);
		return new ResponseEntity<ResponseDTO>(resDTO, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@RequestBody EPADTO employeePayrollDTO) {
		EPAData empData = null;
		empData = employeePayrollService.updateEPAData(employeePayrollDTO);
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