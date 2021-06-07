package rereadRefactor.chapt01;

public class ChildPrince implements Prince{
    @Override
    public int getPrince() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented-3) * 1.5;
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int _daysRented) {
        return 1;
    }
}
