package employee_payroll;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee extends Management implements Serializable, Cloneable  {

     String name , designation;
     int id, present, days;

     double netpay = 0.0, grosspay = 0.0, taxes = 0.0;


    Employee() {
        this.name = getName();
        this.id = Integer.parseInt(getId());
        this.designation = getDesignation();
    }

    private String getDesignation() {
        System.out.println("\n-=-=-=- DESIGNATION -=-=-=");
        System.out.println("""
                    1 -> DEVELOPER
                    2 -> DESIGNER
                    3 -> QUALITY ANALYSIS
                    4 -> TESTER
                -=-=-=-=-=-=-=-=-=-=-=-=-=""");

        System.out.print("\nSELECT OPTION :");

        Scanner in = new Scanner(System.in);

        while (true) {
            int des = in.nextInt();
            if (des < 1 || des > 4) System.out.print("\nENTER VALID NUMBER : ");
            else if (des == 1) return "DEVELOPER";
            else if (des == 2) return "DESIGNER";
            else if (des == 3) return "MARKETING ANALYSIS";
            else return "HUMAN RESOURCE";
        }
    }

    private String getName() {

        Scanner in = new Scanner(System.in);
        System.out.print("\nENTER NAME : ");
        while (true) {
            String name = in.nextLine();
            Pattern pattern = Pattern.compile("^[A-Za-z][A-Za-z0-9_\\s]{3,255}$");
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) return name;
            else System.out.print("ENTER VALID NAME : ");
        }
    }

    private String getId() {
        Scanner in = new Scanner(System.in);
        outer : while (true) {
            System.out.print("\nENTER NEW ID (xxxx) : ");
            String idnumber = in.next();
            Pattern pattern = Pattern.compile("^\\d{4}$");
            Matcher matcher = pattern.matcher(idnumber);
            if (matcher.find()) {
                for (Employee employee : employeeData) {
                    if (employee.id == Integer.parseInt(idnumber)) {
                        System.out.println("ID TAKEN");
                        continue outer;}
                }
                return idnumber;
            }
            System.out.print("ENTER VALID ID ");
        }
    }

    @Override
    public Employee clone() {
        try {
            Employee clone = (Employee) super.clone();
            clone.name = this.name;
            clone.id = this.id;
            clone.days = this.days;
            clone.designation = this.designation;
            clone.netpay = this.netpay;
            clone.grosspay = this.grosspay;
            clone.present = this.present;
            clone.taxes = this.taxes;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


