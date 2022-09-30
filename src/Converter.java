public class Converter {
    int stepDistance = 75;
    int stepCalories = 50;
    int smPerKm = 100000;
    int caloriesPerKilocalories = 1000;

    public int findCoveredDistance(int stepsCount) {
        return stepsCount * stepDistance / smPerKm;
    }

    public int findBurnedKilocalories(int stepsCount) {
        return stepsCount * stepCalories / caloriesPerKilocalories;
    }
}
