package org.example;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class Event {
    public String title;
    public GregorianCalendar date;
    public Region region;
    public String notes;
    public boolean bunting;



    @Override
    public String toString() {
        return "Region: "+ region +" Bank Holiday: " + title + " - " + date.get(Calendar.DATE) + "\\" + (date.get(Calendar.MONTH)+1) + "\\" + date.get(Calendar.YEAR) + '\n'
                + (bunting ? "Bunting is required" : "Bunting is not required") + (notes.length() > 0 ? " - " + notes : "");
    }
}
