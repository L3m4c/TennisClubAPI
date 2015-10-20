package Services;

import Entity.PresentationUser.PresentationUser;
import Entity.PresentationUser.PresentationUserRepository;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PresentationUserService {

    @Resource
    PresentationUserRepository presentationUserRepository;

    public PresentationUser select(long id) {
        return presentationUserRepository.findOne(id);
    }

    public PresentationUser create(PresentationUser presentationUser) {
        return presentationUserRepository.save(presentationUser);
    }

    public PresentationUser update(PresentationUser presentationUser) {
        return presentationUserRepository.save(presentationUserRepository.findOne(presentationUser.getId()));
    }

    public void delete(long id) {
        presentationUserRepository.delete(id);
    }

    public List<PresentationUser> selectAll() {
        return StreamSupport.stream(presentationUserRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public List<PresentationUser> selectAll(List<Long> id) {
        return StreamSupport.stream(presentationUserRepository.findAll(id).spliterator(), false)
                .collect(Collectors.toList());
    }
}
