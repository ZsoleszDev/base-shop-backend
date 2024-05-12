package hu.thesis.baseshop.products.service;

import hu.thesis.baseshop.products.dao.OpinionsDao;
import hu.thesis.baseshop.products.entity.Opinions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OpinionsService {

    @Autowired
    private OpinionsDao opinionsDao;

    public Opinions addNewOpinion(Opinions opinions) {
        return opinionsDao.save(opinions);
    }

    public List<Opinions> getAllOpinion(){
        return (List<Opinions>) opinionsDao.findAll();
    }

    public List<Opinions> getOpinionById(String user_email) {
        List<Opinions> opinions = new ArrayList<>();
        opinionsDao.findAll().forEach(
                x -> {
                    if (Objects.equals(x.getUserUserName(), user_email)) {
                        opinions.add(x);
                    }
                }
        );
        return opinions;
    }

    public List<Opinions> getOpinionByProductId(Integer productId) {
        List<Opinions> opinions = new ArrayList<>();
        opinionsDao.findAll().forEach(
                x -> {
                    if (Objects.equals(x.getProductId(), productId)) {
                        opinions.add(x);
                    }
                }
        );
        return opinions;
    }

    public void deleteOpinion(Integer opinionId) {
        opinionsDao.deleteById(opinionId);
    }

}
