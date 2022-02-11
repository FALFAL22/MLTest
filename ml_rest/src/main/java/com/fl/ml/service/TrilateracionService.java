package com.fl.ml.service;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fl.ml.entity.SatelliteEntity;
import com.fl.ml.exception.MLException;
import com.fl.ml.model.Posicion;
import com.fl.ml.model.Satellite;
import com.fl.ml.repository.SatellitesRepository;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;




@Service
public class TrilateracionService {
	
	@Autowired
    SatellitesRepository satellitesRepository;

	public Posicion getLocation(List<Satellite> satelites) throws MLException {        
		
		
		List<Double> listD = satelites.stream().map(Satellite::getDistance).collect(Collectors.toList());
		
		if (listD.size() != 3) {
            throw new MLException("Se necesitan datos completos de los 3 satelites");
        }
		
        double[] distancias = listD.stream().mapToDouble(d -> d).toArray();
        double[][] locations = {{-500, -200},{100, -100},{500, 100}};
        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(locations, distancias);
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        solver.solve().getPoint().toArray();
        double[] point = solver.solve().getPoint().toArray();
        return new Posicion(point[0], point[1]);
    }
	
	 public String getMessage(List<Satellite> satelites) throws MLException{
		 
		 if (satelites.size() != 3) {
	            throw new MLException("Se necesitan datos completos de los 3 satelites");
	     }
		 if(new TreeSet<Integer>(
				 List.of(
				 satelites.get(0).getMessage().size(),
				 satelites.get(1).getMessage().size(),
				 satelites.get(2).getMessage().size()))
				 .size() != 1) {
			 throw new MLException("Se necesitan datos completos de los 3 satelites");
			 
		 }
		 List<String> result = new ArrayList<>();
		 
	     for (Satellite s : satelites) {
	            for (int i = 0; i < s.getMessage().size(); i++) {
	                if (result.size() <= i)
	                    result.add("");
	                if (!"".equals(s.getMessage().get(i))) {
	                    result.set(i, s.getMessage().get(i));
	                }
	            }
         }
        return String.join(" ", result);

	 }
	 
	 public void insertSatelite (Satellite satelite) throws MLException{
		 if(satellitesRepository.existsByName(satelite.getName())) {
			 throw new MLException("El satelite" + satelite.getName() + " ya existe");
		 }
		 if(satellitesRepository.count() >= 3) {
			 throw new MLException("Se permiten solo 3 satelites");			 
		 }
		
		 SatelliteEntity entity = new SatelliteEntity();
		 entity.setName(satelite.getName());
		 entity.setDistance(satelite.getDistance());
		 entity.setMessage(satelite.getMessage());
		 satellitesRepository.save(entity);
	 }
	 
	 public void deleteSatelite () {
		 satellitesRepository.deleteAll();
	 }
	 
	 public List<Satellite> obtenerSatelites() throws MLException{
		 if(satellitesRepository.count() != 3) {
			 throw new MLException("No hay suficiente información");			 
		 }
		 
		 List<SatelliteEntity> sat = satellitesRepository.findAll();
		 List<Satellite> satellites = new ArrayList<>();
		 sat.stream().forEach(s ->{
			 Satellite satt = new Satellite();
			 satt.setDistance(s.getDistance());
			 satt.setName(s.getName());
			 satt.setMessage(s.getMessage());
			 satellites.add(satt);
		 });
		 
		 return satellites;
	 }
}
