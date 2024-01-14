package com.Solutions.Goomie.repository;

import com.Solutions.Goomie.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}
