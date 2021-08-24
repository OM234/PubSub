package subscriber;

import publisher.Publisher;
import topics.Topic;
import topics.announcements.common.Announcement;

import java.util.concurrent.atomic.AtomicBoolean;

public class SubThread extends Thread {

    private final AtomicBoolean running = new AtomicBoolean(true);
    private final Topic topic;
    Publisher publisher = Publisher.instantiate();
    private int receiveCount = 1;


    public SubThread(Topic topic) {
        this.topic = topic;
        publisher.subscribe(this);
    }

    public void receive(Announcement announcement) {
        System.out.println(String.format("%d) I am thread %d, I am subscribed to %s, this is my announcement: %s",
                receiveCount, this.getId(), this.topic, announcement.getAnnouncement()));
        receiveCount++;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public void stopThread() {
        running.set(false);
    }

    public boolean shouldStop() {
        return this.receiveCount > 10;
    }

    @Override
    public void run() {
        while (running.get()) {
        }
    }

}
