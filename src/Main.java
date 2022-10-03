import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        Converter converter = new Converter();

        while (true) {
            printMenu(stepTracker.stepsCountGoal);
            int command = scanner.nextInt();

            switch (command) {
                case 0:
                    return;
                case 1:
                    setDayStepsCount(scanner, stepTracker);
                    break;
                case 2:
                    printStatistic(scanner, stepTracker, converter);
                    break;
                case 3:
                    setNewStepsCountGoal(scanner, stepTracker);
                    break;
                default:
                    System.out.println("Такой команды нет, попробуйте снова");
                    break;
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
        System.out.print("Введите месяц (от 0 до 11): ");
        int month = scanner.nextInt();

        while ((month < 0) || (month > 11)) {
            System.out.println("Введено неверное число");
            System.out.print("Введите месяц (от 0 до 11): ");
            month = scanner.nextInt();
        }

        System.out.print("Введите день (от 0 до 29): ");
        int day = scanner.nextInt();

        while ((day < 0) || (day > 29)) {
            System.out.println("Введено неверное число");
            System.out.print("Введите день (от 0 до 29): ");
            day = scanner.nextInt();
        }

        System.out.print("Введите количество шагов: ");
        int stepCount = scanner.nextInt();

        while (stepCount < 0) {
            System.out.println("Введено неверное число, количество шагов не может быть отрицательным");
            System.out.print("Введите количество шагов: ");
            stepCount = scanner.nextInt();
        }

        stepTracker.saveDayStepsCount(month, day, stepCount);
    }

    public static void printStatistic(Scanner scanner, StepTracker stepTracker, Converter converter) {
        System.out.print("Введите месяц (от 0 до 11): ");
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
