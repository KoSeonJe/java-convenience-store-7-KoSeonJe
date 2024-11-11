package store.promotion.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Promotion {

    private String name;

    private int buy;

    private int get;

    private LocalDate startDate;

    private LocalDate endDate;

    private Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public static Promotion create(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
        return new Promotion(name, buy, get, startDate, endDate);
    }

    public boolean isActive(LocalDateTime now) {
        LocalDate nowDate = LocalDate.from(now);
        return !startDate.isBefore(nowDate) && !endDate.isAfter(nowDate);
    }
}
