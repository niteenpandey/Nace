package com.db.nace.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.db.nace.model.Nace;
import com.db.nace.repository.NaceRepository;
import com.db.nace.service.NaceService;
import com.db.nace.utils.NaceUtils;

@RestController
@RequestMapping("/nace")
public class NaceController {
	
	 
	  @Autowired
	  private Environment env;
	  
	  @Autowired
	  NaceService naceService;
	  
		/*
		 * @Autowired NaceRepository naceRepositry;
		 */
	  
	@PostMapping("/putNaceDetails")
	public BatchStatus putNaceDetails(@RequestParam("file") MultipartFile csvFile) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, IOException, InterruptedException {
		
	  String filePath =  NaceUtils.saveFile(env.getProperty("nace.csvupload.filepath"), csvFile.getOriginalFilename(), csvFile);
	  BatchStatus batchStatus = naceService.putNaceDetails(filePath);
	  return batchStatus;
	}
	
	@GetMapping("/getNaceDetails/{order_id}")
	public Nace getNaceDetails(@PathVariable String order_id) {
		System.out.print("Order ID "+ order_id );
		Nace nace = new Nace();
		return  naceService.findByOrderId(order_id);
	}
}
