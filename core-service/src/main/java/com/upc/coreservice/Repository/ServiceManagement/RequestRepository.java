package com.upc.coreservice.Repository.ServiceManagement;

import com.upc.coreentities.ServiceManagement.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findRequestById(Long id);
    List<Request> findAll();
  //  List<Request> findAllByUserProfileIdAndStatus(Long id, String status);
  //  List<Request> findAllByBusinessProfileIdAndStatus(Long id, String status);
}
