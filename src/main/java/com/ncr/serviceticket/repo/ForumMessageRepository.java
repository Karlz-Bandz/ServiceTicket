package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.model.ForumMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumMessageRepository extends JpaRepository<ForumMessage, Long> {
}
