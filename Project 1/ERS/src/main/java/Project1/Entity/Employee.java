package Project1.Entity;

import java.util.Objects;
import java.util.Scanner;

public class Employee  implements Comparable<Employee> {
    private String employeeID; // These entities will mark the columns in the database
    private String employeeFirstName, employeeLastName, employeeDob, employeeAddress, employeePhoneNum;
    private String employeeEmail, employeePass;
    private  employeeRole role;

        public Employee(String employeeFirstName, String employeeLastName, String employeeEmail, String role) {
                this.employeeFirstName = employeeFirstName; //Constructor
                this.employeeLastName = employeeLastName;
                this.employeeEmail = employeeEmail;
                this.role = employeeRole.valueOf(role);
                this.employeePass = RandomPassword.randomPassword();
        }

        public Employee() {
        }

        public void viewEmployeeInfo(Employee emp) {
            System.out.println("Employee ID#: " + emp.getEmployeeID());
            System.out.println("Employee First Name: " + emp.getEmployeeFirstName());
            System.out.println("Employee Last Name: " + emp.getEmployeeLastName());
            System.out.println("Employee Date of Birth: " + emp.getEmployeeDob());
            System.out.println("Employee Role: " + emp.getEmployeeRole().toString());
            System.out.println("Employee Email: " + emp.getEmployeeEmail());
            System.out.println("Employee Phone#: " + emp.getEmployeePhoneNum());
            System.out.println("Employee Address: " + emp.getEmployeeAddress());
        }

    public void updateEmployeeInfo(String str, Employee emp){
        Scanner s = new Scanner(System.in);
        String input;
        String[] perInfo= new String[] {"First Name","Last Name", "Date of Birth", "Email", "Phone#", "Address"};
        String[] perInfoVal= new String[] {emp.getEmployeeFirstName(), emp.getEmployeeLastName(), emp.getEmployeeDob(), emp.getEmployeeEmail(), emp.getEmployeePhoneNum(), emp.getEmployeeAddress()};

        if(str.equals("password")){
            System.out.print("Would you like to update your password? ");
            if(Cont().equals("y")){
                System.out.print("Please enter your current password: ");
                input=s.nextLine();
                if(BCrypt.verifyer().verify(input.toCharArray(),emp.getEmployeePass()).verified){
                    System.out.print("Please enter your new password:");
                    input=s.nextLine();
                    System.out.print("Please re-enter your new password:");
                    String input2=s.nextLine();
                    if(input.equals(input2)){
                        String bcryptHashString = BCrypt.withDefaults().hashToString(12, input.toCharArray());
                        emp.setEmployeePass(bcryptHashString);
                    }else
                        System.out.println("Password was not reset.");
                }
            }
        } else {
            for(int i=0; i<perInfo.length;i++){
                System.out.println("Employee "+perInfo[i]+" : "+perInfoVal[i]);
                System.out.print("Would you like to update your " + perInfo[i] + "? ");
                if (Cont().equals("y")) {
                    System.out.print("Please enter your " + perInfo[i] + ": ");
                    input = s.nextLine();

                    switch (perInfo[i]) {
                        case "FirstName":
                            emp.setEmployeeFirstName(input);
                            break;
                        case "LastName":
                            emp.setEmployeeLastName(input);
                            break;
                        case "DateofBirth":
                            emp.setEmployeeDob(input);
                            break;
                        case "Email":
                            emp.setEmployeeEmail(input);
                            break;
                        case "Phone#":
                            emp.setEmployeePhoneNum(input);
                            break;
                        case "Address":
                            emp.setEmployeeAddress(input);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
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
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeDob='" + employeeDob + '\'' +
                ", employeeAddress='" + employeeAddress + '\'' +
                ", employeePhoneNum='" + employeePhoneNum + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", role=" + role +
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
