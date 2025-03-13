import java.util.Scanner;
public class Calculator{
    public static void main(String args[]){
            System.out.println("-------------WELCOME-----------------");
            System.out.println("--------------------------------");
            System.out.println("This is a Simple Console-based Calculator");
            System.out.println("Select your choice from below (1-6):");
            System.out.println("1.Addition (+)");
            System.out.println("2.Subtraction (-)");
            System.out.println("3.Multiplication (*)");
            System.out.println("4.Division (/)");
            System.out.println("5.Remainder (%)");
            System.out.println("6.Exit");

            Scanner sc=new Scanner(System.in);
            System.out.println("Enter your choice:");
            int choice=sc.nextInt();

            if(choice==6){
                System.out.println("Exiting Calculator.Have a good day!");
                
            }
        
            System.out.print("Enter first number:");
            double num1=sc.nextDouble();
            System.out.print("Enter second number:");
            double num2=sc.nextDouble();

            double result=0;

            switch(choice){
                case 1:
                    result=num1+num2;
                    System.out.println("Result of addition is:" + result);
                    break;
                case 2:
                    result=num1-num2;
                    System.out.println("Result of subtraction is:" + result);
                    break;
                case 3:
                    result=num1*num2;
                    System.out.println("Result of multiplication is:" + result);
                    break;
                case 4:
                    if(num2==0){
                        System.out.println("Cannot divide by zero");
                    }
                    else{
                        result=num1/num2;
                        System.out.println("Result of division is:" + result);
                        break;
                    }
                case 5:
                    if(num2==0){
                    System.out.println("Error");
                 }
                   else{
                    result=num1%num2;
                    System.out.println("Result of remainder is:" + result);
                    break;
                   } 
                default:
                System.out.println("Invalid choice.Please enter a valid choice...");
            }
            sc.close();
        }
    }
