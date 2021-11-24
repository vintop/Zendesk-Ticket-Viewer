package com.company.presenter;

import org.json.JSONArray;
import org.json.JSONObject;

public class TicketViewerPresenter {

    private static int PAGE_LIMIT = 25; //max tickets to display per page

    public int displayTickets(JSONObject ticketsJSON, int pageNumber){
        JSONArray ticketsArr = new JSONArray();
        ticketsArr = ticketsJSON.getJSONArray("tickets");

        int ticketCount = ticketsArr.length();
        int pageTotal = ticketCount / PAGE_LIMIT;

        if(pageNumber > pageTotal){
            //we've reached a page that doesn't exist
            pageNumber = 1;
        }else if(pageNumber < 1){
            //we've reached a page that doesn't exist
            pageNumber = pageTotal;
        }

        int ticketsOnPage = 0;
        int offset = (pageNumber - 1)*PAGE_LIMIT;

        for(int i = 0+offset; i < PAGE_LIMIT+offset; i++){
            //check we haven't run out of tickets to display
            if(ticketsArr.getJSONObject(i).isNull("id")){
                break;
            }
            //display the ticket information
            printTicket(ticketsArr.getJSONObject(i).getInt("id"),
                    ticketsArr.getJSONObject(i).getString("status"),
                    ticketsArr.getJSONObject(i).getString("subject"),
                    ticketsArr.getJSONObject(i).getInt("requester_id"),
                    ticketsArr.getJSONObject(i).getString("updated_at"));
            ticketsOnPage++;
        }

        System.out.println("---------------------------------");
        System.out.println("Displaying " + ticketsOnPage + " tickets on Page " + pageNumber + " of " + pageTotal
                + " (n for next, b for prev, menu to return to menu)");


        return pageNumber;
    }

    public void displaySingleTicket(JSONObject ticketsJSON){
        //display the ticket information
        printTicket(ticketsJSON.getInt("id"), ticketsJSON.getString("status"),
                ticketsJSON.getString("subject"), ticketsJSON.getInt("requester_id"),
                ticketsJSON.getString("updated_at"));
    }

    public void printTicket(int id, String status, String subject, int requesterID, String updatedAt){
        System.out.println("[" + status + "]" +
                " Ticket " + id +
                " subject '" + subject + "'" +
                " opened by " + requesterID +
                " updated " + updatedAt);

    }

}
