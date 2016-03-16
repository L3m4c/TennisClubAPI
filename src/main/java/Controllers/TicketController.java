package Controllers;

import Entity.InformationTicket.InformationTicket;
import services.InformationTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
