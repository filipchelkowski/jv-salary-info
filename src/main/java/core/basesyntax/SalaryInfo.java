package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryPerPerson = new int[names.length];
        final int in = 1;
        final int ih = 2;
        final int is = 3;

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate df = LocalDate.parse(dateFrom, formatter);
        LocalDate dt = LocalDate.parse(dateTo, formatter);

        for (String day : data) {
            String line = day.trim();
            String[] res = line.split("\s+");
            LocalDate date = LocalDate.parse(res[0], formatter);
            if ((date.isEqual(df) || date.isAfter(df)) && (date.isEqual(dt) || date.isBefore(dt))) {
                for (int i = 0; i < names.length; i++) {
                    if (res[in].equals(names[i])) {
                        salaryPerPerson[i] += Integer.parseInt(res[ih]) * Integer.parseInt(res[is]);
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
                .append(System.lineSeparator());

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
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

}
