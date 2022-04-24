package com.db.nace.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.nace.model.Nace;

@Repository
public interface NaceRepository extends JpaRepository<Nace,Integer> {

	Nace findByOrderid(String orderid);

}
