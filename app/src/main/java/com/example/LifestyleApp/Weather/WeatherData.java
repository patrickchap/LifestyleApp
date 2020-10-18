package com.example.LifestyleApp.Weather;

public class WeatherData {

    private Temperature mTemperature = new Temperature();

    public class Temperature {
        private double mTemp;
        private double mMinTemp;
        private double mMaxTemp;

        public double getTemp() {
            return mTemp;
        }

        public void setTemp(double temp) {
            mTemp = temp;
        }

        public double getMinTemp() {
            return mMinTemp;
        }

        public void setMinTemp(double minTemp) {
            mMinTemp = minTemp;
        }

        public double getMaxTemp() {
            return mMaxTemp;
        }

        public void setMaxTemp(double maxTemp) {
            mMaxTemp = maxTemp;
        }

    }

    public Temperature getTemperature(){
        return mTemperature;
    }

    public void setTemperature(Temperature temperature){
        mTemperature = temperature;
    }

}