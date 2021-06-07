package rereadRefactor.chapt01;

public class Rental {
    Movie _movie;
    private int _daysRented;

    public Rental(Movie _movie, int _daysRented) {
        this._movie = _movie;
        this._daysRented = _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public void setMovie(Movie _movie) {
        this._movie = _movie;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public void setDaysRented(int _daysRented) {
        this._daysRented = _daysRented;
    }

    public double getCharge() {
        return _movie.getCharge(_daysRented);
    }
    public int getFrequentRenterPoints() {
        return getMovie().getPrinceCode() == Movie.NEW_RELEASE && getDaysRented() > 1 ? 2:1;
    }
}
