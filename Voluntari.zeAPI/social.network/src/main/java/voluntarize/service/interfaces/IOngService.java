package voluntarize.service.interfaces;

import voluntarize.dto.OngDto;
import voluntarize.dto.VolunteerDto;
import voluntarize.request.OngRequest;

import java.util.List;

public interface IOngService {
    OngDto createOng(OngRequest request);
    List<OngDto> searchOngs(String keyword, List<Long> category);
    OngDto findById(Long id);
    boolean deleteOngById(Long id);
    boolean updateOngById(Long id, OngRequest request);
    void addCategories(Long id, Long category);
    List<VolunteerDto> followers(Long id);
}
