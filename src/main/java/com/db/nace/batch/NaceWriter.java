package com.db.nace.batch;


import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.db.nace.repository.NaceRepository;

import com.db.nace.model.Nace;

import java.util.List;

@Component
public class NaceWriter implements ItemWriter<Nace> {

    private NaceRepository NaceRepository;
    

    @Autowired
    public NaceWriter (NaceRepository NaceRepository) {
        this.NaceRepository = NaceRepository;
    }

    @Override
    public void write(List<? extends Nace> Naces) throws Exception{
        System.out.println("Data Saved for Naces: " + Naces);
        NaceRepository.saveAll(Naces);
    }
}
