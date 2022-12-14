package br.com.dio.digitalinovation.cloudparking.controller;

import java.util.List;

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

import br.com.dio.digitalinovation.cloudparking.controller.dto.ParkingCreateDTO;
import br.com.dio.digitalinovation.cloudparking.controller.dto.ParkingDTO;
import br.com.dio.digitalinovation.cloudparking.controller.mapper.ParkingMapper;
import br.com.dio.digitalinovation.cloudparking.model.Parking;
import br.com.dio.digitalinovation.cloudparking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parking")
@Api(tags= "ParkingController")
public class ParkingController {

	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;

	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}

	@GetMapping()
	@ApiOperation("Find all parkings")
	public ResponseEntity<List<ParkingDTO>> findAll() {
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);

		return ResponseEntity.ok(result);

	}

	@GetMapping("/{id}")
	@ApiOperation("Find for ID parking")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
		Parking parking = (Parking) parkingService.findById(id);
	    ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(result);

	}
	
	@PostMapping
	@ApiOperation("Create parking")
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		var parking = parkingService.create(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);

	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Delete parking")
	public ResponseEntity delete(@PathVariable String id) {
		parkingService.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping("/{id}")
	@ApiOperation("Update parking")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto) {
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		var parking = parkingService.update(id,parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.OK).body(result);

	}
	
	@PostMapping("/{id}")
	@ApiOperation("checkout parking")
	public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
		Parking  parking = parkingService.checkOut(id);
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));

	}
	
	
	

}
