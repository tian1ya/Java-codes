package TDD.workshop;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static java.time.LocalDate.of;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PeriodicalTaskTest {

    private PeriodicalTask periodicalTask;

    @Before
    public final void before() {
        periodicalTask = new PeriodicalTask();
    }

    @Test
    public void should_start_from_start_date_get_next_run_at_after_10_minute() {


        LocalDate nextRunAt = periodicalTask
                .hourlyPeriodTask(
                        of(2019, 11, 21),
                        of(2019, 11, 22),
                        1,
                        10)
                .nextRunAt();

        Date mockDate = mockNextRunData();

        assertThat(mockDate, is(nextRunAt));

    }



    private Date mockNextRunData() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 11, 21, 00, 10, 00);
        return cal.getTime();
    }
}
