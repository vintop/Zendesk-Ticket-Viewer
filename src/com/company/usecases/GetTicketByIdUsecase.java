package com.company.usecases;

import com.company.repository.TicketsRepository;
import com.google.inject.Inject;
import org.json.JSONObject;

public class GetTicketByIdUsecase {

    private TicketsRepository repository;

    @Inject
    public GetTicketByIdUsecase(TicketsRepository repository){
        this.repository = repository;
    }

    public JSONObject getTicketByID(String id) {
        return repository.getTicketByID(id);
    }

}
