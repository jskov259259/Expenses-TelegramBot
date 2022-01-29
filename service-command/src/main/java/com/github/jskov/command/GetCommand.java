package com.github.jskov.command;

import com.github.jskov.command.enums.Period;
import com.github.jskov.dao.jdbc.dto.CategorySumDtoDao;
import com.github.jskov.model.dto.CategorySumDto;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;


@Service
public class GetCommand implements Command {

    public static final String GET_MESSAGE = "Information for the required period:\n";
    public static final String GET_MESSAGE_ERROR = "Incorrect date format. Use /help to get detailed information";
    public static final String GET_MESSAGE_EMPTY = "Message is empty. Use following format:\n/get dd.mm.yyyy\n/get dd.mm.yyyy-dd.mm.yyyy\n/get Period designation";

    private CategorySumDtoDao categorySumDtoDao;

    public GetCommand(CategorySumDtoDao categorySumDtoDao) {
        this.categorySumDtoDao = categorySumDtoDao;
    }

    @Override
    public String execute(Update update) {

        if (update.getMessage().getText().trim().split(" ").length == 1) {
            return GET_MESSAGE_EMPTY;
        }

        String incomingText = update.getMessage().getText().trim().split(" ")[1].toLowerCase();
        long unixTime = update.getMessage().getDate();

        LocalDate[] dates;
        try {
            dates = defineDatesByIncomingMessage(incomingText, unixTime);
        } catch (Exception ex) {
            ex.printStackTrace();
            // TODO make logger
            return GET_MESSAGE_ERROR;
        }

        List<CategorySumDto> categorySumDtoList = findCategoriesWithSumOfExpenses(dates[0], dates[1]);
        StringBuilder responseMessage = new StringBuilder(GET_MESSAGE);
        BigDecimal totalSum = new BigDecimal("0.0");
        for (CategorySumDto categorySumDto : categorySumDtoList) {
            responseMessage.append(categorySumDto.getCategoryName() + ": " + categorySumDto.getSumOfExpense() + "\n");
            totalSum = totalSum.add(categorySumDto.getSumOfExpense());
        }
        responseMessage.append("----------\nTotal sum: " + totalSum);
        // TODO make responseMessage from categorySumDtoList with stream API

        return responseMessage.toString();
    }

    private LocalDate[] defineDatesByIncomingMessage(String incomingText, long unixTime) throws Exception {
        LocalDate[] localDates = new LocalDate[2];

        if (incomingText.equalsIgnoreCase("day") ||
                incomingText.equalsIgnoreCase("week") ||
                incomingText.equalsIgnoreCase("month") ||
                incomingText.equalsIgnoreCase("year") ||
                incomingText.equalsIgnoreCase("all")) {

            LocalDate dateOfMessage = LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.UTC).toLocalDate();
            localDates[1] = dateOfMessage;

            Period period = Period.valueOf(incomingText.toUpperCase());
            switch (period) {
                case DAY:
                    localDates[0] = dateOfMessage;
                    break;
                case WEEK:
                    localDates[0] = dateOfMessage.minusWeeks(1);
                    break;
                case MONTH:
                    localDates[0] = dateOfMessage.minusMonths(1);
                    break;
                case YEAR:
                    localDates[0] = dateOfMessage.minusYears(1);
                    break;
                case ALL:
                    localDates[0] = LocalDate.EPOCH;
                    break;
            }
            return localDates;
        }

        String[] dates = incomingText.split("-");
        if (dates.length == 1) {
            String date = reformatDate(dates[0]);
            localDates[0] = LocalDate.parse(date);
            localDates[1] = LocalDate.parse(date);
        }
        if (dates.length == 2) {
            String date1 = reformatDate(dates[0]);
            String date2 = reformatDate(dates[1]);
            localDates[0] = LocalDate.parse(date1);
            localDates[1] = LocalDate.parse(date2);
        }
            return localDates;

    }

    private List<CategorySumDto> findCategoriesWithSumOfExpenses(LocalDate dateFrom, LocalDate dateTo) {
        return categorySumDtoDao.findCategoriesWithSumOfExpenses(dateFrom, dateTo);
    }

    private String reformatDate(String date) {
        String[] res = date.split("\\.");
        String end = res[2] + "-" + res[1] + "-" + res[0];
        return end;
    }
}