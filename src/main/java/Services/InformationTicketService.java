package services;

import dto.InformationTicketDto;
import entity.InformationTicket.InformationTicket;
import entity.InformationTicket.InformationTicketRepository;
import entity.User.User;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InformationTicketService {

    @Resource
    InformationTicketRepository informationTicketRepository;
    @Resource
    UserService userService;

    public InformationTicket select(long id) {
        return informationTicketRepository.findOne(id);
    }

    public InformationTicket create(InformationTicket informationTicket) {
        return informationTicketRepository.save(informationTicket);
    }

    public InformationTicket create(String title, String content, long authorId) {
        InformationTicket informationTicket = new InformationTicket();
        informationTicket.setTitle(title);
        informationTicket.setContent(content);
        User author = userService.select(authorId);
        informationTicket.setCreatedBy(author);
        informationTicket.setModifiedBy(author);
        informationTicket.setCreatedDate(new Date());
        informationTicket.setModifiedDate(new Date());
        return create(informationTicket);
    }

    public InformationTicket update(InformationTicket informationTicket) {
        return informationTicketRepository.save(informationTicket);
    }

    public InformationTicket update(long id, String title, String content, long authorId) {
        InformationTicket informationTicket = new InformationTicket();
        informationTicket.setId(id);
        informationTicket.setTitle(title);
        informationTicket.setContent(content);
        User author = userService.select(authorId);
        informationTicket.setCreatedBy(author);
        informationTicket.setModifiedBy(author);
        informationTicket.setCreatedDate(new Date());
        informationTicket.setModifiedDate(new Date());
        return update(informationTicket);
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


    public InformationTicket dtoToEntity(InformationTicketDto informationTicketDto) {
        InformationTicket informationTicket = new InformationTicket();
        informationTicket.setId(informationTicketDto.getId());
        informationTicket.setTitle(informationTicketDto.getTitle());
        informationTicket.setContent(informationTicketDto.getContent());
        User author = userService.select(informationTicketDto.getAuthorId());
        informationTicket.setCreatedBy(author);
        informationTicket.setModifiedBy(author);
        informationTicket.setCreatedDate(new Date());
        informationTicket.setModifiedDate(new Date());
        return informationTicket;
    }
}
