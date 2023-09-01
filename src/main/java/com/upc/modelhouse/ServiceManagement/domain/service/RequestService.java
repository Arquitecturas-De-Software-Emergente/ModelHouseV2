package com.upc.modelhouse.ServiceManagement.domain.service;

import com.upc.modelhouse.ServiceManagement.domain.model.entity.Request;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RequestService {
    List<Request> getAll();
    List<Request> findAllBusinessProfileIdAndStatus(Long id, String status);
    List<Request> findAllUserProfileIdAndStatus(Long id, String status);
    Request create(Long userId, Long businessId, Request request);
    ResponseEntity<?> delete(Long id);
    Request changeStatus(Long id, Request request);
}
