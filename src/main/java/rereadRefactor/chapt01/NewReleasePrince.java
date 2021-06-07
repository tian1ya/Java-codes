package rereadRefactor.chapt01;

public class NewReleasePrince implements Prince{
    @Override
    public int getPrince() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int _daysRented) {
        return _daysRented > 1 ? 2:1;
    }
}
