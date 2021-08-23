package publisher.generator;

import topics.announcements.common.Announcement;

import java.util.List;
import java.util.Random;

public class RandomAnnouncementGetter implements AnnouncementGetterStrategy {

    @Override
    public Announcement getAnnouncement(List<Announcement> announcements) {
        Random r = new Random();
        return announcements.get(r.nextInt(announcements.size()));
    }
}
