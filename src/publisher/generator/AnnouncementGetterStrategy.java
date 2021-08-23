package publisher.generator;

import topics.announcements.common.Announcement;

import java.util.List;

public interface AnnouncementGetterStrategy {
    Announcement getAnnouncement(List<Announcement> announcements);
}
