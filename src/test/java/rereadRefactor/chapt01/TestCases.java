package rereadRefactor.chapt01;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class TestCases {
    @Test
    public void test_statement_logic() {
        Movie movie1 = new Movie("西游记", 2);
        Rental rental = new Rental(movie1, 2);
        Customer customer = new Customer("小明");
        customer.addRental(rental);

        String statement = customer.statement();
        System.out.println(statement);
        assertTrue(statement.contains("Rental Records for 小明"));
        assertTrue(statement.contains("西游记\t1.5"));
        assertTrue(statement.contains("西游记\t1.5"));
        assertTrue(statement.contains("Amount owed is 1.5"));
        assertTrue(statement.contains("You earned 1frequent renter points"));
    }
}
