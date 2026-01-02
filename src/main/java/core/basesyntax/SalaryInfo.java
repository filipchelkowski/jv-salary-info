package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryPerPerson = new int[names.length];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate df = LocalDate.parse(dateFrom, formatter);
        LocalDate dt = LocalDate.parse(dateTo, formatter);

        for (String day : data) {
            String line = day.trim();
            String[] res = line.split("\s+");
            LocalDate date = LocalDate.parse(res[0], formatter);
            if ((date.isEqual(df) || date.isAfter(df)) && (date.isEqual(dt) || date.isBefore(dt))) {
                for (int i = 0; i < names.length; i++) {
                    if (res[1].equals(names[i])) {
                        salaryPerPerson[i] += Integer.parseInt(res[2]) * Integer.parseInt(res[3]);
                        break;
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                stringBuilder.append(names[i])
                        .append(" - ")
                        .append(salaryPerPerson[i]);
                break;
            }
            stringBuilder.append(names[i])
                    .append(" - ")
                    .append(salaryPerPerson[i])
                    .append("\n");
        }

        return stringBuilder.toString();
    }

}
