package rereadRefactor.chapt01;
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;

    private Prince prince;

    public Movie(String _title, Integer _princeCode) {
        this._title = _title;
        setPrinceCode(_princeCode);
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public Integer getPrinceCode() {
        return prince.getPrince();
    }

    public void setPrinceCode(Integer _princeCode) {
        switch (_princeCode) {
            case REGULAR:
                prince = new RegularPrince();
                break;
            case CHILDRENS:
                prince = new ChildPrince();
                break;
            case NEW_RELEASE:
                prince = new NewReleasePrince();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Prince Code");
        }
    }

    public double getCharge(int _daysRented) {
        return prince.getCharge(_daysRented);
    }

    public int getFrequentRenterPoints(int _daysRented) {
        return prince.getFrequentRenterPoints(_daysRented);
    }
}
