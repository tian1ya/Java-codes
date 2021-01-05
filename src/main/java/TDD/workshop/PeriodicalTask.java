package TDD.workshop;

import java.time.LocalDate;
import java.util.Date;

class PeriodicalTask {

    private PeriodType periodType;
    private int period;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer dayOfWeek;
    private Integer dayOfmonth;
    private Integer hour;
    private Integer minute;

    public LocalDate nextRunAt() {
        return this.startDate.plusMonths(minute);
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayOfmonth() {
        return dayOfmonth;
    }

    public void setDayOfmonth(Integer dayOfmonth) {
        this.dayOfmonth = dayOfmonth;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public PeriodicalTask hourlyPeriodTask(LocalDate start, LocalDate end, int period, int minute) {
        setStartDate(start);
        setEndDate(end);
        setPeriod(period);
        setMinute(minute);
        return this;
    }
}
