# Expenses TelegramBot
Telegram bot for calculating cash payments. The main idea of this bot is to keep track of expenses and display detailed statistics for specific categories in order to optimize costs in the future.

## How it works
The user has several commands at his disposal.

### /start
To start working with the bot, you need to enter this command.
### /help
Command helper, shows a description of the available commands.
### /code
This command shows a list of purchase codes.
### /add [code] [price] 
This command adds consumption data, where code - is an expense category item; price - money spent.
### /get [date]
This command displays statistics for the selected date.
### /get [date1-date2]
This command displays statistics for the selected period.

## Technological stack
- SpringBoot as a skeleton framework.
- H2 database (embedded mode) as a database for saving expenses and other information. 
