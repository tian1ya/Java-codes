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
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Enumeration rentals = _rentals.elements();
        String result = "Rental Records for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            double thisAmount = amountFor(each);

            frequentRenterPoints++;
            if (each.getMovie().getPrinceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1)
                frequentRenterPoints++;

            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + "frequent renter points";
        return result;


    }

    private double amountFor(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPrinceCode()) {
            case Movie.REGULAR:
                thisAmount +=2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented()-2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented()-3) * 1.5;
                break;
        }
        return thisAmount;
    }
}
