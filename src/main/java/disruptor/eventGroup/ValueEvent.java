package disruptor.eventGroup;

/**
 * Created by duanzx on 2018/6/12.
 */
public class ValueEvent {

    private long value;
    private String name;
    private Integer price;

    public long getValue()
    {
        return value;
    }

    public void setValue(final long value)
    {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
