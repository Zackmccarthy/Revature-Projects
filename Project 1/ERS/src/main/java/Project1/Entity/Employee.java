package Project1.Entity;

import java.util.Objects;

public class Employee  implements Comparable<Employee> {
    private String employeeID; // These entities will mark the columns in the database
    private String employeeFirstName, employeeLastName, employeeDob, employeeAddress, employeePhoneNum;
    private String employeeEmail, employeePass;

    private  employeeRole role;

        public Employee(String employeeID, String employeeFirstName, String employeeLastName,String employeeDob, String employeeAddress, String employeePhoneNum, String employeeEmail, String employeePass, employeeRole role) {
                this.employeeID = employeeID; // Constructor
                this.employeeFirstName = employeeFirstName;
                this.employeeLastName = employeeLastName;
                this.employeeDob = employeeDob;
                this.employeeAddress = employeeAddress;
                this.employeePhoneNum = employeePhoneNum;
                this.employeeEmail = employeeEmail;
                this.employeePass = RandomPassword.randomPassword();
        }

        public Employee() {
        }

        public String getEmployeeID() {
                return employeeID;
        }

        public String getEmployeeFirstName() { return employeeFirstName; }

        public String getEmployeeLastName() { return employeeLastName; }

        public String getEmployeeDob() {
        return employeeDob;
    }

        public String getEmployeeAddress() { return employeeAddress; }

        public String getEmployeePhoneNum() { return employeePhoneNum; }

        public String getEmployeeEmail() { return employeeEmail; }

        public String getEmployeePass() { return employeePass; }

        public employeeRole getEmployeeRole() { return role; }


        public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

        public void setEmployeeFirstName(String employeeFirstName) {
                this.employeeFirstName = employeeFirstName;
        }

        public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

        public void setEmployeeDob(String employeeDob) {
                this.employeeDob = employeeDob;
        }

        public void setEmployeeAddress(String employeeAddress) { this.employeeAddress = employeeAddress; }

        public void setEmployeePhoneNum(String employeePhoneNum) { this.employeePhoneNum = employeePhoneNum; }

        public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }

        public void setEmployeePass(String employeePass) { this.employeePass = employeePass; }

        public void setEmployeeRole(employeeRole role) { this.role = role; }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "employeeID='" + employeeID + '\'' +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                ", employeePhoneNum='" + employeePhoneNum + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeID, employee.employeeID) && Objects.equals(employeeFirstName, employee.employeeFirstName) && Objects.equals(employeeLastName, employee.employeeLastName) && Objects.equals(employeeDob, employee.employeeDob) && Objects.equals(employeeAddress, employee.employeeAddress) && Objects.equals(employeePhoneNum, employee.employeePhoneNum) && Objects.equals(employeeEmail, employee.employeeEmail) && Objects.equals(employeePass, employee.employeePass) && role == employee.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, employeeFirstName, employeeLastName, employeeDob, employeeAddress, employeePhoneNum, employeeEmail, employeePass, role);
    }


    @Override
    public int compareTo(Employee o) {
        return this.employeeID.compareTo(o.getEmployeeID());
    }
}
