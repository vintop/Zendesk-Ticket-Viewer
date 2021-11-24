package com.company.viewmodel;

import com.company.usecases.GetTicketByIdUsecase;
import com.company.usecases.GetTicketsUsecase;
import com.google.inject.Inject;
import org.json.JSONObject;

public class TicketsViewModel {

    private  GetTicketsUsecase getTicketsUsecase;
    private GetTicketByIdUsecase getTicketByIdUsecase;

    @Inject
    public TicketsViewModel(GetTicketsUsecase getTicketsUsecase,
                            GetTicketByIdUsecase getTicketByIdUsecase
    ){
        this.getTicketsUsecase = getTicketsUsecase;
        this.getTicketByIdUsecase = getTicketByIdUsecase;
    }

    public JSONObject getAllTickets() {
        return getTicketsUsecase.getAllTickets();
    }

    public JSONObject getTicketByID(String ticketID) {
        return getTicketByIdUsecase.getTicketByID(ticketID);
    }

}
