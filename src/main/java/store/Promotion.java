package store;

import static store.StoreConstant.PROMOTION_BUY_INDEX;
import static store.StoreConstant.PROMOTION_END_DATE_INDEX;
import static store.StoreConstant.PROMOTION_GET_INDEX;
import static store.StoreConstant.PROMOTION_NAME_INDEX;
import static store.StoreConstant.PROMOTION_START_DATE_INDEX;

import java.time.LocalDate;


public class Promotion {

    private String name;

    private int buy;

    private int get;

    private LocalDate startDate;

    private LocalDate endDate;

    public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public static Promotion create(String[] rawFields) {
        return new Promotion(
                rawFields[PROMOTION_NAME_INDEX],
                Integer.parseInt(rawFields[PROMOTION_BUY_INDEX]),
                Integer.parseInt(rawFields[PROMOTION_GET_INDEX]),
                LocalDate.parse(rawFields[PROMOTION_START_DATE_INDEX]),
                LocalDate.parse(rawFields[PROMOTION_END_DATE_INDEX])
        );
    }
}
