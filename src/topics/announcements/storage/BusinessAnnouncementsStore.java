package topics.announcements.storage;

import topics.Topic;
import topics.announcements.common.Announcement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BusinessAnnouncementsStore extends AnnouncementStore {

    private static final String[] announcements = {
            "Business news today!",
            "The market is hot!",
            "Stocks down another day",
            "The S&P reached record levels",
    };

    public BusinessAnnouncementsStore() {
        super(Topic.BUSINESS);
    }

    @Override
    public List<Announcement> getAnnouncementsStore() {
        return Arrays.stream(announcements)
                .map(BusinessAnnouncement::new)
                .collect(Collectors.toList());
    }

    class BusinessAnnouncement extends Announcement<String> {

        public BusinessAnnouncement(String announcement) {
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

