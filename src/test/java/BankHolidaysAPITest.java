import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankHolidaysAPITest {

    @Test
    void testGetAllEvents() throws Exception {
        BankHolidaysAPI bankHolidaysAPI = new BankHolidaysAPI();
        String content = bankHolidaysAPI.getBankHolidaysData();
        Root allHols = bankHolidaysAPI.getAllHolidays();

        Events allEvents = allHols.getAllEvents(Region.values());

        assertNotNull(allEvents);

        List<Event> allBankHolidays = allEvents.getAllBankHolidays();
        assertEquals(52, allBankHolidays.size());

        List<Event> englandAndWalesHolidays = allEvents.getBankHolidaysByRegion(Region.ENGLAND_AND_WALES);
        assertEquals(8, englandAndWalesHolidays.size());
    }

    @Test
    void testGetNextBankHoliday() throws Exception {
        BankHolidaysAPI bankHolidaysAPI = new BankHolidaysAPI();
        String content = bankHolidaysAPI.getBankHolidaysData();
        Root allHols = bankHolidaysAPI.getAllHolidays();

        Events allEvents = allHols.getAllEvents(Region.values());

        assertNotNull(allEvents);

        Event nextBankHoliday = allEvents.getNextBankHolidayByRegion(Region.ENGLAND_AND_WALES);
        assertNotNull(nextBankHoliday);
        assertEquals(Region.ENGLAND_AND_WALES, nextBankHoliday.region);
    }

    @Test
    void testGetPreviousBankHoliday() throws Exception {
        BankHolidaysAPI bankHolidaysAPI = new BankHolidaysAPI();
        String content = bankHolidaysAPI.getBankHolidaysData();
        Root allHols = bankHolidaysAPI.getAllHolidays();

        Events allEvents = allHols.getAllEvents(Region.values());

        assertNotNull(allEvents);

        Event previousBankHoliday = allEvents.getPreviousBankHolidayByRegion(Region.SCOTLAND);
        assertNotNull(previousBankHoliday);
        assertEquals(Region.SCOTLAND, previousBankHoliday.region);
    }
}
