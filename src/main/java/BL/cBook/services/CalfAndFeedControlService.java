package BL.cBook.services;

import BL.cBook.dao.CalfRepository;
import BL.cBook.dao.FeedControlRepository;
import BL.cBook.databaseModel.DBCalf;
import BL.cBook.model.Calf;
import BL.cBook.model.FeedControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CalfAndFeedControlService implements CalfService, FeedControlService {

    @Autowired
    private CalfRepository calfRepository;
    @Autowired
    private FeedControlRepository feedControlRepository;

    public void save(DBCalf calf) {
        calfRepository.save(calf);
    }
    public List<DBCalf> findAll() {
        return calfRepository.findAll();
    }
    public void saveAll(List<DBCalf> list) { calfRepository.saveAll(list); }
    public void deleteAll() {
        calfRepository.deleteAll();
    }
    public void deleteAll(List<DBCalf> list) {
        calfRepository.deleteAll(list);
    }

    public void saveFeedControl(FeedControl fc) {
        feedControlRepository.save(fc);
    }
    public List<FeedControl> findAllFeedControls() {
        return feedControlRepository.findAll();
    }
    public void saveAllFeedControllers(List<FeedControl> list) {
        feedControlRepository.saveAll(list);
    }
    public void deleteFeed(int id) {
        feedControlRepository.deleteById(id);
    }
    public void deleteAllFeed() {
        feedControlRepository.deleteAll();
    }
    public void deleteAllFeed(List<FeedControl> list) { feedControlRepository.deleteAll(list); }
}
