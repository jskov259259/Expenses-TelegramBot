package com.github.jskov.command;

import com.github.jskov.command.enums.Period;
import com.github.jskov.command.exceptions.IncorrectDateExpense;
import com.github.jskov.dao.jdbc.dto.CategorySumDtoDao;
import com.github.jskov.model.dto.CategorySumDto;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.*;
import java.util.List;


@Service
public class GetCommand implements Command {

    public static final String GET_MESSAGE = "Information for the required period:\n----------\n";
    public static final String GET_MESSAGE_ERROR = "Incorrect date format";

    private CategorySumDtoDao categorySumDtoDao;

    public GetCommand(CategorySumDtoDao categorySumDtoDao) {
        this.categorySumDtoDao = categorySumDtoDao;
    }

    @Override
    public String execute(Update update) {

        String incomingText = update.getMessage().getText().trim().split(" ")[1].toLowerCase();
        long unixTime = update.getMessage().getDate();

        LocalDate[] dates;
        try {
            dates = defineDatesByIncomingMessage(incomingText, unixTime);
        } catch (IncorrectDateExpense ex) {
            ex.printStackTrace();
            // TODO make logger for IncorrectDateExpense
            return GET_MESSAGE_ERROR;
        }

        List<CategorySumDto> categorySumDtoList = findCategoriesWithSumOfExpenses(dates[0], dates[1]);
        StringBuilder responseMessage = new StringBuilder(GET_MESSAGE);
        for (CategorySumDto categorySumDto : categorySumDtoList) {
            responseMessage.append(categorySumDto.getCategoryName() + " " + categorySumDto.getSumOfExpense() + "\n");
        }
        // TODO make responseMessage from categorySumDtoList with stream API

        return responseMessage.toString();
    }

    private LocalDate[] defineDatesByIncomingMessage(String incomingText, long unixTime) throws IncorrectDateExpense {
        LocalDate dateOfMessage = LocalDateTime.ofEpochSecond(unixTime, 0, ZoneOffset.UTC).toLocalDate();

        LocalDate[] localDates = new LocalDate[2];
        localDates[1] = dateOfMessage;

        Period period = Period.valueOf(incomingText.toUpperCase());
        switch (period) {
            case DAY : localDates[0] = dateOfMessage; break;
            case WEEK: localDates[0] = dateOfMessage.minusWeeks(1); break;
            case MONTH: localDates[0] = dateOfMessage.minusMonths(1); break;
            case YEAR: localDates[0] = dateOfMessage.minusYears(1); break;
            case ALL : localDates[0] = LocalDate.EPOCH; break;
        }

        return localDates;
    }

    private List<CategorySumDto> findCategoriesWithSumOfExpenses(LocalDate dateFrom, LocalDate dateTo) {
        return categorySumDtoDao.findCategoriesWithSumOfExpenses(dateFrom, dateTo);
    }
}