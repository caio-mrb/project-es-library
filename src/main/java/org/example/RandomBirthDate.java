package org.example;

import java.time.LocalDate;
import java.util.Random;

public class RandomBirthDate {

    public static LocalDate generate(int firstAge, int secondAge)
    {
        int differenceBetweenAges = Math.max(firstAge, secondAge) - Math.min(firstAge, secondAge);

        Random random = new Random();

        int randomAge = random.nextInt(differenceBetweenAges + 1) + Math.min(firstAge, secondAge);
        int randomYear = LocalDate.now().getYear() - randomAge;
        int randomMonth = random.nextInt(12) + 1;
        int maxDay = getMaxDaysInMonth(randomMonth, randomYear);
        int randomDay = random.nextInt(maxDay) + 1;

        return LocalDate.of(randomYear, randomMonth, randomDay);
    }

    // Helper method to get maximum days in a month
    private static int getMaxDaysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }
}

