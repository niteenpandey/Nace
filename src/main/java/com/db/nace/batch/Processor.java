package com.db.nace.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.db.nace.model.Nace;

@Component
public class Processor  implements ItemProcessor<Nace,Nace> { 



@Override
public Nace process(Nace item) throws Exception {
	// TODO Auto-generated method stub
	return item;
}
}