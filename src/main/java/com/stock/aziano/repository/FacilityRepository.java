package com.stock.aziano.repository;


import com.stock.aziano.models.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

}