package Services;

import Entity.InformationTicket.InformationTicket;
import Entity.InformationTicket.InformationTicketRepository;
import Entity.PresentationUser.PresentationUser;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InformationTicketService {

    @Resource
    InformationTicketRepository informationTicketRepository;

    public InformationTicket select(long id) {
        return informationTicketRepository.findOne(id);
    }

    public InformationTicket create(InformationTicket informationTicket) {
        return informationTicketRepository.save(informationTicket);
    }

    public InformationTicket update(InformationTicket informationTicket) {
        return informationTicketRepository.save(informationTicketRepository.findOne(informationTicket.getId()));
    }

    public void delete(long id) {
        informationTicketRepository.delete(id);
    }

    public List<InformationTicket> selectAll() {
        return StreamSupport.stream(informationTicketRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public List<InformationTicket> selectAll(List<Long> id) {
        return StreamSupport.stream(informationTicketRepository.findAll(id).spliterator(), false)
                .collect(Collectors.toList());
    }
}
