package com.maersk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
 
import com.maersk.dao.BookContainerRepository;
import com.maersk.model.ShipmentDetails;
 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@Service
public class BookContainerServiceImpl implements BookContainerService {
     
    @Autowired
    BookContainerRepository bookContainerRepository;
 
    public String create(ShipmentDetails sd) {
    	
    	ShipmentDetails sd_t = sd;
    	sd_t.setTimestamp(LocalDateTime.now());
    	ShipmentDetails sd_u = bookContainerRepository.save(sd_t).subscribe();
    	// aurtoincrement and stating value of booking ref will be handled from mongodb end
    	//Although mongodb provides MongoRepository which we can extend to override getNextSequence to do it progrmatically too
    	return sd_t.getBookingRef();
    	
    }
 
    
 
}