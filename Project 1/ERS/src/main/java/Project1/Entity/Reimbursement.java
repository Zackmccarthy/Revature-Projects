package Project1.Entity;
import java.time.*;
import java.util.Objects;


public class Reimbursement {
    private int reimbursementID;
    private double reimbursementAmount;
    private String reimbursementReason;
    private reimbursementStatus status;
    private LocalDateTime LocalDateTime;
    private Employee employee;

    public Reimbursement(double reimbursementAmount, String reimbursementReason, Employee employee) {
        this.reimbursementAmount = reimbursementAmount;
        this.reimbursementReason = reimbursementReason;
        this.reimbursementStatus = status;
        this.employee = employee;
    }

    public int getReimbursementID() { return reimbursementID; }

    public double getReimbursementAmount() { return reimbursementAmount; }

    public String getReimbursementReason() {  return reimbursementReason; }

    public reimbursementStatus getStatus() {
        if (this.status == null) {
            this.status = reimbursementStatus.valueOf("PENDING");
        }
        return status;
    }

    public java.time.LocalDateTime getLocalDateTime() { return LocalDateTime; }

    public Employee getEmployee() { return employee; }

    public void setReimbursementID(int reimbursementID) { this.reimbursementID = reimbursementID; }

    public void setReimbursementAmount(double reimbursementAmount) {
        if (reimbursementAmount > 0) {
            this.reimbursementAmount = reimbursementAmount;
        } else {
            this.reimbursementAmount = 0;
        }
    }

    public void setReimbursementReason(String reimbursementReason) { this.reimbursementReason = reimbursementReason; }

    public void setStatus(reimbursementStatus status) { this.status = status; }

    public void setLocalDateTime(java.time.LocalDateTime localDateTime) { LocalDateTime = localDateTime; }

    public void setEmployee(Employee employee) { this.employee = employee; }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementID=" + reimbursementID +
                ", reimbursementAmount=" + reimbursementAmount +
                ", reimbursementReason='" + reimbursementReason + '\'' +
                ", status=" + status +
                ", employee=" + employee.getEmployeeID() +
                '}';
    }
}
