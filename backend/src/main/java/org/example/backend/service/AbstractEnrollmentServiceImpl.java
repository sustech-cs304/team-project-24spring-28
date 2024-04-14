package org.example.backend.service;

import org.example.backend.api.AbstractEnrollmentRepository;
import org.example.backend.domain.AbstractEnrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbstractEnrollmentServiceImpl implements AbstractEnrollmentService {
    @Autowired
    AbstractEnrollmentRepository abstractEnrollmentRepository;

    @Override
    public boolean saveAbstractEnrollment(AbstractEnrollment abstractEnrollment) {
        abstractEnrollmentRepository.save(abstractEnrollment);
        return true;
    }

    @Override
    public boolean deleteAbstractEnrollmentById(long id) {
        abstractEnrollmentRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateAbstractEnrollment(AbstractEnrollment abstractEnrollment) {
        abstractEnrollmentRepository.save(abstractEnrollment);
        return true;
    }

    @Override
    public AbstractEnrollment findAbstractEnrollmentById(long id) {
        return abstractEnrollmentRepository.findById(id).orElse(null);
    }
}
