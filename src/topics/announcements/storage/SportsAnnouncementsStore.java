package topics.announcements.storage;

import topics.Topic;
import topics.announcements.common.Announcement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SportsAnnouncementsStore extends AnnouncementStore {

    private final String[] announcements = {
            "Sports news today!",
            "The Canadiens almost had it!",
            "Another boring day of baseball",
            "The fastest man on the planet!"
    };

    public SportsAnnouncementsStore() {
        super(Topic.SPORTS);
    }

    @Override
    public List<Announcement> getAnnouncementsStore() {
        return Arrays.stream(announcements)
                .map(SportsAnnouncement::new)
                .collect(Collectors.toList());
    }

    class SportsAnnouncement extends Announcement<String> {

        public SportsAnnouncement(String announcement) {
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
