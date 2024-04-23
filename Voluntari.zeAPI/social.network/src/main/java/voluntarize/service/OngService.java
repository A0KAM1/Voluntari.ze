package voluntarize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voluntarize.entity.Ong;
import voluntarize.entity.User;
import voluntarize.repository.OngRepository;
import voluntarize.repository.UserRepository;
import voluntarize.request.OngRequest;

import java.util.List;
import java.util.Optional;

@Service
public class OngService {

    @Autowired
    private OngRepository _ongRepository;
    @Autowired
    private UserRepository _userRepository;

    public Ong create(OngRequest ong){
        User user = this.getUserAttributes(ong);
        Ong res = this.convertToEntity(ong, user);
        _userRepository.save(user);
        _ongRepository.save(res);
        return res;
    }

    public List<Ong> findByFilter(String keyword, List<Long> category){
        List<Ong> res = _ongRepository.findByFilter(keyword, category);
        return res;
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

    private User getUserAttributes(OngRequest request){
        User res = new User();
        res.setEmail(request.email);
        res.setPassword(request.password);
        res.setUsername(request.username);
        res.setDescription(request.description);
        res.setName(request.name);
        res.setPhoneNumber(request.phoneNumber);
        res.setProfilePicture(request.profilePicture);
        res.setCity(request.city);
        res.setState(request.state);
        res.setCountry(request.country);
        return res;
    }

    private Ong convertToEntity(OngRequest request, User user){
        Ong res = new Ong();
        res.setAddress(request.address);
        res.setCnpj(request.cnpj);
        res.setUser(user);
        res.setQrCode(request.qrCode);
        return res;
    }
}
