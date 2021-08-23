package topics.announcements.common;

public abstract class Announcement<T> {
    protected T announcement;

    public Announcement(T announcement) {
        this.announcement = announcement;
    }

    abstract public T getAnnouncement();

    abstract public void setAnnouncement(T announcement);
}
