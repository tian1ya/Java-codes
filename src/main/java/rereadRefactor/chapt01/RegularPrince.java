package rereadRefactor.chapt01;

public class RegularPrince implements Prince{
    @Override
    public int getPrince() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int daysRented) {
        int result =2;
        if (daysRented > 2)
            result += (daysRented-2) * 1.5;
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int _daysRented) {
        return 1;
    }
}
