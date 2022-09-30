public class StepTracker {
    int stepsCountGoal = 10000;
    int[][] months = new int[12][30];

    public void changeStepsCountGoal(int newStepsCountGoal) {
        stepsCountGoal = newStepsCountGoal;
    }

    public void saveDayStepsCount(int monthNumber, int dayNumber, int stepsCount) {
        months[monthNumber][dayNumber] = stepsCount;
    }

    public int findMonthStepsCount(int[] month) {
        int monthStepsCount = 0;

        for (int dayStepsCount: month) {
            monthStepsCount += dayStepsCount;
        }

        return monthStepsCount;
    }

    public int findMaxStepsCount(int[] month) {
        int maxStepsCount = -9999;

        for (int dayStepsCount : month) {
            if (dayStepsCount > maxStepsCount) {
                maxStepsCount = dayStepsCount;
            }
        }

        return maxStepsCount;
    }

    public int findAverageStepsCount(int[] month) {
        int monthStepsCount = findMonthStepsCount(month);
        return monthStepsCount / 30;
    }

    public int findTheBestSeries(int[] month) {
        int maxSeries = 0;
        int series = 0;

        for (int dayStepsCount : month) {
            if (dayStepsCount >= stepsCountGoal) {
                series++;

                if (series > maxSeries) {
                    maxSeries = series;
                }
            } else {
                series = 0;
            }
        }

        return maxSeries;
    }
}
