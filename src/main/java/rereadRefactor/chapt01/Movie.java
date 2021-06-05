package rereadRefactor.chapt01;

import org.apache.kafka.common.protocol.types.Field;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;
    private Integer _princeCode;

    public Movie(String _title, Integer _princeCode) {
        this._title = _title;
        this._princeCode = _princeCode;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public Integer getPrinceCode() {
        return _princeCode;
    }

    public void setPrinceCode(Integer _princeCode) {
        this._princeCode = _princeCode;
    }
}
