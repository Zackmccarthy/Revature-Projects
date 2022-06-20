package Project1.Repository;

import Project1.Entity.Reimbursement;

import java.util.List;

public interface ReimbursementRepository {

    void save(Reimbursement reimbursement);

    List<Reimbursement> findAll();




}
