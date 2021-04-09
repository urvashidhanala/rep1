package com.urvashi.healthCare.Appointment.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.urvashi.healthCare.Appointment.pojo.Patient;
import com.urvashi.healthCare.Appointment.repo.PatientRepo;



@RestController
public class PatientController {
	@Autowired
	private PatientRepo patientRepo;
	@PostMapping(value="savePatient")
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient)
	{	System.out.println(patient);
	 patientRepo.save(patient);
		return new ResponseEntity<Patient>(HttpStatus.CREATED);
		
	}
	@GetMapping("getPatient/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Integer id)
	{
		System.out.println("Requested Id"+id);
		Optional<Patient> opt = patientRepo.findById(id);
		if(opt.isPresent())
		{
			return new ResponseEntity<Patient>(opt.get(),HttpStatus.OK);
		}
		return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("getAllPatients")
	public ResponseEntity<List<Patient>> getAllPatient()
	{
		List<Patient> patients = patientRepo.findAll();
		if(patients.isEmpty())
		{
			return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<List<Patient>>(patients,HttpStatus.OK);
		
	}
	@PutMapping("updatePatient")
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient ptt)
	{
		patientRepo.save(ptt);
		return new ResponseEntity<Patient>(HttpStatus.OK);
	}
	@DeleteMapping("deletePatient/{id}")
	public ResponseEntity<Patient> deletePatient(@PathVariable Integer id)
	{
		patientRepo.deleteById(id);
		return new ResponseEntity<Patient>(HttpStatus.OK);
	}
	@GetMapping("findPatientByName/{name}")
	public ResponseEntity<List<Patient>> getPatientsByName(@PathVariable String name)

	{List<Patient> list = patientRepo.findByName(name);
		return new ResponseEntity<List<Patient>>(list,HttpStatus.OK);
		
	}
	@GetMapping("findPatientByNameLike/{name}")
	public ResponseEntity<List<Patient>> getPatientsByNameLike1(@PathVariable String name)

	{
		//List<Patient> list = patientRepo.findByNameLike("%"+name+"%");
		List<Patient> list = patientRepo.getPatientsByNameLike("%"+name+"%");
		return new ResponseEntity<List<Patient>>(list,HttpStatus.OK);
		
	}
	
	
	
	

}
