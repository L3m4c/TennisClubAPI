package Controllers;

import Dto.InformationTicketDto;
import Entity.InformationTicket.InformationTicket;
import services.InformationTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InformationTicketController {

    @Autowired
    InformationTicketService informationTicketService;

    @RequestMapping(value = "/informationTickets", method = RequestMethod.GET)
    @ResponseBody
    public List<InformationTicket> getAllTicketInformation() {
        return informationTicketService.selectAll();
    }

    @RequestMapping(value = "/informationTicket/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InformationTicket getTicketInformation(@PathVariable long id) {
        return informationTicketService.select(id);
    }


    @RequestMapping(value = "/informationTicket", method = RequestMethod.POST)
    @ResponseBody
    public InformationTicket createTicketInformation(@RequestBody InformationTicketDto informationTicketDto)

    {
        return informationTicketService.create(informationTicketService.dtoToEntity(informationTicketDto));
    }

    @RequestMapping(value = "/informationTicket", method = RequestMethod.PUT)
    @ResponseBody
    public InformationTicket updateTicketInformation(@RequestBody InformationTicketDto informationTicketDto)

    {
        return informationTicketService.update(informationTicketService.dtoToEntity(informationTicketDto));
    }

    @RequestMapping(value = "/informationTicket/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteTicketInformation(@PathVariable long id) {
        informationTicketService.delete(id);
    }

}
