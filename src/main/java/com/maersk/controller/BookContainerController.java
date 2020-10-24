package com.maersk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
 
import com.maersk.model.ShipmentDetails;
import com.maersk.service.BookContainerService;
import com.maersk.vo.CheckAvailabilityVO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@RestController
@RequestMapping("/api/bookings/")
public class BookContainerController {
    @Autowired
    private BookContainerService bookContainerService;
 
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String, String> create(@RequestBody ShipmentDetails sd) {
    	String bookingRef = bookContainerService.create(sd);
    	Map<String, String> refMap = new HashMap<String, String>();
    	refMap.put("bookingRef",bookingRef);
    	return refmap;
    } 
 
    @RequestMapping(value = "/checkAvailable", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> checkAvailablability(@RequestBody ShipmentDetails sd) {
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ShipmentDetails> entity = new HttpEntity<ShipmentDetails>(sd,headers);
        
        CheckAvailabilityVO vo = restTemplate.exchange(
           "https://maersk.com/api/bookings/checkAvailable", HttpMethod.POST, entity, CheckAvailabilityVO.class).getBody();
        Map<String, Boolean> refMap = new HashMap<String, Boolean>();
        if(vo.getAvailableSpace() > 0) {
        	refMap.put("available",true); 
        } else {
        	refMap.put("available",false); 
        }
        return refMap;
     
    }

 
}
