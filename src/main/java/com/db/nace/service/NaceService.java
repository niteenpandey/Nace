package com.db.nace.service;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;

import com.db.nace.model.Nace;

public interface NaceService {

	Nace findByOrderId(String orderId);
	
	BatchStatus putNaceDetails(String filePath);
}
