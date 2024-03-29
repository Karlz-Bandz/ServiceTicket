package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.model.MessagePattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagePatternRepository extends JpaRepository<MessagePattern, Long> {

//    List<MessagePattern> findByOwnerUsername(String email);
}
