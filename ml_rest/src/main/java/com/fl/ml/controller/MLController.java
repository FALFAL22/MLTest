package com.fl.ml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fl.ml.exception.MLException;
import com.fl.ml.model.Satellite;
import com.fl.ml.request.InputRequest;
import com.fl.ml.response.MLResponse;
import com.fl.ml.service.TrilateracionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Api ejercicio ML" )
@RestController
public class MLController {

	@Autowired
	TrilateracionService trilateracionService;
	
	@ApiOperation(value = "Endpoint para comprobar comprobar que el api levanto ", response = ResponseEntity.class, tags = "/info")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Suceess|OK"),
		    @ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("/info")
    public String hello() {
        return "Status ok";
    }  
	
	@ApiOperation(value = "Endpoint para obtener mensaje y ubicación ", response = ResponseEntity.class, tags = "/top_secret")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Suceess|OK"),
	    @ApiResponse(code = 404, message = "Not Found") })
    @PostMapping("/topsecret")
    public ResponseEntity<MLResponse> postResponse(@RequestBody InputRequest inputRequest) {
 	
        try {
        	MLResponse mlResponse = new MLResponse(trilateracionService.getLocation(inputRequest.getSatellites()), trilateracionService.getMessage(inputRequest.getSatellites()));
            return ResponseEntity.status(HttpStatus.OK).body(mlResponse);
        } catch (MLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@ApiOperation(value = "Endpoint para isertar de a un satelite ", response = ResponseEntity.class, tags = "/topsecret_split")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Suceess|OK"),
	    @ApiResponse(code = 404, message = "Not Found") })
	@PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<Satellite> postResponseSplit(@PathVariable("satellite_name") String satelliteName, @RequestBody Satellite inputRequest) {
		try {		
			inputRequest.setName(satelliteName);
			trilateracionService.insertSatelite(inputRequest);        	
            return ResponseEntity.status(HttpStatus.OK).body(inputRequest);
        } catch (MLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
	}
	
	@ApiOperation(value = "Endpoint para borrar satelites ", response = ResponseEntity.class, tags = "/delete")
	@DeleteMapping("/delete")
	public void delete() {
		trilateracionService.deleteSatelite();
	}
	
	@ApiOperation(value = "Endpoint para obtener las coordenadas ", response = ResponseEntity.class, tags = "/topsecret_split")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 404, message = "Not Found") })
    @GetMapping("/topsecret_split")
    public ResponseEntity<MLResponse> getResponseSplit() {
		MLResponse mlResponse = null;
        try {
        	List<Satellite> satellites = trilateracionService.obtenerSatelites();
        	mlResponse = new MLResponse(trilateracionService.getLocation(satellites), trilateracionService.getMessage(satellites));
            return ResponseEntity.status(HttpStatus.OK).body(mlResponse);
        } catch (MLException e) {      
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mlResponse);
        }
    }
}
