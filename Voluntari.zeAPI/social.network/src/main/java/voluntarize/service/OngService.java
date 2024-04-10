package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.entity.Ong;
import voluntarize.repository.OngRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OngService {

    @Autowired
    private OngRepository _ongRepository;

    public Ong create(Ong ong){
        _ongRepository.save(ong);
        return ong;
    }

    public List<Ong> findAll(){
        return _ongRepository.findAll();
    }

    public Ong findById(Long id){
        Optional<Ong> res = _ongRepository.findById(id);
        if(res.isPresent()) return res.get();
        return null;
    }

    public boolean delete(Long id){
        Ong res = this.findById(id);
        if(res != null) {
            _ongRepository.delete(res);
            return true;
        }
        return false;
    }

    public Ong update(Long id, Ong ong){
        Ong res = this.findById(id);
        if(res != null) return _ongRepository.save(ong);
        return null;
    }
}
