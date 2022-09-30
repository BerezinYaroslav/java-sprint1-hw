import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        Converter converter = new Converter();

        while (true) {
            printMenu(stepTracker.stepsCountGoal);
            int command = scanner.nextInt();

            if (command == 0) {
                return;
            } else if (command == 1) {
                setDayStepsCount(scanner, stepTracker);
            } else if (command == 2) {
                printStatistic(scanner, stepTracker, converter);
            } else if (command == 3) {
                setNewStepsCountGoal(scanner, stepTracker);
            } else {
                System.out.println("Такой команды нет, попробуйте снова");
            }

            System.out.println();
        }
    }

    public static void printMenu(int stepsCountGoal) {
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день (текущая - " + stepsCountGoal + ")");
        System.out.println("0 - Выйти из приложения");
        System.out.print("Введите, что вы хотите сделать: ");
    }

    public static void setDayStepsCount(Scanner scanner, StepTracker stepTracker) {
        System.out.print("Введите месяц (начиная с 0): ");
        int month = scanner.nextInt();
        System.out.print("Введите день (начиная с 0): ");
        int day = scanner.nextInt();
        System.out.print("Введите количество шагов: ");
        int stepCount = scanner.nextInt();

        stepTracker.saveDayStepsCount(month, day, stepCount);
    }

    public static void printStatistic(Scanner scanner, StepTracker stepTracker, Converter converter) {
        System.out.print("Введите месяц (начиная с 0): ");
        int monthIndex = scanner.nextInt();
        int[] month = stepTracker.months[monthIndex];

        System.out.println("Количество пройденных шагов по дням:");

        for (int i = 0; i < month.length; i++) {
            System.out.print(i + " день: " + month[i] + ", ");
        }

        System.out.println(
                "Общее количество шагов за месяц: " + stepTracker.findMonthStepsCount(month)
        );
        System.out.println(
                "Максимальное пройденное количество шагов в месяце: " + stepTracker.findMaxStepsCount(month)
        );
        System.out.println(
                "Среднее количество шагов: " + stepTracker.findAverageStepsCount(month)
        );
        System.out.println(
                "Пройденная дистанция (в км): " + converter.findCoveredDistance(stepTracker.findMonthStepsCount(month))
        );
        System.out.println(
                "Количество сожжённых килокалорий: " +
                        converter.findBurnedKilocalories(stepTracker.findMonthStepsCount(month))
        );
        System.out.println(
                "Лучшая серия: " + stepTracker.findTheBestSeries(month)
        );
    }

    public static void setNewStepsCountGoal(Scanner scanner, StepTracker stepTracker) {
        System.out.print("Введите новую цель по количеству шагов в день: ");
        int newStepsCountGoal = scanner.nextInt();

        while (newStepsCountGoal <= 0) {
            System.out.println("Цель не должна быть меньше или равна нулю");
            System.out.print("Введите новую цель по количеству шагов в день: ");
            newStepsCountGoal = scanner.nextInt();
        }

        stepTracker.changeStepsCountGoal(newStepsCountGoal);
    }
}
