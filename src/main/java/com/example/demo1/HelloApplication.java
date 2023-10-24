package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.example.demo1.Queues.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 618, 393);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        HelloController controller = fxmlLoader.getController();
        controller.initialize();
    }






    public static int StockOfBurger = 50;
    public static  int IncomeFromQueue_1 =0;
    public static  int IncomeFromQueue_2 =0;
    public static  int IncomeFromQueue_3 =0;

    public static String[] CombinedArray = new String[ Customers.Customers_Que1.length + Customers.Customers_Que2.length + Customers.Customers_Que3.length];
    public static String[] combined_Waiting_Array = new String[Customers.Customers_Que1_Waiting.length+Customers.Customers_Que2_Waiting.length+Customers.Customers_Que3_Waiting.length];


    public static void main(String[] args) throws FileNotFoundException {


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Menu Options:");
            System.out.println();
            System.out.println("100 or VFQ: View all Queues");
            System.out.println("101 or VEQ: View all Empty Queues");
            System.out.println("102 or ACQ: Add customer to a Queue");
            System.out.println("103 or RCQ: Remove a customer from a Queue");
            System.out.println("104 or PCQ: Remove a served customer");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
            System.out.println("106 or SPD: Store Program Data into file");
            System.out.println("107 or LPD: Load Program Data from file");
            System.out.println("108 or STK: View Remaining burgers Stock");
            System.out.println("109 or AFS: Add burgers to Stock");
            System.out.println("110 or IFQ: To view Income of Each Queue");
            System.out.println("112 or GUI: Open Graphical User Interface ");

            System.out.println("999 or EXT: Exit the Program");
            System.out.println();
            System.out.print("Choose a Option: ");
            String choice = scanner.next();
            switch (choice) {
                case "100", "VFQ":
                    DisplayQues();
                    WarningMassege();
                    break;
                case "101", "VEQ":
                    DisplayAllEmptyQues();
                    break;
                case "102", "ACQ":
                    Queues.AddCustomer(scanner);
                    WarningMassege();
                    break;
                case "103", "RCQ":
                    RemoveCustomer(scanner);
                    break;
                case "104", "PCQ":
                    RemoveServedCustomer(scanner);
                    WarningMassege();
                    break;
                case "105", "VCS":
                    ViewCustomerSortedInAlphabeticalOrder();
                case "106", "SPD":
                    WritingToFile();
                    break;
                case "107", "LPD":
                    LoadDataFromFile();
                    break;
                case "108", "STK":
                    RemainingStock();
                    break;
                case "109", "AFS":
                    AddBurgertoStock(scanner);
                    break;
                case "110" , "IFQ":
                    Income();
                    break;
                case "112" , "GUI":
                    Waiting_Queue_Combine();
                    launch();
                    continue;
                case "999", "EXT":
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }




    }

    private static void DisplayQues() {
        System.out.println();
        System.out.println("*****************");
        System.out.println("*   Cashiers    *");
        System.out.println("*****************");
        System.out.print("1 ");
        System.out.print("2 ");
        System.out.println("3");
        System.out.print("- ");
        System.out.print("- ");
        System.out.println("- ");

        for (int i = 0; i < 5; i++) {//Printing '0' or 'X' according to presence of a customer
            if (i < 2)
                System.out.print(Queues.Que1[i] != 0 ? "O " : "X ");
            else
                System.out.print("  ");

            if (i < 3)
                System.out.print(Queues.Que2[i] != 0 ? "O " : "X ");
            else
                System.out.print("  ");

            System.out.println(Queues.Que3[i] != 0 ? "O" : "X");

        }
        System.out.println("'X' - Not Occupied , 'O' - Occupied  ");
        System.out.println();


        System.out.println();
        System.out.print("Cashier Number One : ");
        for (int element : Queues.Que1) {
            System.out.print(element + " ");
        }
        System.out.println();

        System.out.print("Cashier Number Two : ");
        for (int element : Queues.Que2) {
            System.out.print(element + " ");
        }
        System.out.println();

        System.out.print("Cashier Number Three : ");
        for (int element : Queues.Que3) {
            System.out.print(element + " ");
        }
        System.out.println();
        System.out.println();
    }

    private static void DisplayAllEmptyQues() {//Finding empty spots and displaying
        System.out.println();
        for (int i = 0; i < Queues.Que1.length; i++) {
            if (Queues.Que1[i] == 0) {
                System.out.println("Queue 1 Has an Empty Spot");
                break;
            }
        }
        for (int i = 0; i < Queues.Que2.length; i++) {
            if (Queues.Que2[i] == 0) {
                System.out.println("Queue 2 Has an Empty Spot");
                break;
            }
        }
        for (int i = 0; i < Queues.Que3.length; i++) {
            if (Queues.Que3[i] == 0) {
                System.out.println("Queue 3 Has an Empty Spot");
                break;
            }
        }
        System.out.println();
    }


    private static void RemoveCustomer(Scanner scanner) {
//removing customer

        int CountQue1 = 0;
        int CountQue2 = 0;
        int CountQue3 = 0;
        int RemoveFrom = 0;

        System.out.println("From which Queue You Need to Remove a Customer");
        try {
            RemoveFrom = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You Entered a Wrong Number");
            scanner.nextLine();


        }

        if (RemoveFrom == 1) {
            while (true) {
                System.out.println("From Which Position you Need to remove a Customer:");
//asking from where to remove a customer
                int PositionQue1 = 0;
                try {
                    PositionQue1 = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Enter a Number Between 1-3 ");
                    scanner.nextLine();
                    continue;

                }
                //Checking How Many Customers are in the Queue____________________________________
                for (int i = 0; i < 2; i++) {
                    if (Queues.Que1[i] == 1) {
                        CountQue1 += 1;
                    }
                }
                if (PositionQue1 <= CountQue1) {

                    //Removing Customers From the Relevant spot___________________________________________
                    if (CountQue1 == 0) {
                        System.out.println("This Queue is already Empty");
                        break;
                    } else if (PositionQue1 == 1) {
                        Queues.Que1[0] = 0;
                        System.out.println(Customers.Customers_Que1[CountQue1 - 1][0] + " Removed From Queue Successfully");
                        StockOfBurger += Queues.Que1_Burgers[0];//Adding burgers back to stock

                        // Removing Customer Name_______________________________________________________________________________
                        Customers.Customers_Que1[PositionQue1 - 1][0] = "";
                        Customers.Customers_Que1[PositionQue1 - 1][1] = "";
                        Queues.i_for_Que1--;

                        for (int i = PositionQue1 - 1; i < Customers.Customers_Que1.length - 1; i++) {
                            Customers.Customers_Que1[i] = Customers.Customers_Que1[i + 1];  // Shift elements to the left
                        }
                        Customers.Customers_Que1[Customers.Customers_Que1.length - 1][0] = "";
                        Customers.Customers_Que1[Customers.Customers_Que1.length - 1][1] = ""; //Since there is First Name and Last Name
                        break;

                    } else if (PositionQue1 == 2) {
                        Queues.Que1[1] = 0;
                        System.out.println("Customer Removed From Queue Successfully");
                        StockOfBurger += Queues.Que1_Burgers[1];//Adding burgers back to stock

                        // Removing Customer Name_______________________________________________________________________________
                        Customers.Customers_Que1[PositionQue1 - 1][0] = "";
                        Customers.Customers_Que1[PositionQue1 - 1][1] = "";
                        Queues.i_for_Que1--;
                        for (int i = PositionQue1; i < Customers.Customers_Que1.length - 1; i++) {
                            Customers.Customers_Que1[i] = Customers.Customers_Que1[i + 1];  // Shift elements to the left
                        }
                        Customers.Customers_Que1[Customers.Customers_Que1.length - 1][0] = "";
                        Customers.Customers_Que1[Customers.Customers_Que1.length - 1][1] = ""; //Since there is First Name and Last Name
                        break;
                    }
                } else {
                    System.out.println("Queue is Not that Long Please enter a Valid Position");
                    continue;
                }
            }
        } else if (RemoveFrom == 2) {
            System.out.println("From Which Position you Need to remove a Customer:");
            int PositionQue2 = scanner.nextInt();
            //Checking How Many Customers are in the Queue____________________________________
            for (int i = 0; i < 3; i++) {
                if (Queues.Que2[i] == 1) {
                    CountQue2 += 1;
                }
            }
            if (PositionQue2 <= CountQue2) {
                //Removing Customers From the Relevant spot___________________________________________
                if (PositionQue2 == 0) {
                    System.out.println("This Queue is already Empty");
                } else if (PositionQue2 == 1) {
                    Queues.Que2[0] = 0;
                    if (Queues.i_for_Que2 == 2) {
                        Queues.Que2[0] = 1;
                        Queues.Que2[1] = 0;
                    } else if (Queues.i_for_Que2 == 3) {
                        Queues.Que2[0] = 1;
                        Queues.Que2[1] = 1;
                        Queues.Que2[2] = 0;
                    }
                    System.out.println("Customer Removed From Queue Successfully");
                    // Removing Customer Name_______________________________________________________________________________
                    Customers.Customers_Que2[PositionQue2 - 1][0] = "";
                    Customers.Customers_Que2[PositionQue2 - 1][1] = "";
                    Queues.i_for_Que2--;
                    for (int i = PositionQue2 - 1; i < Customers.Customers_Que2.length - 1; i++) {
                        Customers.Customers_Que2[i] = Customers.Customers_Que2[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][0] = "";
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][1] = ""; //Since there is First Name and Last Name
                    Queues.i_for_Que2--;
                    StockOfBurger += Queues.Que2_Burgers[0];//Adding burgers back to stock
                } else if (PositionQue2 == 2) {
                    Queues.Que2[1] = 0;
                    if (Queues.i_for_Que2 > 2) {
                        Queues.Que2[1] = 1;
                        Queues.Que2[2] = 0;
                    }
                    System.out.println("Customer Removed From Queue Successfully");
                    // Removing Customer Name_______________________________________________________________________________
                    Customers.Customers_Que2[PositionQue2 - 1][0] = "";
                    Customers.Customers_Que2[PositionQue2 - 1][1] = "";
                    Queues.i_for_Que2--;
                    for (int i = PositionQue2 - 1; i < Customers.Customers_Que2.length - 1; i++) {
                        Customers.Customers_Que2[i] = Customers.Customers_Que2[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][0] = "";
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][1] = ""; //Since there is First Name and Last Name
                    Queues.i_for_Que2--;
                    StockOfBurger += Queues.Que2_Burgers[1];//Adding burgers back to stock
                } else {
                    Queues.Que2[2] = 0;
                    System.out.println("Customer Removed From Queue Successfully");
                    // Removing Customer Name_______________________________________________________________________________
                    Customers.Customers_Que2[PositionQue2 - 1][0] = "";
                    Customers.Customers_Que2[PositionQue2 - 1][1] = "";
                    Queues.i_for_Que2--;
                    for (int i = PositionQue2 - 1; i < Customers.Customers_Que2.length - 1; i++) {
                        Customers.Customers_Que2[i] = Customers.Customers_Que2[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][0] = "";
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][1] = ""; //Since there is First Name and Last Name
                    Queues.i_for_Que2--;
                    StockOfBurger += Queues.Que2_Burgers[2];//Adding burgers back to stock
                }
            } else {
                System.out.println("Queue is Not that Long Please enter a Valid Position");
            }
        } else if (RemoveFrom == 3) {
            while (true) {
                System.out.println("From Which Position you Need to remove a Customer:");
                int PositionQue3 = scanner.nextInt();
                //Checking How Many Customers are in the Queue____________________________________
                for (int i = 0; i < 5; i++) {
                    if (Queues.Que3[i] == 1) {
                        CountQue3 += 1;
                    }
                }
                if (PositionQue3 <= CountQue3) {
                    System.out.println(CountQue3);
                    if (CountQue3 == 0) {
                        System.out.println("This Queue is already Empty");
                        break;
                    }
                    //Removing Customers From the Relevant spot___________________________________________
                    if (PositionQue3 == 1) {
                        Queues.Que3[0] = 0;
                        if (Queues.i_for_Que3 == 2) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 0;
                        } else if (Queues.i_for_Que3 == 3) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 0;
                        }
                        System.out.println("Customer Removed From Queue Successfully");
                        // Removing Customer Name_______________________________________________________________________________
                        Customers.Customers_Que3[PositionQue3 - 1][0] = "";
                        Customers.Customers_Que3[PositionQue3 - 1][1] = "";
                        Queues.i_for_Que3--;
                        for (int i = PositionQue3 - 1; i < Customers.Customers_Que3.length - 1; i++) {
                            Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                        }
                        Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                        Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name
                        Queues.i_for_Que3--;
                        StockOfBurger += Queues.Que3_Burgers[0];//Adding burgers back to stock
                        break;
                    } else if (PositionQue3 == 2) {
                        Queues.Que3[1] = 0;
                        if (Queues.i_for_Que3 == 3) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 0;
                        } else if ( Queues.i_for_Que3 == 4) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 1;
                            Queues.Que3[3] = 0;
                        } else if ( Queues.i_for_Que3 == 5) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 1;
                            Queues.Que3[3] = 1;
                            Queues.Que3[4] = 0;
                        }

                        System.out.println("Customer Removed From Queue Successfully");
                        // Removing Customer Name_______________________________________________________________________________
                        Customers.Customers_Que3[PositionQue3 - 1][0] = "";
                        Customers.Customers_Que3[PositionQue3 - 1][1] = "";
                        Queues.i_for_Que3--;
                        for (int i = PositionQue3 - 1; i < Customers.Customers_Que3.length - 1; i++) {
                            Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                        }
                        Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                        Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name
                        Queues.i_for_Que3--;
                        StockOfBurger += Queues.Que3_Burgers[1];//Adding burgers back to stock
                        break;
                    } else if (PositionQue3 == 3) {
                        Queues.Que3[2] = 0;
                        if (Queues.i_for_Que3 == 3) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 0;
                        } else if (Queues.i_for_Que3 == 4) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 1;
                            Queues.Que3[3] = 0;
                        } else if (Queues.i_for_Que3 == 5) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 1;
                            Queues.Que3[3] = 1;
                            Queues.Que3[4] = 0;
                        }
                        System.out.println("Customer Removed From Queue Successfully");
                        // Removing Customer Name_______________________________________________________________________________
                        Customers.Customers_Que3[PositionQue3 - 1][0] = "";
                        Customers.Customers_Que3[PositionQue3 - 1][1] = "";
                        Queues.i_for_Que3--;
                        for (int i = PositionQue3 - 1; i < Customers.Customers_Que3.length - 1; i++) {
                            Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                        }
                        Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                        Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name
                        Queues.i_for_Que3--;
                        StockOfBurger += Queues.Que3_Burgers[2];//Adding burgers back to stock
                        break;
                    } else if (PositionQue3 == 4) {
                        Queues.Que3[3] = 0;
                        if (Queues.i_for_Que3 == 3) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 0;
                        } else if (Queues.i_for_Que3 == 4) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues.Que3[2] = 1;
                            Queues.Que3[3] = 0;
                        } else if (Queues.i_for_Que3 == 5) {
                            Queues.Que3[0] = 1;
                            Queues.Que3[1] = 1;
                            Queues. Que3[2] = 1;
                            Queues.Que3[3] = 1;
                            Queues.Que3[4] = 0;
                        }
                        System.out.println("Customer Removed From Queue Successfully");
                        // Removing Customer Name_______________________________________________________________________________
                        Customers.Customers_Que3[PositionQue3 - 1][0] = "";
                        Customers.Customers_Que3[PositionQue3 - 1][1] = "";
                        Queues.i_for_Que3--;

                        for (int i = PositionQue3 - 1; i < Customers.Customers_Que3.length - 1; i++) {
                            Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                        }
                        Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                        Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name
                        Queues.i_for_Que3--;
                        StockOfBurger += Queues.Que3_Burgers[3];//Adding burgers back to stock
                        break;
                    }
                } else if (PositionQue3 == 5) {
                    Queues.Que3[4] = 0;
                    if (Queues.i_for_Que3 == 3) {
                        Queues.Que3[0] = 1;
                        Queues.Que3[1] = 1;
                        Queues.Que3[2] = 0;
                    } else if (Queues.i_for_Que3 == 4) {
                        Queues.Que3[0] = 1;
                        Queues.Que3[1] = 1;
                        Queues.Que3[2] = 1;
                        Queues.Que3[3] = 0;
                    } else if (Queues.i_for_Que3 == 5) {
                        Queues.Que3[0] = 1;
                        Queues.Que3[1] = 1;
                        Queues. Que3[2] = 1;
                        Queues.Que3[3] = 1;
                        Queues.Que3[4] = 0;
                    }
                    System.out.println("Customer Removed From Queue Successfully");
                    // Removing Customer Name_______________________________________________________________________________
                    Customers.Customers_Que3[PositionQue3 - 1][0] = "";
                    Customers.Customers_Que3[PositionQue3 - 1][1] = "";
                    Queues.i_for_Que3--;

                    for (int i = PositionQue3 - 1; i < Customers.Customers_Que3.length - 1; i++) {
                        Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name
                    Queues.i_for_Que3--;
                    StockOfBurger += Queues.Que3_Burgers[4];//Adding burgers back to stock
                    break;

                } else {
                    System.out.println("Queue is Not that Long Please enter a Valid Position");
                    break;
                }

            }

        } else if (RemoveFrom > 3 || RemoveFrom < 1) {
            System.out.println(" Enter a Valid Number!!!! ");
        }

    }

    private static void RemoveServedCustomer(Scanner scanner) {
        System.out.println();

        int CountQue1_SERVED = 0;
        int CountQue2_SERVED = 0;
        int CountQue3_SERVED = 0;

        System.out.println("From which Queue You Need to Remove a Customer Who is Served");
        int RemoveFrom_SERVED = 0;
        try {
            RemoveFrom_SERVED = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please Enter a Number Between 1-3 ");
            scanner.nextLine();
        }

        //Selecting from Which Queue A served Customer need to be removed______________________________________________
        switch (RemoveFrom_SERVED) {
            case (1):
                for (int i = 0; i < 2; i++) {
                    if (Queues.Que1[i] == 1) {
                        CountQue1_SERVED++;
                    }
                }
                if (CountQue1_SERVED == 0) {
                    System.out.println("This Queue is Already Empty Please Enter Another Queue");
                } else if (CountQue1_SERVED == 1) {
                    Queues.Que1[0] = 0;

                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que1--;
                    for (int i = 0; i < Customers.Customers_Que1.length - 1; i++) {
                        Customers.Customers_Que1[i] = Customers.Customers_Que1[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que1[Customers.Customers_Que1.length - 1][0] = "";
                    Customers.Customers_Que1[Customers.Customers_Que1.length - 1][1] = ""; //Since there is First Name and Last Name



                } else if (CountQue1_SERVED == 2) {
                    Queues.Que1[1] = 0;

                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que1--;
                    for (int i = 0; i < Customers.Customers_Que1.length - 1; i++) {
                        Customers.Customers_Que1[i] = Customers.Customers_Que1[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que1[Customers.Customers_Que1.length - 1][0] = "";
                    Customers.Customers_Que1[Customers.Customers_Que1.length - 1][1] = ""; //Since there is First Name and Last Name

                    if (Queues.Que1_Waiting[0]==1){
                        Queues.Que1[1] = 1;
                        Customers.Customers_Que1[1][0]=Customers.Customers_Que1_Waiting[0][0];// Updating the last element of Que1 with 1st of Waiting

                        for (int i = 0; i < Customers.Customers_Que1_Waiting.length - 1; i++) {
                            Customers.Customers_Que1_Waiting[i] = Customers.Customers_Que1_Waiting[i + 1];  // Shift elements to the left
                        }
                        for (int i = 0; i < 1; i++) {
                            Queues.Que1_Waiting[i] = Queues.Que1_Waiting[i + 1];  // Shift elements to the left
                        }
                        Queues.Que1_Waiting[1]=0;
                        Customers.Customers_Que1_Waiting[Customers.Customers_Que1_Waiting.length - 1][0] = "";//making last element null
                        Customers.Customers_Que1_Waiting[Customers.Customers_Que1_Waiting.length - 1][1] = ""; //Since there is First Name and Last Name

                    }
                }
                break;

            case (2):
                for (int i = 0; i < 3; i++) {
                    if (Queues.Que2[i] == 1) {
                        CountQue2_SERVED++;
                    }
                }
                if (CountQue2_SERVED == 0) {
                    System.out.println("This Queue is Already Empty Please Enter Another Queue");
                } else if (CountQue2_SERVED == 1) {
                    Queues.Que2[0] = 0;
                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que2--;
                    for (int i = 0; i < Customers.Customers_Que2.length - 1; i++) {
                        Customers.Customers_Que2[i] = Customers.Customers_Que2[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][0] = "";
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][1] = ""; //Since there is First Name and Last Name
                } else if (CountQue2_SERVED == 2) {
                    Queues.Que2[1] = 0;
                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que2--;
                    for (int i = 0; i < Customers.Customers_Que2.length - 1; i++) {
                        Customers.Customers_Que2[i] = Customers.Customers_Que2[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][0] = "";
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][1] = ""; //Since there is First Name and Last Name
                } else if (CountQue2_SERVED == 3) {
                    Queues.Que2[2] = 0;
                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que2--;
                    for (int i = 0; i < Customers.Customers_Que2.length - 1; i++) {
                        Customers.Customers_Que2[i] = Customers.Customers_Que2[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][0] = "";
                    Customers.Customers_Que2[Customers.Customers_Que2.length - 1][1] = ""; //Since there is First Name and Last Name

                    if (Queues.Que2_Waiting[0]==1){
                        System.out.println("Queue 3");
                        System.out.println(Customers.Customers_Que2_Waiting[0][0]);
                        System.out.println(Customers.Customers_Que2_Waiting[1][0]);

                        Customers.Customers_Que2[2][0]=Customers.Customers_Que2_Waiting[0][0];// Updating the last element of Que1 with 1st of Waiting(NAMES)
                        Queues.Que2[2]=1;

                        for (int i = 0; i < Customers.Customers_Que2_Waiting.length - 1; i++) {
                            Customers.Customers_Que2_Waiting[i] = Customers.Customers_Que2_Waiting[i + 1];  // Shift elements to the left
                        }
                        for (int i = 0; i < 2; i++) {
                            Queues.Que2_Waiting[i] = Queues.Que2_Waiting[i + 1];  // Shift elements to the left
                        }
                        Queues.Que2_Waiting[2]=0;

                        Customers.Customers_Que2_Waiting[Customers.Customers_Que2_Waiting.length - 1][0] = "";//making last element null
                        Customers.Customers_Que2_Waiting[Customers.Customers_Que2_Waiting.length - 1][1] = ""; //Since there is First Name and Last Name

                    }

                }
                break;


            case 3:
                for (int i = 0; i < 5; i++) {
                    if (Queues.Que3[i] == 1) {
                        CountQue3_SERVED++;
                    }
                }

                if (CountQue3_SERVED == 0) {
                    System.out.println("This Queue is Already Empty");
                } else if (CountQue3_SERVED == 1) {
                    Queues.Que3[0] = 0;
                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que3--;
                    for (int i = 0; i < Customers.Customers_Que3.length - 1; i++) {
                        Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name
                } else if (CountQue3_SERVED == 2) {
                    Queues.Que3[1] = 0;
                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que3--;
                    for (int i = 0; i < Customers.Customers_Que3.length - 1; i++) {
                        Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name

                } else if (CountQue3_SERVED == 3) {
                    Queues.Que3[2] = 0;
                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que3--;
                    for (int i = 0; i < Customers.Customers_Que3.length - 1; i++) {
                        Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name
                } else if (CountQue3_SERVED == 4) {
                    Queues.Que3[3] = 0;
                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que3--;
                    for (int i = 0; i < Customers.Customers_Que3.length - 1; i++) {
                        Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name
                } else if (CountQue3_SERVED == 5) {
                    Queues.Que3[4] = 0;
                    System.out.println("A Served Customer is Removed Successfully From the Queue");
                    Queues.i_for_Que3--;
                    for (int i = 0; i < Customers.Customers_Que3.length - 1; i++) {
                        Customers.Customers_Que3[i] = Customers.Customers_Que3[i + 1];  // Shift elements to the left
                    }
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][0] = "";
                    Customers.Customers_Que3[Customers.Customers_Que3.length - 1][1] = ""; //Since there is First Name and Last Name

                    if (Queues.Que3_Waiting[0]==1){
                        System.out.println("Queue 3");
                        System.out.println(Customers.Customers_Que3_Waiting[0][0]);
                        System.out.println(Customers.Customers_Que3_Waiting[1][0]);

                        Queues.Que3[4]=1;//Updating the last element of Que3 (QUEUES)
                        Customers.Customers_Que3[4][0]=Customers.Customers_Que3_Waiting[0][0];// Updating the last element of Que3 with 1st of Waiting

                        for (int i = 0; i < Customers.Customers_Que3_Waiting.length - 1; i++) {
                            Customers.Customers_Que3_Waiting[i] = Customers.Customers_Que3_Waiting[i + 1];  // Shift elements to the left
                        }
                        for (int i = 0; i < 4; i++) { //Updating last element of que 3 with 1st of waiting (QUEUES)
                            Queues.Que3_Waiting[i] = Queues.Que3_Waiting[i + 1];  // Shift elements to the left
                        }
                        Queues.Que3_Waiting[4]=0;

                        Customers.Customers_Que3_Waiting[Customers.Customers_Que3_Waiting.length - 1][0] = "";//making last element null
                        Customers.Customers_Que3_Waiting[Customers.Customers_Que3_Waiting.length - 1][1] = ""; //Since there is First Name and Last Name

                    }

                }
                break;
        }
    }



    //Method to Add more burgers to the stock___________________________________________________________________________
    private static void AddBurgertoStock(Scanner scanner) {
        System.out.println("How Many Burgers Do You Need to Add to the Stock: ");//Asking how many burgers to Add
        int amount = scanner.nextInt();

        StockOfBurger += amount; //Adding burgers to stock
        System.out.println(amount + ":  Burgers Added to the Stock");
        System.out.println("New stock of Burgers is:  " + StockOfBurger);

    }


    //Method which Shows if the burger stock is too low_________________________________________________________________
    private static void WarningMassege() {
        if (StockOfBurger <= 10) {
            System.out.println("Burger Stock is Below than 10 Remaining Burgers: " + StockOfBurger);
        }
        System.out.println("Remaining Burger Stock: " + StockOfBurger);
        System.out.println();
    }
    public static void Waiting_Queue_Combine(){


        //Combined Array For Waiting List
        combined_Waiting_Array[0]= Customers.Customers_Que1_Waiting[0][0];
        combined_Waiting_Array[1] =  Customers.Customers_Que1_Waiting[1][0];


        combined_Waiting_Array[2] =  Customers.Customers_Que2_Waiting[0][0];
        combined_Waiting_Array[3] =  Customers.Customers_Que2_Waiting[1][0];
        combined_Waiting_Array[4] =  Customers.Customers_Que2_Waiting[2][0];



        combined_Waiting_Array[5] =  Customers.Customers_Que3_Waiting[0][0];
        combined_Waiting_Array[6] =  Customers.Customers_Que3_Waiting[1][0];
        combined_Waiting_Array[7] =  Customers.Customers_Que3_Waiting[2][0];
        combined_Waiting_Array[8] =  Customers.Customers_Que3_Waiting[3][0];
        combined_Waiting_Array[9] =  Customers.Customers_Que3_Waiting[4][0];

    }

    public static void ViewCustomerSortedInAlphabeticalOrder() {
        //creating new Arrays Just for the First names of Customers
        String[] FirstName_Queue_1 = new String[2];
        FirstName_Queue_1[0] =  Customers.Customers_Que1[0][0];
        FirstName_Queue_1[1] =  Customers.Customers_Que1[1][0];

        String[] FirstName_Queue_2 = new String[3];
        FirstName_Queue_2[0] =  Customers.Customers_Que2[0][0];
        FirstName_Queue_2[1] =  Customers.Customers_Que2[1][0];


        String[] FirstName_Queue_3 = new String[5];
        FirstName_Queue_3[0] =  Customers.Customers_Que3[0][0];
        FirstName_Queue_3[1] =  Customers.Customers_Que3[1][0];
        FirstName_Queue_3[2] =  Customers.Customers_Que3[2][0];
        FirstName_Queue_3[3] =  Customers.Customers_Que3[3][0];
        FirstName_Queue_3[4] =  Customers.Customers_Que3[4][0];

        String[] combinedArray = new String[FirstName_Queue_1.length + FirstName_Queue_2.length + FirstName_Queue_3.length];
        System.arraycopy(FirstName_Queue_1, 0, combinedArray, 0, FirstName_Queue_1.length);
        System.arraycopy(FirstName_Queue_2, 0, combinedArray, FirstName_Queue_1.length, FirstName_Queue_2.length);
        System.arraycopy(FirstName_Queue_3, 0, combinedArray, FirstName_Queue_1.length + FirstName_Queue_2.length, FirstName_Queue_2.length);


        int nonNullCount = 0;  //Counting how many null elements in the combined array
        for (String element : combinedArray) {
            if (element != null) {
                nonNullCount++;
            }
        }
        String[] combinedArray_WithoutNull = new String[nonNullCount];
        int index = 0;
        for (String element : combinedArray) {
            if (element != null) {
                combinedArray_WithoutNull[index] = element;
                index++;
            }
        }


        int n = combinedArray_WithoutNull.length; //comparing each elements of array to arrange them in alphabetical order
        for (int i =0; i<n-1;i++){
            for(int j = 0; j<n-i-1;j++){
                if (combinedArray_WithoutNull[j].compareTo(combinedArray_WithoutNull[j + 1]) > 0) {
                    String temp = combinedArray_WithoutNull[j];
                    combinedArray_WithoutNull[j] = combinedArray_WithoutNull[j + 1];
                    combinedArray_WithoutNull[j + 1] = temp;
                }

                else continue;

            }
        }
        for (String name : combinedArray_WithoutNull) { //printing sorted names
            System.out.println(name);
        }
    }

    private static void WritingToFile() throws FileNotFoundException {
        PrintWriter WrittenFile = new PrintWriter("Customers_in_Queues");

        WrittenFile.println("*****************");
        WrittenFile.println("*   Cashiers    *");
        WrittenFile.println("*****************");

        WrittenFile.println();
        WrittenFile.println("Cashier Number One : ");
        for (int element : Queues.Que1) {
            WrittenFile.println(element + " ");
        }
        WrittenFile.println();

        WrittenFile.println("Cashier Number Two : ");
        for (int element : Queues.Que2) {
            WrittenFile.println(element + " ");
        }
        WrittenFile.println();

        WrittenFile.println("Cashier Number Three : ");
        for (int element : Queues.Que3) {
            WrittenFile.println(element + " ");
        }
        WrittenFile.println();
        WrittenFile.println("\"1\" represent the Presence of a Customer, \"0\" represents the Absence of a customer");
        WrittenFile.println();
        WrittenFile.close();

    }


    private static void LoadDataFromFile() throws FileNotFoundException {

        Scanner infile = new Scanner(new File("Customers_in_Queues"));
        //Loading Data from Queue 1
        String blank = null;
        String que= null;
        for (int i = 0; i < 5; i++) { //Reading and ignoring Useless Texts(Decorated text)
            blank = infile.nextLine();
        }
        System.out.println(blank);
        for(int i = 0;i<2;i++){
            que = infile.next();
            int que1_int= Integer.parseInt(que);
            Queues.Que1[i]=que1_int;
            System.out.println(Queues.Que1[i]);
        }
        //Loading Data From Queue 2
        System.out.println();
        blank = null;
        que= null;
        for(int i = 0 ; i<3;i++){
            blank = infile.nextLine();
        }
        for(int i = 0;i<3;i++){
            que = infile.next();
            int que1_int= Integer.parseInt(que);
            Queues.Que2[i]=que1_int;
            System.out.println(Queues.Que2[i]);
        }

        //Loading Data From Queue 3
        System.out.println();
        blank = null;
        que= null;
        for(int i = 0 ; i<3;i++){
            blank = infile.nextLine();
        }
        for(int i = 0;i<5;i++){
            que = infile.next();
            int que1_int= Integer.parseInt(que);
            Queues.Que3[i]=que1_int;
            System.out.println(Queues.Que3[i]);
        }


    }

    private static void RemainingStock() {
        System.out.print("Remaining Burgers from  the Stock are: ");
        System.out.println(StockOfBurger);
    }
    public static int que1_amount = Queues.Que1_Burgers[0]+Queues.Que1_Burgers[1];
    public static int que2_amount = Queues.Que2_Burgers[0]+Queues.Que2_Burgers[1]+Queues.Que2_Burgers[2];
    public static int que3_amount = Queues.Que3_Burgers[0]+Queues.Que3_Burgers[1]+Queues.Que3_Burgers[2]+Queues.Que3_Burgers[3]+Queues.Que3_Burgers[4];
    public static void Income() {
        int Price_of_Burger = 650;//initializing the price of a individual burger


        que1_amount = Queues.Que1_Burgers[0]+ Queues.Que1_Burgers[1];
        que2_amount = Queues.Que2_Burgers[0]+Queues.Que2_Burgers[1]+Queues.Que2_Burgers[2];
        que3_amount = Queues.Que3_Burgers[0]+Queues.Que3_Burgers[1]+Queues.Que3_Burgers[2]+Queues.Que3_Burgers[3]+Queues.Que3_Burgers[4];
        IncomeFromQueue_1 = 650*que1_amount; //calculating income based on how many customers are in the queue
        IncomeFromQueue_2 = 650*que2_amount;
        IncomeFromQueue_3 = 650*que3_amount;
        System.out.println();
        System.out.println("***** Displaying Income From Each Queues Individually *****");
        System.out.println();

        System.out.println("Income From the Queue Number 1 :Rs."+IncomeFromQueue_1);
        System.out.println("Income From the Queue Number 2 :Rs."+IncomeFromQueue_2);
        System.out.println("Income From the Queue Number 3 :Rs."+IncomeFromQueue_3);
        int totalIncome =IncomeFromQueue_1+IncomeFromQueue_2+IncomeFromQueue_3;
        System.out.println();
        System.out.println("Total Income From All the Queues:Rs."+totalIncome);

        System.out.println();
        System.out.println("Price of a burger - Rs.650.00");
        System.out.println();
    }

   
}













