package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.entity.Ong;
import voluntarize.repository.OngRepository;

@Service
public class UserService {

    @Autowired
    private OngRepository _ongRepository;

    public Ong create(Ong ong){
        _ongRepository.save(ong);
        return ong;
    }

    public List<Ong> findAll(){
        return _ongRepository.findAll();
    }
}
