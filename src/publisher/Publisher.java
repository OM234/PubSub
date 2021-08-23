package publisher;

import publisher.generator.AnnouncementGetterStrategy;
import publisher.generator.RandomAnnouncementGetter;
import subscriber.SubThread;
import topics.Topic;
import topics.announcements.common.Announcement;
import topics.announcements.storage.AnnouncementStore;
import topics.announcements.storage.BusinessAnnouncementsStore;
import topics.announcements.storage.NewsAnnouncementsStore;
import topics.announcements.storage.SportsAnnouncementsStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Publisher implements Runnable {

    private static Publisher publisherSingleton;
    private AnnouncementGetterStrategy announcementGetterStrategy;
    private List<SubThread> subscribers = new ArrayList<>();
    private List<AnnouncementStore> announcementStores = Arrays.asList(
            new BusinessAnnouncementsStore(),
            new NewsAnnouncementsStore(),
            new SportsAnnouncementsStore()
    );
    private Map<Topic, List<Announcement>> announcements;


    private Publisher(AnnouncementGetterStrategy announcementGetterStrategy) {
        this.announcementGetterStrategy = announcementGetterStrategy;
        populateAnnouncements();
    }

    public static Publisher instantiate(AnnouncementGetterStrategy aAnnouncementGetterStrategy) {
        if (publisherSingleton == null) {
            publisherSingleton = new Publisher(aAnnouncementGetterStrategy);
        }
        return publisherSingleton;
    }

    public static Publisher instantiate() {
        if (publisherSingleton == null) {
            publisherSingleton = new Publisher(new RandomAnnouncementGetter());
        }
        return publisherSingleton;
    }


    private void populateAnnouncements() {
        announcements = announcementStores.stream()
                .map(announcementStore -> Map.of(announcementStore.getTopic(), announcementStore.getAnnouncementsStore()))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void publishToSubs() {
        subscribers.stream()
                .map(subscriber -> Map.of(subscriber, announcementGetterStrategy.getAnnouncement(announcements.get(subscriber.getTopic()))))
                .flatMap(map -> map.entrySet().stream())
                .forEach(entry -> entry.getKey().receive(entry.getValue()));
    }

    public void stopFinishedSubs() {
        subscribers.stream()
                .filter(SubThread::shouldStop)
                .forEach(SubThread::stopThread);
    }

    public void setAnnouncementGetterStrategy(AnnouncementGetterStrategy aAnnouncementGetterStrategy) {
        announcementGetterStrategy = aAnnouncementGetterStrategy;
    }

    public void subscribe(SubThread subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void run() {
        while (subscribers.stream().anyMatch(Thread::isAlive)) {
            publishToSubs();
            stopFinishedSubs();
            sleepThread();
            System.out.println();
        }
    }

    private void sleepThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
