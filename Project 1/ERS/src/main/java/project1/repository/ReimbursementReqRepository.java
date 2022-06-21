package project1.repository;

import project1.entity.ExpReimbursementReq;
import project1.entity.Employee;

import java.util.List;

public interface ReimbursementReqRepository {
    void save(ExpReimbursementReq expReimbursementReq);

    List<ExpReimbursementReq> findAll();

    List<ExpReimbursementReq> findAllByStatus(String status);

    List<ExpReimbursementReq> findAllByStatusForEmployee(String status, int empID);

    public List<ExpReimbursementReq> findAllResolved();

    List<ExpReimbursementReq> findAllResolvedForEmployee(int empID);

    void updateStatus(ExpReimbursementReq expReimbursementReq, String status);

}
