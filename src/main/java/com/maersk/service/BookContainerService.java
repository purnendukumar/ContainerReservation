package com.maersk.service;

import com.maersk.model.ShipmentDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface BookContainerService
{
    String create(ShipmentDetails sd);
     

}