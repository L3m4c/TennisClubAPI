package controllers;

import entity.InformationTicket.InformationTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import services.InformationTicketService;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    InformationTicketService informationTicketService;

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    @ResponseBody
    public List<InformationTicket> getAllTicket() {
        return informationTicketService.selectAll();
    }


}
