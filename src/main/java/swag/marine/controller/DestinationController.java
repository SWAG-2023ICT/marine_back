package swag.marine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Destination;
import swag.marine.service.DestinationService;

@RequestMapping("/marine/destination")
@RestController
@Tag(name = "destination",description = "주소 관련 API")
public class DestinationController {
    @Autowired
    DestinationService destinationService;

    @Operation(summary = "주소 추가",description = "사용자가 주소를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "주소가 정상적으로 추가되었습니다.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "주소추가에 실패했습니다.",
                content = @Content(mediaType = "application/json"))
    })
    @PostMapping(value = "/addDestination",consumes = MediaType.APPLICATION_JSON_VALUE
        ,produces = MediaType.APPLICATION_JSON_VALUE)
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
