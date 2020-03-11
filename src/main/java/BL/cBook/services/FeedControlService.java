package BL.cBook.services;

import BL.cBook.model.FeedControl;
import java.util.List;

public interface FeedControlService {

    void saveFeedControl(FeedControl fc);
    List<FeedControl> findAllFeedControls();
    void saveAllFeedControllers(List<FeedControl> list);
    void deleteFeed(int id);
    void deleteAllFeed();

}
