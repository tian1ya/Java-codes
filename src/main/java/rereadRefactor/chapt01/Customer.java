package rereadRefactor.chapt01;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    /*
        用于生成详单的函数
     */

    public String statement() {

        String result = getInitRentalResultMsg();
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + "frequent renter points";
        return result;
    }

    private String getInitRentalResultMsg() {
        Enumeration rentals = _rentals.elements();
        String result = "Rental Records for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            double thisAmount = each._movie.getCharge(each.getDaysRented());
            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
        }
        return result;
    }

    public int getTotalFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            frequentRenterPoints += each._movie.getFrequentRenterPoints(each.getDaysRented());
        }
        return frequentRenterPoints;
    }

    public double getTotalCharge() {
        double totalAmount = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {

            Rental each = (Rental) rentals.nextElement();
            double thisAmount = each._movie.getCharge(each.getDaysRented());
            totalAmount += thisAmount;
        }
        return totalAmount;
    }
}
