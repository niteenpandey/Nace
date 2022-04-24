package com.db.nace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.db.nace.model.Nace;
import com.db.nace.repository.NaceRepository;
import com.db.nace.service.NaceService;
import com.db.nace.service.NaceServiceImpl;

@SpringBootTest
public class NaceServiceTest {
	

@InjectMocks
NaceService naceService = new NaceServiceImpl();

@Mock
NaceRepository naceRepository;

@Autowired
NaceService naceServiceTest;

@Test
public void getNaceDetails() {
	
	
	Nace nace = new Nace();
	nace.setOrderid("398483");
	nace.setLevel("3");
	nace.setCode("1.1");
	nace.setParent(null);
	nace.setItemInclude("This group includes the growing of non-perennial crops");
	nace.setRefrenceToISIC("11");
	nace.setDiscription("Growing of non-perennial crops");
		
	when(naceRepository.findByOrderid(nace.getOrderid())).thenReturn(nace);
	naceService.findByOrderId("398483");
	assertEquals(nace, nace);
}

@Test
public void putNaceDetails() {
	String resourceName = "naceTest1.csv";

	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource(resourceName).getFile());
	String absolutePath = file.getAbsolutePath();

	System.out.println(absolutePath);

	BatchStatus batchStatus = naceServiceTest.putNaceDetails(absolutePath);
	
	assertEquals(batchStatus.toString(), "COMPLETED");
}


}
