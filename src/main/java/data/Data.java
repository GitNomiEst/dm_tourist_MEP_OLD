package data;

public class Data {
    private String touristName;
    private String touristNationality;
    private String duration;
    private String tripDestination;
    private String tripDistance;
    
    public Data(String touristName, String touristNationality, String duration, String tripDestination, String tripDistance) {
        this.touristName = touristName;
        this.touristNationality = touristNationality;
        this.duration = duration;
        this.tripDestination = tripDestination;
        this.tripDistance = tripDistance;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public String getTouristNationality() {
        return touristNationality;
    }

    public void setTouristNationality(String touristNationality) {
        this.touristNationality = touristNationality;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public String getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(String tripDistance) {
        this.tripDistance = tripDistance;
    }
    
}
