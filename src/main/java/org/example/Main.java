package org.example;

import java.util.List;
import java.util.Scanner;

import static org.example.Region.NORTHERN_IRELAND;

public class Main {
            private static Root allHols;
            public static void main(String[] args) {
                BankHolidaysAPI bankHolidaysAPI = new BankHolidaysAPI();

                try {
                    String content = bankHolidaysAPI.getBankHolidaysData();
                    allHols = bankHolidaysAPI.getAllHolidays();

                    Events allEvents = allHols.getAllEvents(Region.values()); // Fetch all events

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter a region (ENGLAND_AND_WALES, SCOTLAND, NORTHERN_IRELAND):");
                    String regionInput = scanner.nextLine();

                    Region selectedRegion = Region.valueOf(regionInput);

                    //Display all bank holidays sorted by region and date
                    displayBankHolidays(allEvents, selectedRegion); // Display BankHoliday by region - sorted
                    displayNextBankHoliday(allEvents, selectedRegion); // Display Next BankHoliday
                    displayPreviousBankHoliday(allEvents, selectedRegion);// Display Previous BankHoliday



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            private static void displayBankHolidays(Events events, Region region) {
                List<Event> bankHolidays;

                if (region == null) {
                    System.out.println("All Bank Holidays:");
                    bankHolidays = events.getAllBankHolidays();
                } else {
                    System.out.println("Bank Holidays in " + region + ":");
                    bankHolidays = events.getBankHolidaysByRegion(region);
                }

                for (Event event : bankHolidays) {
                    System.out.println(event.toString());
                }
                System.out.println();
            }
    private static void displayNextBankHoliday(Events events, Region region) {
        Event nextBankHoliday = events.getNextBankHolidayByRegion(region);

        if (nextBankHoliday != null) {
            System.out.println("Next Bank Holiday in " + region + ":");
            System.out.println(nextBankHoliday.toString());
        } else {
            System.out.println("No upcoming Bank Holidays in " + region);
        }

        System.out.println();
    }

    private static void displayPreviousBankHoliday(Events events, Region region) {
        Event previousBankHoliday = events.getPreviousBankHolidayByRegion(region);

        if (previousBankHoliday != null) {
            System.out.println("Previous Bank Holiday in " + region + ":");
            System.out.println(previousBankHoliday.toString());
        } else {
            System.out.println("No previous Bank Holidays in " + region);
        }

        System.out.println();
    }
        }

