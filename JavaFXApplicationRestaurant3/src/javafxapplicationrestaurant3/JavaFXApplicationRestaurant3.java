/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplicationrestaurant3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


/**
 *
 * @author hhyyt
 */
public class JavaFXApplicationRestaurant3 extends Application
{
     BorderPane root;
    TableView table;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println("heidi");
        

        root = new BorderPane();
        System.out.println("2");
        root.setTop(createTitle());

        System.out.println("3");
        root.setLeft(createMenuChoices());
        System.out.println("4");
        table = createCustomerTable();
        root.setRight(table);
        root.setRight(updateCustomerTable(table));
        System.out.println("5");
        root.setCenter(null);
        // Default layout diagram is 9.1) in the document
        Scene scene = new Scene(root, 1500, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ZAUQ Restaurant Reservation Organiser");
        primaryStage.show();

    }

    public Label createTitle()
    {
        Label my_label = new Label("ZAUQ Restaurant Reservation Organiser");
        my_label.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 30));
        my_label.setTranslateX(350);
        //my_label.setAlignment(Pos.CENTER);

        return my_label;
    }

    // Each button in the menuChoices (left-hand side) has a handle to create a new form to fill in for the required task
    public VBox createMenuChoices()
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(80);
        vbox.setTranslateY(50);

        Font buttonFont = Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 13);
        int prefSizeX = 220;
        int prefSizeY = 50;

        
        Button buttonCustomer = new Button("Customer");
        buttonCustomer.setPrefSize(prefSizeX, prefSizeY);
        buttonCustomer.setFont(buttonFont);
        buttonCustomer.setStyle("-fx-base: #00FF22;");
        
        buttonCustomer.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                createCustomerAddMenu();
            }
        });
        
        Button buttonReservation = new Button("Reservation");
        buttonReservation.setPrefSize(prefSizeX, prefSizeY);
        buttonReservation.setFont(buttonFont);
        buttonReservation.setStyle("-fx-base: #2758FF;");

        buttonReservation.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                createReservationAddMenu();
            }
        });

        
        
        /*
        Button buttonAdd = new Button("Add Customer Reservation");
        buttonAdd.setPrefSize(prefSizeX, prefSizeY);
        buttonAdd.setFont(buttonFont);
        buttonAdd.setStyle("-fx-base: #00FF22;");
        buttonAdd.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                createAddMenu();
            }
        });

        Button buttonModify = new Button("Change Customer Reservation");
        buttonModify.setPrefSize(prefSizeX, prefSizeY);
        buttonModify.setFont(buttonFont);
        buttonModify.setStyle("-fx-base: #F6FF00;");
        buttonModify.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                createModifyMenu();
            }

        });

        Button buttonDelete = new Button("Delete Customer Reservation");
        buttonDelete.setPrefSize(prefSizeX, prefSizeY);
        buttonDelete.setFont(buttonFont);
        buttonDelete.setStyle("-fx-base: #FF0000;");
        buttonDelete.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                createDeleteMenu();
            }
        });

        Button buttonMatch = new Button("Match Reservations");
        buttonMatch.setPrefSize(prefSizeX, prefSizeY);
        buttonMatch.setFont(buttonFont);
        buttonMatch.setStyle("-fx-base: #B100FF;");
        buttonMatch.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                createMatchMenu();
            }
        });
*/
        //vbox.getChildren().addAll(buttonAdd, buttonModify, buttonDelete, buttonMatch);

        vbox.getChildren().addAll(buttonCustomer, buttonReservation);
        
        
        return vbox;

    }

    // Empty until updated from SQL database
    private TableView createCustomerTable()
    {
        
        Label label = new Label("Customers");
        label.setFont(Font.font(STYLESHEET_CASPIAN, 20));

        TableView table = new TableView();
        table.setTranslateY(20);

        TableColumn<CustomerInformation, String> customerIDCol = new TableColumn<>("CustomerID");
        customerIDCol.setMinWidth(30);
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        TableColumn<CustomerInformation, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<CustomerInformation, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<CustomerInformation, String> phoneCol = new TableColumn<>("Phone Number");
        phoneCol.setMinWidth(100);
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                
        
        table.getColumns().addAll(customerIDCol, firstNameCol, lastNameCol, phoneCol);

        return table;
    }

    // Receive each Customer record from the SQL database and pass it into the TableView
    private TableView updateCustomerTable(TableView table)
    {
        
        table.getItems().clear();
        DatabaseConnect conn = new DatabaseConnect();

        Queue<CustomerInformation> queue = conn.retrieveCustomerInformation();
        CustomerInformation ci = null;
        if (queue != null)
        {
            while (queue.length() != 0)
            {
                ci = queue.pop();
                table.getItems().add(ci);
            }
        }

        return table;
    }

    private ChoiceBox createCustomerMaintenanceChoice(String currentChoice)
    {
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList("Add Customer", "Modify Customer", "Delete Customer"));
        cb.setValue(currentChoice);
        
        
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
            {
                public void changed(ObservableValue ov, Number value, Number new_value)
                {
                    if (new_value.equals(0))
                    {
                        createCustomerAddMenu();
                    }
                    else if (new_value.equals(1))
                    {
                        createCustomerModifyMenu();
                    }
                    else if (new_value.equals(2))
                    {
                        createCustomerDeleteMenu();
                    }
                }
            });
        return cb;
    }

    
    private void createCustomerAddMenu()
    {

        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5));
        vbox.setSpacing(20);
        vbox.setTranslateY(15);
        
        ChoiceBox customerMaintenanceChoice = createCustomerMaintenanceChoice("Add Customer");
        customerMaintenanceChoice.setStyle("-fx-base: Orange; -fx-control-inner-background: -fx-base ;");
        customerMaintenanceChoice.setScaleX(1.5);
        customerMaintenanceChoice.setScaleY(1.5);
        customerMaintenanceChoice.setTranslateX(30);


        
        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        TextField phoneNumber = new TextField("Phone Number");

        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if (validateName(firstName.getText()) && validateName(lastName.getText()) && validateNumber(phoneNumber.getText()))    
                {
                    
                    // Adding customer to SQL database then updating TableView
                    DatabaseConnect conn = new DatabaseConnect();

                    conn.addCustomer(firstName.getText(), lastName.getText(), phoneNumber.getText());
                    updateCustomerTable(table);

                    informationAfterConfirm.setText("Customer Added");  
            }
                else
                {
                    // Tell the user what information was not submitted correctly, so they can amend
                    String inputsToChange = "Error, please enter the following in the correct format: ";
                    if (!validateName(firstName.getText()))
                    {
                        inputsToChange += "First Name ";
                    }
                    if (!validateName(lastName.getText()))
                    {
                        inputsToChange += "Last Name ";
                    }
                    if (!validateNumber(phoneNumber.getText()))
                    {
                        inputsToChange = inputsToChange + "Phone Number ";
                    }
                    informationAfterConfirm.setText(inputsToChange);
                }
            }

        });

        vbox.getChildren().addAll(customerMaintenanceChoice, firstName, lastName, phoneNumber, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);
    }


    private void createCustomerModifyMenu()
    {
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);
        vbox.setTranslateY(50);
        
        ChoiceBox customerMaintenanceChoice = createCustomerMaintenanceChoice("Modify Customer");
        customerMaintenanceChoice.setStyle("-fx-base: Orange; -fx-control-inner-background: -fx-base ;");
        customerMaintenanceChoice.setScaleX(1.5);
        customerMaintenanceChoice.setScaleY(1.5);
        customerMaintenanceChoice.setTranslateX(30);
        


        TextField customerID = new TextField("CustomerID");
        TextField firstName = new TextField("New First Name");
        TextField lastName = new TextField("New Last Name");
        TextField phoneNumber = new TextField("New Phone Number");

        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {

                if (validateCustomerID(customerID.getText()) && validateName(firstName.getText()) && validateName(lastName.getText()) && validateNumber(phoneNumber.getText()))
                {
                    // Modify customer in SQL database then update TableView
                    DatabaseConnect conn = new DatabaseConnect();

                    conn.updateCustomer(customerID.getText(), firstName.getText(), lastName.getText(), phoneNumber.getText());
                    updateCustomerTable(table);

                    informationAfterConfirm.setText("Customer Modified");

                } 
                else
                {
                    // Tell the user what information was not submitted correctly, so they can amend
                    String inputsToChange = "Error, please enter the following in the correct format: ";
                    if (!validateCustomerID(customerID.getText()))
                    {
                        inputsToChange += "CustomerID";
                    }
                    if (!validateName(firstName.getText()))
                    {
                        inputsToChange +=  "First Name ";                        
                    }
                    if (!validateName(lastName.getText()))
                    {
                        inputsToChange +=  "LastName ";                        
                    }
                    if (!validateNumber(phoneNumber.getText()))
                    {
                        inputsToChange += "Phone Number ";
                    }
                    informationAfterConfirm.setText(inputsToChange);
                }
            }

        });

        vbox.getChildren().addAll(customerMaintenanceChoice, customerID, firstName, lastName, phoneNumber, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);

        
    }
    private void createCustomerDeleteMenu()
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);
        vbox.setTranslateY(50);
        
        ChoiceBox customerMaintenanceChoice = createCustomerMaintenanceChoice("Delete Customer");
        customerMaintenanceChoice.setStyle("-fx-base: Orange; -fx-control-inner-background: -fx-base ;");
        customerMaintenanceChoice.setScaleX(1.5);
        customerMaintenanceChoice.setScaleY(1.5);
        customerMaintenanceChoice.setTranslateX(30);
        
        TextField customerID = new TextField("CustomerID");
        Label informationAfterConfirm = new Label("(Deletes all this Customer's reservations too)");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {

                if (validateCustomerID(customerID.getText()))
                {
                    // now add customer
                    DatabaseConnect conn = new DatabaseConnect();

                    conn.deleteCustomer(customerID.getText());
                        updateCustomerTable(table);


                    informationAfterConfirm.setText("Customer Deleted");

                } 
                else
                {
                    // Tell the user CustomerID was not submitted correctly, so they can amend
                    informationAfterConfirm.setText("Error, please input a valid Customer ID");
                }
            }
        });

        vbox.getChildren().addAll(customerMaintenanceChoice, customerID, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);

    }

    
    
    
    private ChoiceBox createReservationMaintenanceChoice(String currentChoice)
    {
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList("Add Reservation", "Modify Reservation", "Delete Reservation"));
        cb.setValue(currentChoice);
        
        
        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
            {
                public void changed(ObservableValue ov, Number value, Number new_value)
                {
                    if (new_value.equals(0))
                    {
                        createReservationAddMenu();
                    }
                    else if (new_value.equals(1))
                    {
                        createReservationModifyMenu();
                    }
                    else if (new_value.equals(2))
                    {
                        createReservationDeleteMenu();
                    }
                }
            });
        return cb;
    }

    private void createReservationAddMenu()
    {

        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5));
        vbox.setSpacing(20);
        vbox.setTranslateY(15);
        
        ChoiceBox reservationMaintenanceChoice = createReservationMaintenanceChoice("Add Reservation");
        reservationMaintenanceChoice.setStyle("-fx-base: Orange; -fx-control-inner-background: -fx-base ;");
        reservationMaintenanceChoice.setScaleX(1.5);
        reservationMaintenanceChoice.setScaleY(1.5);
        reservationMaintenanceChoice.setTranslateX(30);


        
        TextField customerID = new TextField("Customer ID");
        TextField date = new TextField("Date (YYYY-MM-DD)");
        Label timeSlotText = new Label("Time Slot");
        ChoiceBox timeSlot = new ChoiceBox(FXCollections.observableArrayList("5:00PM", "8:00PM", "11:00PM"));
        timeSlot.setValue("5:00PM");
        TextField groupSize = new TextField("Group Size");
        TextField accessibilityArrangements = new TextField("Accessibility Arrangements (optional)");
        Label preferredSeatingLevelText = new Label("Preferred Seating Level");
        ChoiceBox preferredSeatingLevel = new ChoiceBox(FXCollections.observableArrayList("None", "Outside", "Ground Floor", "1st Floor"));
        preferredSeatingLevel.setValue("None");

        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if ( (validateCustomerID(customerID.getText())) && validateDate(date.getText()) && validateNumber(groupSize.getText()) )
                {
                    
                    // Adding customer to SQL database then updating TableView
                    DatabaseConnect conn = new DatabaseConnect();
                    boolean canSubmit = conn.canAddReservation(customerID.getText(), date.getText(), timeSlot.getValue().toString()); // if this new reservation would make there be more reservations than tables at the date and time, return false
                   
                    System.out.println(canSubmit);
                    
                    if (canSubmit)
                    {
                        conn.addReservation(customerID.getText(), groupSize.getText(), accessibilityArrangements.getText(), convertToInt(preferredSeatingLevel.getValue().toString()), date.getText(), timeSlot.getValue().toString());
                        updateCustomerTable(table);

                        informationAfterConfirm.setText("Reservation Added");  
                    }
                    else
                    {
                        informationAfterConfirm.setText("Error, max reservations at that date and time");
                    }
                }
                else
                {
                    // Tell the user what information was not submitted correctly, so they can amend
                    String inputsToChange = "Error, please enter the following in the correct format: ";
                    if (!validateCustomerID(customerID.getText()))
                    {
                        inputsToChange += "CustomerID ";
                    }
                    if (!validateDate(date.getText()))
                    {
                        inputsToChange +=  "Date ";                        
                    }
                    if (!validateNumber(groupSize.getText()))
                    {
                        inputsToChange +=  "Group Size ";                        
                    }
                    informationAfterConfirm.setText(inputsToChange);
                }
            }

        });

        vbox.getChildren().addAll(reservationMaintenanceChoice, customerID, date, timeSlotText, timeSlot, groupSize, accessibilityArrangements, preferredSeatingLevelText, preferredSeatingLevel, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);        
    }
    
    private void createReservationModifyMenu()
    {

        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5));
        vbox.setSpacing(20);
        vbox.setTranslateY(15);
        
        ChoiceBox reservationMaintenanceChoice = createReservationMaintenanceChoice("Modify Reservation");
        reservationMaintenanceChoice.setStyle("-fx-base: Orange; -fx-control-inner-background: -fx-base ;");
        reservationMaintenanceChoice.setScaleX(1.5);
        reservationMaintenanceChoice.setScaleY(1.5);
        reservationMaintenanceChoice.setTranslateX(30);


        
        TextField customerID = new TextField("Customer ID");
        
        TextField oldDate = new TextField("Current Date (YYYY-MM-DD)");
        Label oldTimeSlotText = new Label("Current Time Slot");
        ChoiceBox oldTimeSlot = new ChoiceBox(FXCollections.observableArrayList("5:00PM", "8:00PM", "11:00PM"));
        oldTimeSlot.setValue("5:00PM");
        
        TextField newDate = new TextField("New Date (YYYY-MM-DD)");
        Label newTimeSlotText = new Label("New Time Slot");
        ChoiceBox newTimeSlot = new ChoiceBox(FXCollections.observableArrayList("5:00PM", "8:00PM", "11:00PM"));
        newTimeSlot.setValue("5:00PM");
        
        TextField groupSize = new TextField("New Group Size");
        TextField accessibilityArrangements = new TextField("New Accessibility Arrangements (optional)");
        Label preferredSeatingLevelText = new Label("New Preferred Seating Level");
        ChoiceBox preferredSeatingLevel = new ChoiceBox(FXCollections.observableArrayList("None", "Outside", "Ground Floor", "1st Floor"));
        preferredSeatingLevel.setValue("None");

        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                 if ( (validateCustomerID(customerID.getText())) && validateDate(oldDate.getText()) && validateDate(newDate.getText()) && validateNumber(groupSize.getText()) )
                {
                    
                    DatabaseConnect conn = new DatabaseConnect();
                    boolean canModify = conn.canUpdateReservation(customerID.getText(), oldDate.getText(), oldTimeSlot.getValue().toString());
                   
                    if (canModify)
                    {
                        conn.updateReservation(customerID.getText(), oldDate.getText(), oldTimeSlot.getValue().toString(), groupSize.getText(), accessibilityArrangements.getText(), convertToInt(preferredSeatingLevel.getValue().toString()), newDate.getText(), newTimeSlot.getValue().toString());
                            updateCustomerTable(table);

                        informationAfterConfirm.setText("Reservation Modified");        
                    }
                    else
                    {
                        informationAfterConfirm.setText("Error, no reservations with this Customer at this Date and Time");
                    }
                }
                else
                {
                    // Tell the user what information was not submitted correctly, so they can amend
                    String inputsToChange = "Error, please enter the following in the correct format: ";
                    if (!validateCustomerID(customerID.getText()))
                    {
                        inputsToChange += "CustomerID ";
                    }
                    if (!validateDate(oldDate.getText()))
                    {
                        inputsToChange +=  "Current Date ";                        
                    }
                    if (!validateDate(newDate.getText()))
                    {
                        inputsToChange += "New Date ";
                    }
                    if (!validateNumber(groupSize.getText()))
                    {
                        inputsToChange +=  "Group Size ";                        
                    }
                    informationAfterConfirm.setText(inputsToChange);
                }
            }

        });

        vbox.getChildren().addAll(reservationMaintenanceChoice, customerID, oldDate, oldTimeSlotText, oldTimeSlot, newDate, newTimeSlotText, newTimeSlot, groupSize, accessibilityArrangements, preferredSeatingLevelText, preferredSeatingLevel, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);        
    }
    
    
    private void createReservationDeleteMenu()
    {

        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5));
        vbox.setSpacing(20);
        vbox.setTranslateY(15);
        
        ChoiceBox reservationMaintenanceChoice = createReservationMaintenanceChoice("Delete Reservation");
        reservationMaintenanceChoice.setStyle("-fx-base: Orange; -fx-control-inner-background: -fx-base ;");
        reservationMaintenanceChoice.setScaleX(1.5);
        reservationMaintenanceChoice.setScaleY(1.5);
        reservationMaintenanceChoice.setTranslateX(30);


        
        TextField customerID = new TextField("Customer ID");
        TextField date = new TextField("Date (YYYY-MM-DD)");
        Label timeSlotText = new Label("Time Slot");
        ChoiceBox timeSlot = new ChoiceBox(FXCollections.observableArrayList("5:00PM", "8:00PM", "11:00PM"));
        timeSlot.setValue("5:00PM");
        
        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                  if ( (validateCustomerID(customerID.getText())) && validateDate(date.getText()) )
                {
                    
                    DatabaseConnect conn = new DatabaseConnect();
                    boolean canModify = conn.canUpdateReservation(customerID.getText(), date.getText(), timeSlot.getValue().toString());
                   
                    if (canModify)
                    {
                        conn.deleteReservation(customerID.getText(), date.getText(), timeSlot.getValue().toString());
                        updateCustomerTable(table);

                        informationAfterConfirm.setText("Reservation Deleted");        
                    }
                    else
                    {
                        informationAfterConfirm.setText("Error, no reservations with this Customer at this Date and Time");
                    }
                }
                else
                {
                    // Tell the user what information was not submitted correctly, so they can amend
                    String inputsToChange = "Error, please enter the following in the correct format: ";
                    if (!validateCustomerID(customerID.getText()))
                    {
                        inputsToChange += "CustomerID ";
                    }
                    if (!validateDate(date.getText()))
                    {
                        inputsToChange +=  "Date ";                        
                    }
                    informationAfterConfirm.setText(inputsToChange);
                }
            }

        });

        vbox.getChildren().addAll(reservationMaintenanceChoice, customerID, date, timeSlotText, timeSlot, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);        
    }
    
    









    
    // 9.2) in the document
    private void createAddMenu()
    {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);
        vbox.setTranslateY(50);

        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        TextField groupSize = new TextField("Group Size");
        TextField phoneNumber = new TextField("Phone Number");
        Label timeSlotText = new Label("Time Slot");
        ChoiceBox timeSlot = new ChoiceBox(FXCollections.observableArrayList("5:00PM", "8:00PM", "11:00PM"));
        timeSlot.setValue("5:00PM");

        TextField date = new TextField("Date (YYYY-MM-DD)");
        TextField accessibilityArrangements = new TextField("Accessibility Arrangements (optional)");
        Label preferredSeatingLevelText = new Label("Preferred Seating Level");
        ChoiceBox preferredSeatingLevel = new ChoiceBox(FXCollections.observableArrayList("None", "Outside", "Ground Floor", "1st Floor"));
        preferredSeatingLevel.setValue("None");

        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if (true)//(validateInputs(firstName.getText(), lastName.getText(), groupSize.getText(), phoneNumber.getText(), timeSlot.getValue().toString(), date.getText(), accessibilityArrangements.getText(), preferredSeatingLevel.getValue().toString()))
                {
                    
                    // Adding customer to SQL database then updating TableView
                    DatabaseConnect conn = new DatabaseConnect();
                    boolean canSubmit = true;//conn.canAddCustomer(date.getText(), timeSlot.getValue().toString()); // if this new reservation would make there be more reservations than tables at the date and time, return false
                   
                    if (canSubmit)
                    {
                        updateCustomerTable(table);

                        informationAfterConfirm.setText("Customer Added");  
                    }
                    else
                    {
                        informationAfterConfirm.setText("Error, max reservations at that date and time");
                    }
                }
                else
                {
                    // Tell the user what information was not submitted correctly, so they can amend
//                    informationAfterConfirm.setText(whichInputsToChange(firstName.getText(), lastName.getText(), groupSize.getText(), phoneNumber.getText(), timeSlot.getValue().toString(), date.getText(), accessibilityArrangements.getText(), preferredSeatingLevel.getValue().toString()));
                }
            }

        });

        vbox.getChildren().addAll(firstName, lastName, groupSize, phoneNumber, timeSlotText, timeSlot, date, accessibilityArrangements, preferredSeatingLevelText, preferredSeatingLevel, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);

    }

    // 9.3) in the document
    private void createModifyMenu()
    {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);
        vbox.setTranslateY(50);

        TextField customerID = new TextField("CustomerID");
        TextField firstName = new TextField("New First Name");
        TextField lastName = new TextField("New Last Name");
        TextField groupSize = new TextField("New Group Size");
        TextField phoneNumber = new TextField("New Phone Number");
        Label timeSlotText = new Label("New Time Slot");
        ChoiceBox timeSlot = new ChoiceBox(FXCollections.observableArrayList("5:00PM", "8:00PM", "11:00PM"));
        timeSlot.setValue("5:00PM");

        TextField date = new TextField("New Date (YYYY-MM-DD)");
        TextField accessibilityArrangements = new TextField("New Accessibility Arrangements (optional)");
        Label preferredSeatingLevelText = new Label("New Preferred Seating Level");
        ChoiceBox preferredSeatingLevel = new ChoiceBox(FXCollections.observableArrayList("None", "Outside", "Ground Floor", "1st Floor"));
        preferredSeatingLevel.setValue("None");

        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {

                if (true)//((validateCustomerID(customerID.getText())) && (validateInputs(firstName.getText(), lastName.getText(), groupSize.getText(), phoneNumber.getText(), timeSlot.getValue().toString(), date.getText(), accessibilityArrangements.getText(), preferredSeatingLevel.getValue().toString())))
                {
                    // Modify customer in SQL database then update TableView
                    DatabaseConnect conn = new DatabaseConnect();

//                    conn.updateCustomer(customerID.getText(), firstName.getText(), lastName.getText(), phoneNumber.getText(), accessibilityArrangements.getText(), convertToInt(preferredSeatingLevel.getValue().toString()), groupSize.getText());
                    //conn.updateReservation(customerID.getText(), date.getText(), timeSlot.getValue().toString());
                   // updateCustomerTable(table);

                    informationAfterConfirm.setText("Customer Modified");

                } 
                else
                {
                    // Tell the user what information was not submitted correctly, so they can amend
                    //informationAfterConfirm.setText(whichInputsToChange(customerID.getText(), firstName.getText(), lastName.getText(), groupSize.getText(), phoneNumber.getText(), timeSlot.getValue().toString(), date.getText(), accessibilityArrangements.getText(), preferredSeatingLevel.getValue().toString()));
                }
            }

        });

        vbox.getChildren().addAll(customerID, firstName, lastName, groupSize, phoneNumber, timeSlotText, timeSlot, date, accessibilityArrangements, preferredSeatingLevelText, preferredSeatingLevel, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);

    }

    // 9.4) in the document
    private void createDeleteMenu()
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);
        vbox.setTranslateY(50);

        TextField customerID = new TextField("CustomerID");
        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(14));
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {

                if (validateCustomerID(customerID.getText()))
                {
                    // now add customer
                    DatabaseConnect conn = new DatabaseConnect();

                    conn.deleteCustomer(customerID.getText());
                   // conn.deleteReservation(customerID.getText());

                   // updateCustomerTable(table);

                    informationAfterConfirm.setText("Customer Deleted");

                } 
                else
                {
                    // Tell the user CustomerID was not submitted correctly, so they can amend
                    informationAfterConfirm.setText("Error, please input a valid Customer ID");
                }
            }
        });

        vbox.getChildren().addAll(customerID, buttonConfirm, informationAfterConfirm);

        root.setCenter(vbox);

    }

    // 9.5) in the document
    private void createMatchMenu()
    {
        ImageView imageView = new ImageView();
        Image image = null;
        try
        {
            image = new Image(new FileInputStream("image.jpg"));

        } catch (FileNotFoundException ex)
        {
        }
        imageView = new ImageView(image);
        imageView.setTranslateX(270);
        imageView.setTranslateY(-80);

            
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);
        vbox.setTranslateY(50);

        Label timeSlotText = new Label("Time Slot");
        ChoiceBox timeSlot = new ChoiceBox(FXCollections.observableArrayList("5:00PM", "8:00PM", "11:00PM"));
        timeSlot.setValue("5:00PM");

        TextField date = new TextField("Date (YYYY-MM-DD)");

        Label informationAfterConfirm = new Label("");
        informationAfterConfirm.setFont(Font.font(13));
        informationAfterConfirm.setWrapText(true);
        informationAfterConfirm.setMinHeight(400);
        informationAfterConfirm.setTranslateY(-490);
        informationAfterConfirm.toBack();
        Button buttonConfirm = new Button("Confirm");
        buttonConfirm.setPrefSize(250, 100);
        buttonConfirm.setFont(Font.font(STYLESHEET_CASPIAN, 30));
        buttonConfirm.setStyle("-fx-base: #85FF3B;");
        buttonConfirm.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {

                if (true)//(validateInputs(timeSlot.getValue().toString(), date.getText()))
                {
                    // now add customer
                    DatabaseConnect conn = new DatabaseConnect();

                    // Get Reservation IDs that match time and date
                    LinkedListQueueCustomerReservation queueCustomerReservation = conn.getReservations(date.getText(), timeSlot.getValue().toString());
                    LinkedListQueueTable queueTable = conn.getTables();

                    if (queueCustomerReservation.isEmpty())
                    {
                        informationAfterConfirm.setText("Error, no reservations at that date and time");
                    }
                    else
                    {
                        Hungarian hungarian = new Hungarian();
                        String result = hungarian.performHungarian(queueCustomerReservation, queueTable);

                        informationAfterConfirm.setText("Success, here are the results " + result);
                        

                        // Get Tables
                        // Create Scores for each Reservation and Table
                        // Match them to best ones
                        // Each index in the matrix will have a row (reservationID0 and column(Table)
                        // Put them in a list of all tables matched with reservations
                    }

                } else
                {
                    // Tell the user date was not submitted correctly, timeSlot will never be incorrectly inputted because
                    // It is a multiple choice box
                    informationAfterConfirm.setText("Error, please input valid date ");
                }
            }

        });

        vbox.getChildren().addAll(timeSlotText, timeSlot, date, buttonConfirm,  imageView, informationAfterConfirm);

        root.setCenter(vbox);

    }


    private boolean validateName(String name)
    {
        return !(name.isBlank());
    }
    private boolean validateNumber(String number)
    {
        return isInteger(number);
    }
    private boolean validateDate(String date)
    {
        return isDate(date);
    }
    
   
    
    private boolean validateCustomerID(String string)
    {
        DatabaseConnect conn = new DatabaseConnect();

        if (string == null)
        {
            return false;
        }

        try
        {
            Integer.parseInt(string);
        } catch (NumberFormatException nfe)
        {
            return false;
        }

        return conn.foundCustomerID(string);
    }

    
    private boolean isInteger(String string)
    {
        if (string == null)
        {
            return false;
        }
        try
        {
            Long.parseLong(string);
        } catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    private boolean isDate(String string)
    {
        if (string == null)
        {
            return false;
        }
        try
        {
            //formatter.parse(string);
            LocalDate.parse(string, DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));

        } catch (DateTimeParseException e)
        {
            return false;
        }
        return true;
    }

    private String whichInputsToChange(String firstName, String lastName, String groupSize, String phoneNumber, String timeSlot, String date, String accessibilityArrangements, String preferredSeatingLevel)
    {
        String inputsToChange = "Error, please enter the following in the correct format:";

        if (firstName.isBlank())
        {
            inputsToChange = inputsToChange + " First Name,";
        }
        if (lastName.isBlank())
        {
            inputsToChange = inputsToChange + " Last Name,";
        }
        if (!isInteger(groupSize))
        {
            inputsToChange = inputsToChange + " Group Size,";
        }
        if (!isInteger(phoneNumber))
        {
            inputsToChange = inputsToChange + " Phone Number,";
        }
        if (!isDate(date))
        {
            inputsToChange = inputsToChange + " Date,";
        }

        return inputsToChange;
    }

    private String whichInputsToChange(String customerID, String firstName, String lastName, String groupSize, String phoneNumber, String timeSlot, String date, String accessibilityArrangements, String preferredSeatingLevel)
    {
        String inputsToChange = "Error, please enter the following in the correct format:";

        if (!(validateCustomerID(customerID)))
        {
            inputsToChange = inputsToChange + " Customer ID,";
        }
        if (firstName.isBlank())
        {
            inputsToChange = inputsToChange + " First Name,";
        }
        if (lastName.isBlank())
        {
            inputsToChange = inputsToChange + " Last Name,";
        }
        if (!isInteger(groupSize))
        {
            inputsToChange = inputsToChange + " Group Size,";
        }
        if (!isInteger(phoneNumber))
        {
            inputsToChange = inputsToChange + " Phone Number,";
        }
        if (!isDate(date))
        {
            inputsToChange = inputsToChange + " Date,";
        }

        return inputsToChange;
    }
    
    
   /* private String whichInputsToChange(int firstName, String lastName, String phoneNumber)
    {
        String inputsToChange = "Error, please enter the following in the correct format:";

        if (firstName.isBlank())
        {
            inputsToChange = inputsToChange + " First Name,";
        }
        if (lastName.isBlank())
        {
            inputsToChange = inputsToChange + " Last Name,";
        }
        if (!isInteger(phoneNumber))
        {
            inputsToChange = inputsToChange + " Phone Number,";
        }

        return inputsToChange;
    }
 */   
    private String whichInputsToChange(String customerID, String firstName, String lastName, String phoneNumber)
    {
        String inputsToChange = "Error, please enter the following in the correct format:";

        if (!(validateCustomerID(customerID)))
        {
            inputsToChange = inputsToChange + " Customer ID,";
        }
        if (firstName.isBlank())
        {
            inputsToChange = inputsToChange + " First Name,";
        }
        if (lastName.isBlank())
        {
            inputsToChange = inputsToChange + " Last Name,";
        }
        if (!isInteger(phoneNumber))
        {
            inputsToChange = inputsToChange + " Phone Number,";
        }

        return inputsToChange;
    }
    
    
    
    
    public String convertToInt(String string)
    {
        String returnString = "";
        if (string == "None")
        {
            returnString = "0";
        } else if (string == "Outside")
        {
            returnString = "1";
        } else if (string == "Ground Floor")
        {
            returnString = "2";
        } else
        {
            returnString = "3";
        }
        return returnString;
    }

    public static void main(String[] args)
    {

        launch(args);

    }   
}
