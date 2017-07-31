package com.interview.rest;

import com.interview.dao.MessageRepository;
import com.interview.model.MessageModel;
import com.interview.services.BroadCaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 * This class handles the rest requests
 * @todo implement rate limiter
 *
 */
@RestController
@RequestMapping("/api/1.0")
public class CurrencyRestController {
    /*
    * Declare variables
    * */
    private static final String API_URL = "/api/1.0/";
    private static final int API_RESPONSE_RATE = 10000; // 5000 messages per minute
    private final AtomicInteger counter = new AtomicInteger();

    private static long prevTime = -1;

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody MessageModel input) {
        if(isAllowedRequest()){ //if within limit accept and process
            messageRepository.save(input); //save the message to db
            BroadCaster.broadcast(input);
            return ResponseEntity.ok().build();
        }
        //System.err.println("===> Rejected ");
        //otherwise reject the message until limit is not exceeded
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Exceed API rate per minute");
    }


    //our simple rate limiter for the api
    private boolean isAllowedRequest(){
        //check if prevtime is initialized
        if(prevTime <0){
            prevTime = System.nanoTime();
        }
        //set time difference
        long diffNanos = System.nanoTime() - prevTime;
        //if the difference between the two is less than a minute we check the count received
        if(TimeUnit.NANOSECONDS.toMillis(diffNanos) <= 60000.0f){
            if(counter.get() <= API_RESPONSE_RATE){//check for count
                counter.incrementAndGet();
                return true;
            }
        }else{ //if it is greater than a minute, reset the prevtime and the counter
            counter.set(0); //reset counter
            counter.incrementAndGet(); //increment since we are going to allow this request
            prevTime = System.nanoTime();
            return true;
        }
        return false;
    }


}
