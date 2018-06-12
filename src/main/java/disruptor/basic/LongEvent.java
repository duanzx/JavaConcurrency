package disruptor.basic;

import java.io.Serializable;

public class LongEvent implements Serializable {
    private static final long serialVersionUID = -3032386760246096011L;
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
