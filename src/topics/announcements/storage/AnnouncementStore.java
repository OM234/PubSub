package topics.announcements.storage;

import topics.Topic;
import topics.announcements.common.Announcement;

import java.util.List;

public abstract class AnnouncementStore {

    final protected Topic topic;

    protected AnnouncementStore(Topic topic) {
        this.topic = topic;
    }

    abstract public List<Announcement> getAnnouncementsStore();

    public Topic getTopic() {
        return topic;
    }
}
