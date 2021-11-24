package com.company.presenter;

import com.company.viewmodel.TicketsViewModel;
import com.google.inject.Inject;
import org.json.JSONObject;

import java.util.Scanner;

/*
 * The MainViewPresenter contains common messages displayed to the user so that they
 * can be re-used in methods and altered in a more efficient manner.
 */
public class MainViewPresenter {

    private static String[] menuOptions = {"Display All Tickets","Display A Single Ticket","Quit Program"};
    private static String welcomeMsg = "Hello and welcome to the Zendesk ticket viewer!";
    private static String welcomeInstructions = "Please type menu to view the menu, or q to quit the program: ";
    private static String menuHeader = "---------------------------------\nMenu:";
    private static String menuInstructions = "Please type a number to select a menu option (or type q to quit): ";
    private static String confirmExit = "Are you sure you wish exit the program? (y / n): ";
    private static String quitMsg = "Thanks for using this Zendesk Ticket Viewer! Farewell~";
    private static String unrecognizedInputErr = "Error: Unrecognized input, please enter your selection again: ";
    private static String askTicketID = "Please enter a ticket ID: ";
    private static String invalidTicketID = "Invalid ticket ID, please try again. (or type menu to return to the menu)";
    private static String invalidPageCommand = "Invalid page command, type n for next, b for back, menu for menu, q for quit:";

    private TicketsViewModel viewModel;
    private TicketViewerPresenter ticketViewerPresenter;

    private static Scanner sc;

    private String getInput(){
        sc = new Scanner(System.in);
        String input = sc.next();
        return input;
    }

    @Inject
    public MainViewPresenter(TicketsViewModel viewModel) {
        this.viewModel = viewModel;
        this.ticketViewerPresenter = new TicketViewerPresenter();
    }

    public void runWelcomeMenu(){
        printWelcome();
        String input = getInput();

        while(true){
            if(input.contains("menu")){
                runMainMenu();
                return;

            }else if(input.contains("q")){
                quit();
                return;
            }else{
                unrecognizedInput();
            }
            input = getInput();
        }

    }

    public void runMainMenu(){
        String input;

        while(true){
            //display the menu and receive input
            printMainMenu();
            input = getInput();

            //check input and act accordingly
            if(input.contains("1")){ //Display All Tickets
                displayAllTickets();
            }
            else if (input.contains("2")){ //Display A Single Ticket
                displaySingleTicket();
            }
            else if (input.contains("3") || input.contains("q")){ //Quit Program
                quit();
                return;
            }
            else{
                unrecognizedInput();
            }
        }
    }

    public void displayAllTickets(){
        String input = "";
        int pageNumber = 1;
        JSONObject ticketsJSON = new JSONObject();

        try{
            ticketsJSON = viewModel.getAllTickets(); //get the tickets
        }catch(Exception e){
            System.out.println("ERROR: Could not successfully get your tickets.");
            return;
        }
        pageNumber = ticketViewerPresenter.displayTickets(ticketsJSON, pageNumber); //display current ticket page

        while(true){
            input = getInput();

            if(input.contains("q") || input.contains("menu")){
                break; //return to menu
            }else if(input.contains("n")){
                pageNumber++;
                pageNumber = ticketViewerPresenter.displayTickets(ticketsJSON, pageNumber); //display next ticket page
            }else if(input.contains("b")){
                pageNumber--;
                pageNumber = ticketViewerPresenter.displayTickets(ticketsJSON, pageNumber); //display previous ticket page
            }else{
                invalidPageCommand();
                //api.displayTickets(ticketsJSON, offset); //display current ticket page
            }
        }
    }

    public void displaySingleTicket(){
        String id = "";
        JSONObject ticketsJSON = new JSONObject();

        askTicketID();
        id = getInput();

        try{
            ticketsJSON = viewModel.getTicketByID(id); //get the ticket
        }catch(Exception e){
            System.out.println("ERROR: Could not find ticket "+ id +". Please check the ID and try again.");
            return;
        }

        ticketViewerPresenter.displaySingleTicket(ticketsJSON); //display the ticket

        return;
    }

    public void printMainMenu(){
        printMenuHeader();
        printMenuOptions();
        printMenuInstructions();
        return;
    }

    public void printMenuInstructions(){
        System.out.println(menuInstructions);
        return;
    }

    public void printWelcome(){
        System.out.println(welcomeMsg);
        System.out.println(welcomeInstructions);
        return;
    }

    public void printMenuHeader(){
        System.out.println(menuHeader);
        return;
    }

    public void quit(){
        System.out.println(quitMsg);
    }

    public void unrecognizedInput(){
        System.out.println(unrecognizedInputErr);
    }

    public void printMenuOptions(){
        for(int i = 1; i <= menuOptions.length; i++){
            System.out.println(i + ": "+ menuOptions[i-1]);
        }
        System.out.println("---------------------------------");

    }

    public void askTicketID(){
        System.out.println(askTicketID);
    }

    public void invalidTicket(){
        System.out.println(invalidTicketID);
    }

    public void invalidPageCommand(){
        System.out.println(invalidPageCommand);
    }

}
