package disruptor.one2three;

/**
 * Created by duanzx on 2018/6/12.
 */
public class ValueEvent {

    private long value;

    public long getValue()
    {
        return value;
    }

    public void setValue(final long value)
    {
        this.value = value;
    }
}
