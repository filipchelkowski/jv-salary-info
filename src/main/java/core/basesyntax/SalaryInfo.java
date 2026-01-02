package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryPerPerson = new int[names.length];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dF = LocalDate.parse(dateFrom, formatter);
        LocalDate dT = LocalDate.parse(dateTo, formatter);

        for (String day : data) {
            String[] result = day.split(" ");
            LocalDate date = LocalDate.parse(result[0], formatter);
            if (date.isAfter(dF) && date.isBefore(dT) || date.isEqual(dF) || date.isEqual(dT)) {
                for (int i = 0; i < names.length; i++) {
                    if (result[1].equals(names[i])) {
                        salaryPerPerson[i] = Integer.parseInt(result[2]) * Integer.parseInt(result[3]);
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo + "\n");

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i])
                    .append(" - ")
                    .append(salaryPerPerson[i])
                    .append("\n");
        }

        return stringBuilder.toString();
    }

}
