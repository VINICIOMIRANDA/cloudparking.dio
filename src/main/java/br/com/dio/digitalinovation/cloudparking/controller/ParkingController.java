package br.com.dio.digitalinovation.cloudparking.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dio.digitalinovation.cloudparking.controller.dto.ParkingDTO;
import br.com.dio.digitalinovation.cloudparking.controller.mapper.ParkingMapper;
import br.com.dio.digitalinovation.cloudparking.model.Parking;
import br.com.dio.digitalinovation.cloudparking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	
	private final ParkingService parkingService ;
	private final ParkingMapper parkingMapper;
	
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}
	
	@GetMapping()
	public List<ParkingDTO> findAll(){
		List<Parking> parkingList =  parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		
		return result;

	}
	




}
