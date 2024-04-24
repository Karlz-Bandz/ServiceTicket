package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.dto.MessageDto;
import com.ncr.serviceticket.model.MessagePattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagePatternRepository extends JpaRepository<MessagePattern, Long> {

    @Query("SELECT new com.ncr.serviceticket.dto.MessageDto(a.id, a.title, a.message) FROM MessagePattern a WHERE a.operator.email = :email ORDER BY a.id DESC")
    List<MessageDto> findByOwnerUsername(@Param("email") String email);

    @Query("SELECT a.id FROM MessagePattern a WHERE a.operator.email = :email")
    List<Long> findEachIdByEmail(@Param("email") String email);
}
