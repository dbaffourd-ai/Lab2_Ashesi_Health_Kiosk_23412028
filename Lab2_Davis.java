import java.util.Scanner;
import java.util.Random;

public class AshesiHealthKiosk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
          Random r = new Random();

        System.out.println("Welcome to Ashesi Health Kiosk");

        
        System.out.print("Enter service code (P/L/T/C): ");
        char serviceCode = sc.next().charAt(0);
        serviceCode = Character.toUpperCase(serviceCode);

             String service = "";
         if (serviceCode == 'P') {
            service = "PHARMACY";
            System.out.println("Go to: Pharmacy Desk");
        } else if (serviceCode == 'L') {
            service = "LAB";
            System.out.println("Go to: Lab Desk");
          } else if (serviceCode == 'T') {
            service = "TRIAGE";
            System.out.println("Go to: Triage Desk");
        } else if (serviceCode == 'C') {
            service = "COUNSELING";
            System.out.println("Go to: Counseling Desk");
        } else {
            System.out.println("Invalid service code");
            return;
        }

        
        double metricVal = 0;
         int roundedMetric = 0;
           String metricText = "";

        if (service.equals("TRIAGE")) {
            System.out.println("Pick metric: 1=BMI  2=Dosage  3=Trig");
            int option = sc.nextInt();

            if (option == 1) {
                System.out.print("Enter weight (kg): ");
                double w = sc.nextDouble();
                  System.out.print("Enter height (m): ");
                double h = sc.nextDouble();

                double bmi = w / (h * h);
                metricVal = Math.round(bmi * 10) / 10.0;
                  roundedMetric = (int)Math.round(bmi);
                metricText = "BMI=" + metricVal;

                if (bmi < 18.5) {
                    System.out.println("BMI: " + metricVal + " Category: Underweight");
         } else if (bmi >= 18.5 && bmi < 25) {
                    System.out.println("BMI: " + metricVal + " Category: Normal");
                } else if (bmi >= 25 && bmi < 30) {
                    System.out.println("BMI: " + metricVal + " Category: Overweight");
                } else {
                    System.out.println("BMI: " + metricVal + " Category: Obese");
    }

            } else if (option == 2) {
                System.out.print("Enter dosage (mg): ");
                double dose = sc.nextDouble();
                double tabs = Math.ceil(dose / 250.0);
                   int tablets = (int) tabs;  
                metricVal = tablets;
                roundedMetric = tablets;
                metricText = "TABLETS=" + tablets;
                System.out.println("Number of tablets: " + tablets);

            } else if (option == 3) {
                System.out.print("Enter angle: ");
                  double deg = sc.nextDouble();
                double rad = Math.toRadians(deg);
                double s = Math.sin(rad);
             double c = Math.cos(rad);
                double sRound = Math.round(s * 1000) / 1000.0;
                  double cRound = Math.round(c * 1000) / 1000.0;
                System.out.println("sin=" + sRound + " cos=" + cRound);

                metricVal = sRound;
                roundedMetric = (int)Math.round(sRound * 100);
                metricText = "SIN=" + sRound;
        }
        }

        
     char first = (char)('A' + r.nextInt(26));
        String id = "" + first;
        for (int i = 0; i < 4; i++) {
            int d = 3 + r.nextInt(7);
            id = id + d;
        }
        System.out.println("Generated ID: " + id);

        if (id.length() != 5) {
     System.out.println("wrong length");
            return;
        }
        if (!Character.isLetter(id.charAt(0))) {
            System.out.println("Invalid: first character must be letter");
            return;
        }
        if (!(Character.isDigit(id.charAt(1)) && Character.isDigit(id.charAt(2))
                && Character.isDigit(id.charAt(3)) && Character.isDigit(id.charAt(4)))) {
          System.out.println("Invalid: last 4 must be numbers");
            return;
        }
        System.out.println("ID OK");

        
        System.out.print("Enter your first name: ");
        String name = sc.next();
        char b = Character.toUpperCase(name.charAt(0));
        char shifted = (char)('A' + (b - 'A' + 2) % 26);

        String lastTwo = id.substring(3); // last two chars
        String display = shifted + lastTwo + "-" + roundedMetric;
        System.out.println("Display Code: " + display);

        
        String summary = service + " | ID=" + id;
           if (service.equals("TRIAGE")) {
            summary = summary + " | " + metricText;
        }
                    summary = summary + " | Code=" + display;

        System.out.println("Summary: " + summary);

        sc.close();
    }
}
