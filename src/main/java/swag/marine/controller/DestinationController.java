package swag.marine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Destination;
import swag.marine.service.DestinationService;

@RequestMapping("/marine/destination")
@RestController
public class DestinationController {
    @Autowired
    DestinationService destinationService;

    @PostMapping("/addDestination")
    public ResponseEntity addDestination(@RequestBody Destination destination)  {
       if(destinationService.addDestination(destination) == 1){
           return ResponseEntity.status(HttpStatus.CREATED).build();
       }else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }

    }

    @PostMapping("/updateDestination")
    public ResponseEntity updateDestination(@RequestBody Destination destination)  {
        if(destinationService.updateDestination(destination) == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/deleteDestination")
    public ResponseEntity deleteDestination(@RequestParam int destinationId)  {
        if(destinationService.deleteDestination(destinationId) == 1){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
