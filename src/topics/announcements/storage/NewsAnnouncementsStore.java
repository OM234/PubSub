package topics.announcements.storage;

import topics.Topic;
import topics.announcements.common.Announcement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NewsAnnouncementsStore extends AnnouncementStore {

    private final String[] announcements = {
            "News today!",
            "Another bloody day in Iraq",
            "The man who escaped the bear twice",
            "How to get drunk by 4pm"
    };

    public NewsAnnouncementsStore() {
        super(Topic.NEWS);
    }

    @Override
    public List<Announcement> getAnnouncementsStore() {
        return Arrays.stream(announcements)
                .map(NewsAnnouncement::new)
                .collect(Collectors.toList());
    }

    class NewsAnnouncement extends Announcement<String> {

        public NewsAnnouncement(String announcement) {
            super(announcement);
        }

        @Override
        public String getAnnouncement() {
            return super.announcement;
        }

        @Override
        public void setAnnouncement(String announcement) {
            super.announcement = announcement;
        }
    }
}
