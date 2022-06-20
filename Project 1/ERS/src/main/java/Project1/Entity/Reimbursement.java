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

    public int getReimbursementID() { return reimbursementID; }

    public double getReimbursementAmount() { return reimbursementAmount; }

    public String getReimbursementReason() {  return reimbursementReason; }

    public reimbursementStatus getStatus() { return status; }

    public java.time.LocalDateTime getLocalDateTime() { return LocalDateTime; }

    public Employee getEmployee() { return employee; }

    public void setReimbursementID(int reimbursementID) { this.reimbursementID = reimbursementID; }

    public void setReimbursementAmount(double reimbursementAmount) { this.reimbursementAmount = reimbursementAmount; }

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
                ", LocalDateTime=" + LocalDateTime +
                ", employee=" + employee +
                '}';
    }
}
