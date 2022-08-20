package br.com.dio.digitalinovation.cloudparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dio.digitalinovation.cloudparking.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

}
