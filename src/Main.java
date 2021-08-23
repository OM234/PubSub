import publisher.Publisher;
import publisher.generator.RandomAnnouncementGetter;
import subscriber.SubThread;
import topics.Topic;

public class Main {
    public static void main(String[] args) {
        SubThread thread = new SubThread(Topic.NEWS);
        SubThread thread2 = new SubThread(Topic.BUSINESS);
        SubThread thread3 = new SubThread(Topic.SPORTS);
        Publisher publisher = Publisher.instantiate(new RandomAnnouncementGetter());

        thread.start();
        thread2.start();
        thread3.start();
        publisher.run();
    }
}
