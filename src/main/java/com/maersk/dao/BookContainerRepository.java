package com.maersk.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.maersk.model.ShipmentDetails;

import reactor.core.publisher.Flux;
 
public interface BookContainerRepository extends ReactiveMongoRepository<ShipmentDetails, Integer> {
	

}