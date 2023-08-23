package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class Events extends ArrayList<Event> {

    public List<Event> getBankHolidaysByRegion(Region region) {
        List<Event> bankHolidaysByRegion = new ArrayList<>();

        for (Event e : this) {
            if (e.region == region) {
                bankHolidaysByRegion.add(e);
            }
        }

        Collections.sort(bankHolidaysByRegion, new EventComparator());

        return bankHolidaysByRegion;
    }
    public List<Event> getAllBankHolidays() {
        List<Event> allBankHolidays = new ArrayList<>();

        for (Event event : this) {
            allBankHolidays.add(event);
        }

        // Sort the list by date
        Collections.sort(allBankHolidays, new EventComparator());

        return allBankHolidays;
    }
    public Event getNextBankHolidayByRegion(Region region) {
        List<Event> bankHolidaysByRegion = getBankHolidaysByRegion(region);

        GregorianCalendar today = new GregorianCalendar();

        for (Event e : bankHolidaysByRegion) {
            if (e.date.after(today)) {
                return e;
            }
        }

        return null;
    }

    public Event getPreviousBankHolidayByRegion(Region region) {
        List<Event> bankHolidaysByRegion = getBankHolidaysByRegion(region);

        GregorianCalendar today = new GregorianCalendar();

        for (int i = bankHolidaysByRegion.size() - 1; i >= 0; i--) {
            Event e = bankHolidaysByRegion.get(i);
            if (e.date.before(today)) {
                return e;
            }
        }

        return null;
    }
    private static class EventComparator implements Comparator<Event> {
        @Override
        public int compare(Event event1, Event event2) {
            return event1.date.compareTo(event2.date);
        }
    }
}
