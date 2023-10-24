package com.example.demo1;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Queues {
    //Arrays for Queues
    public static int[] Que1 = {0, 0};

    public static int[] Que2 = {0, 0, 0};
    public static int[] Que3 = {0, 0, 0, 0, 0};
    //Arrays for Waiting Queues
    public static int[] Que1_Waiting = new int[2];
    public static int[] Que2_Waiting = new int[3];
    public static int[] Que3_Waiting = new int[5];

    //Arrays for Burgers
    public static int[] Que1_Burgers = new int[2];
    public static int[] Que2_Burgers = new int[3];
    public static int[] Que3_Burgers = new int[5];


    //Count For no. of Customers in the Queue
    public static int i_for_Que1 = 0;
    public static int i_for_Que2 = 0;
    public static int i_for_Que3 = 0;


    public static void AddCustomer(Scanner scanner) {
        System.out.println("Enter Customer's First Name: ");
        String FirstName = scanner.next();

        System.out.println("Enter Customer's Second Name: ");
        String LastName = scanner.next();


        while (true) {



            System.out.println("Enter Cashier Number (1,2 or 3): ");
            int CashierNumber = 0;
            try {
                CashierNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You Entered a Wrong Number");
                scanner.nextLine();

            }
            int Burgers = 0;
            System.out.println("How many Burgers you need: ");
            try {
                Burgers = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a correct number");
                scanner.nextLine();
            }



            // Selecting Relevant Cashier for the Customer to be Added_______________________________________.
            if (CashierNumber == 1 && i_for_Que1 < 2) {
                Que1[i_for_Que1] = 1;
                Que1_Burgers [i_for_Que1] = Burgers;
                i_for_Que1++;


                System.out.println(FirstName + " Added to The Queue Number 1 Successfully! ");
                HelloApplication.StockOfBurger -= Burgers;
                //Saving Customer Name*********************************************************************************
                Customers.Customers_Que1[i_for_Que1 - 1][0] = FirstName;
                Customers.Customers_Que1[i_for_Que1 - 1][1] = LastName;
                break;

            } else if (CashierNumber == 1 && i_for_Que1 >= 2) { //Adding to a Waiting Queue if that Queue is already full
                if (Que1_Waiting[1]==1){
                    System.out.println("Please select a another Queue ");
                    continue;
                }
                System.out.println("Queue Number One is Full New Customer Will be Added to the Waiting Queue");
                Que1_Waiting[i_for_Que1 - 2] = 1;
               Customers.Customers_Que1_Waiting[i_for_Que1 - 2][0] = FirstName;
               Customers.Customers_Que1_Waiting[i_for_Que1 - 2][1] = LastName;
                i_for_Que1++;


                break;
            } else if (CashierNumber == 2 && i_for_Que2 < 3) {

                Que2[i_for_Que2] = 1;
                Que2_Burgers [i_for_Que2] = Burgers;
                i_for_Que2++;
                System.out.println(FirstName + " Added to The Queue Successfully! ");
                HelloApplication.StockOfBurger -= Burgers;
                //Saving Customer Name*********************************************************************************
                Customers.Customers_Que2[i_for_Que2 - 1][0] = FirstName;
                Customers.Customers_Que2[i_for_Que2 - 1][1] = LastName;

                break;

            }else if (CashierNumber == 2 && i_for_Que2 >= 3) {//Adding to a Waiting Queue if that Queue is already full
                System.out.println("Queue Number Two is Full New Customer Will be Added to the Waiting Queue");
                if (Que2_Waiting[2]==1){
                    System.out.println("Please select a another Queue ");
                    continue;
                }
                Que2_Waiting[i_for_Que2 - 3] = 1;
                Customers.Customers_Que2_Waiting[i_for_Que2 - 3][0] = FirstName;
                Customers.Customers_Que2_Waiting[i_for_Que2 - 3][1] = LastName;
                i_for_Que2++;
                break;
            }

            else if (CashierNumber == 3 && i_for_Que3 < 5) {
                if ( i_for_Que3 < 5) {
                    Que3[i_for_Que3] = 1;
                    Que3_Burgers [i_for_Que3] = Burgers;
                    i_for_Que3++;
                    System.out.println(FirstName + " Added to The Queue Successfully! ");
                    HelloApplication.StockOfBurger -= Burgers;
                    //Saving Customer Name*********************************************************************************
                    Customers.Customers_Que3[i_for_Que3 - 1][0] = FirstName;
                    Customers.Customers_Que3[i_for_Que3 - 1][1] = LastName;

                    break;

                }  else if (CashierNumber == 3 && i_for_Que3 >= 5) {//Adding to a Waiting Queue if that Queue is already full
                    System.out.println("Queue Number Three is Full New Customer Will be Added to the Waiting Queue");
                    if (Que3_Waiting[4]==1){
                        System.out.println("Please select a another Queue ");
                        continue;
                    }
                    Que3_Waiting[i_for_Que3-5] = 1;
                   Customers.Customers_Que3_Waiting[i_for_Que3 - 5][0] = FirstName;
                   Customers.Customers_Que3_Waiting[i_for_Que3 - 5][1] = LastName;


                    break;
                }
            }
        }

    }


}