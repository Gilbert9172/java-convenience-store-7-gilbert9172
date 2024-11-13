package store.repository.sequence;

import java.util.concurrent.atomic.AtomicLong;

public class TimeStampSequenceGenerator implements SequenceGenerator {

    private static final AtomicLong LAST_TIME_MS = new AtomicLong();

    @Override
    public Long generate() {
        long now = System.currentTimeMillis();
        while (true) {
            long lastTime = LAST_TIME_MS.get();
            if (lastTime >= now) {
                now = lastTime + 1;
            }
            if (LAST_TIME_MS.compareAndSet(lastTime, now)) {
                return now;
            }
        }
    }
}
