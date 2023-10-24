package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class HelloController {


    @FXML
    private Label que1_pos1;
    @FXML
    private Label que1_pos2;

    @FXML
    private Label que2_pos1;
    @FXML
    private Label que2_pos2;
    @FXML
    private Label que2_pos3;
    @FXML
    private Label que3_pos1;
    @FXML
    private Label que3_pos2;
    @FXML
    private Label que3_pos3;
    @FXML
    private Label que3_pos4;
    @FXML
    private Label que3_pos5;
    @FXML
    private Label que1Wait_pos1;
    @FXML
    private Label que1Wait_pos2;
    @FXML
    private Label que2Wait_pos1;
    @FXML
    private Label que2Wait_pos2;
    @FXML
    private Label que2Wait_pos3;
    @FXML
    private Label que3Wait_pos1;
    @FXML
    private Label que3Wait_pos2;
    @FXML
    private Label que3Wait_pos3;
    @FXML
    private Label que3Wait_pos4;
    @FXML
    private Label que3Wait_pos5;
    @FXML
    private AnchorPane WaitingQueuePane;
    @FXML
    private Label income_que1;
    @FXML
    private Label income_que2;
    @FXML
    private Label income_que3;
    @FXML
    private AnchorPane incomePane;
    @FXML
    private AnchorPane search_pane;
    @FXML
    private TextField search_textfield;
    @FXML
    private Label name_search;
    @FXML
    private Label queue_search;;
    @FXML
    private Label burgers_search;;
    @FXML
    private Button exit_Button;
    @FXML
    private Button exit_Button2;




    public void initialize() {
        search_pane.setVisible(false);
        WaitingQueuePane.setVisible(false);
        incomePane.setVisible(false);
//Displaying Queues
        //Queue 1 Initializing
        if (Queues.Que1[0] == 1) {
            que1_pos1.setText(Customers.Customers_Que1[0][0]);
        } else {
            que1_pos1.setText("Empty");
        }

        if (Queues.Que1[1] == 1) {
            que1_pos2.setText(Customers.Customers_Que1[1][0]);
        } else {
            que1_pos2.setText("Empty");
        }

        //Queue 2 Initializing
        if (Queues.Que2[0] == 1) {
            que2_pos1.setText(Customers.Customers_Que2[0][0]);
        } else {
            que2_pos1.setText("Empty");
        }

        if (Queues.Que2[1] == 1) {
            que2_pos2.setText(Customers.Customers_Que2[1][0]);
        } else {
            que2_pos2.setText("Empty");
        }

        if (Queues.Que2[2] == 1) {
            que2_pos3.setText(Customers.Customers_Que2[2][0]);
        } else {
            que2_pos3.setText("Empty");
        }


        //Queue 3 Initializing
        if (Queues.Que3[0] == 1) {
            que3_pos1.setText(Customers.Customers_Que3[0][0]);
        } else {
            que3_pos1.setText("Empty");
        }

        if (Queues.Que3[1] == 1) {
            que3_pos2.setText(Customers.Customers_Que3[1][0]);
        } else {
            que3_pos2.setText("Empty");
        }

        if (Queues.Que3[2] == 1) {
            que3_pos3.setText(Customers.Customers_Que3[2][0]);
        } else {
            que3_pos3.setText("Empty");
        }
        if (Queues.Que3[3] == 1) {
            que3_pos4.setText(Customers.Customers_Que3[3][0]);
        } else {
            que3_pos4.setText("Empty");
        }
        if (Queues.Que3[4] == 1) {
            que3_pos5.setText(Customers.Customers_Que3[4][0]);
        } else {
            que3_pos5.setText("Empty");
        }

        //Displaying Waiting Queues

            if (HelloApplication.combined_Waiting_Array[0] != null) {
                que1Wait_pos1.setText(HelloApplication.combined_Waiting_Array[0]);
            } else {
                que1Wait_pos1.setText("EMPTY");
            }

        if (HelloApplication.combined_Waiting_Array[1] != null) {
            que1Wait_pos2.setText(HelloApplication.combined_Waiting_Array[1]);
        } else {
            que1Wait_pos2.setText("EMPTY");
        }

        if (HelloApplication.combined_Waiting_Array[2] != null) {
            que2Wait_pos1.setText(HelloApplication.combined_Waiting_Array[2]);
        } else {
            que2Wait_pos1.setText("EMPTY");
        }

        if (HelloApplication.combined_Waiting_Array[3] != null) {
            que2Wait_pos2.setText(HelloApplication.combined_Waiting_Array[3]);
        } else {
            que2Wait_pos2.setText("EMPTY");
        }

        if (HelloApplication.combined_Waiting_Array[4] != null) {
            que2Wait_pos3.setText(HelloApplication.combined_Waiting_Array[4]);
        } else {
            que2Wait_pos3.setText("EMPTY");
        }

        if (HelloApplication.combined_Waiting_Array[5] != null) {
            que3Wait_pos1.setText(HelloApplication.combined_Waiting_Array[5]);
        } else {
            que3Wait_pos1.setText("EMPTY");
        }
        if (HelloApplication.combined_Waiting_Array[6] != null) {
            que3Wait_pos2.setText(HelloApplication.combined_Waiting_Array[6]);
        } else {
            que3Wait_pos2.setText("EMPTY");
        }
        if (HelloApplication.combined_Waiting_Array[7] != null) {
            que3Wait_pos3.setText(HelloApplication.combined_Waiting_Array[7]);
        } else {
            que3Wait_pos3.setText("EMPTY");
        }
        if (HelloApplication.combined_Waiting_Array[8] != null) {
            que3Wait_pos4.setText(HelloApplication.combined_Waiting_Array[8]);
        } else {
            que3Wait_pos4.setText("EMPTY");
        }
        if (HelloApplication.combined_Waiting_Array[9] != null) {
            que3Wait_pos5.setText(HelloApplication.combined_Waiting_Array[9]);
        } else {
            que3Wait_pos5.setText("EMPTY");
        }
        HelloApplication.que1_amount = Queues.Que1_Burgers[0]+ Queues.Que1_Burgers[1];
        HelloApplication.que2_amount = Queues.Que2_Burgers[0]+ Queues.Que2_Burgers[1]+ Queues.Que2_Burgers[2];
        HelloApplication.que3_amount = Queues.Que3_Burgers[0]+ Queues.Que3_Burgers[1]+ Queues.Que3_Burgers[2]+ Queues.Que3_Burgers[3]+ Queues.Que3_Burgers[4];

        //Initializing Income Page
        HelloApplication.IncomeFromQueue_1 = 650* HelloApplication.que1_amount; //calculating income based on how many customers are in the queue
        HelloApplication.IncomeFromQueue_2 = 650* Queues.i_for_Que2;
        HelloApplication.IncomeFromQueue_3 = 650* Queues.i_for_Que3;
        income_que1.setText(String.valueOf(HelloApplication.IncomeFromQueue_1));
        income_que2.setText(String.valueOf(HelloApplication.IncomeFromQueue_2));
        income_que3.setText(String.valueOf(HelloApplication.IncomeFromQueue_3));


    }
    public void ClickWaitinglist(){

        WaitingQueuePane.setVisible(true);
        incomePane.setVisible(false);
        search_pane.setVisible(false);

    }
    public void ClickIncome(){

        WaitingQueuePane.setVisible(false);
        incomePane.setVisible(true);
        search_pane.setVisible(false);

    }

    public void ClickSearch(){
        String searchname = search_textfield.getText();

        for (int i = 0 ; i<2;i++){
            if (Objects.equals(Customers.Customers_Que1[i][0], searchname)) {
                name_search.setText(Customers.Customers_Que1[i][0]+" "+(Customers.Customers_Que1[i][1]));
                queue_search.setText("Queue No: 1");
                burgers_search.setText("Amount of Burgers Ordered: "+String.valueOf(Queues.Que1_Burgers[i]));
                break;
            } else {
                continue;
            }
        }for (int i = 0; i<3 ; i++){
            if (Objects.equals(Customers.Customers_Que2[i][0], searchname)) {
                name_search.setText((Customers.Customers_Que2[i][0]+" "+(Customers.Customers_Que2[i][1])));
                queue_search.setText("Queue No: 2");
                burgers_search.setText("Amount of Burgers Ordered: "+String.valueOf(Queues.Que2_Burgers[i]));
                break;
            } else {
                continue;
            }
        }
        for (int i = 0; i<5 ; i++){
            if (Objects.equals(Customers.Customers_Que3[i][0], searchname)) {
                name_search.setText(Customers.Customers_Que3[i][0]+" "+(Customers.Customers_Que3[i][1]));
                queue_search.setText("Queue No: 3");
                burgers_search.setText("Amount of Burgers Ordered: "+String.valueOf(Queues.Que3_Burgers[i]));
                break;
            } else {
                continue;
            }
        }

    }
    public void ClickSearch_Customer(){

        search_pane.setVisible(true);
        WaitingQueuePane.setVisible(false);
        incomePane.setVisible(false);

        //Searching for specific customer

    }
    public void ClickExit(){

        search_pane.setVisible(false);
        WaitingQueuePane.setVisible(false);
        incomePane.setVisible(false);



    }

    }

