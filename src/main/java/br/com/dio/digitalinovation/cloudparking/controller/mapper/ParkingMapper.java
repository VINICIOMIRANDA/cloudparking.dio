package br.com.dio.digitalinovation.cloudparking.controller.mapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.dio.digitalinovation.cloudparking.controller.dto.ParkingDTO;
import br.com.dio.digitalinovation.cloudparking.model.Parking;

@Component
public class ParkingMapper {

	private static final ModelMapper MODEL_HAPPER = new ModelMapper();

	public ParkingDTO parkingDTO(Parking parking) {
		return MODEL_HAPPER.map(parking, ParkingDTO.class);
	}

	public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
		return parkingList.stream().map(this::parkingDTO).collect(Collectors.toList());
	}

}
