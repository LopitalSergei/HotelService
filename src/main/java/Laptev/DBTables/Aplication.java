package Laptev.DBTables;

public class Aplication {
    private long aplicationId;
    private String clientName;
    private int places;
    private String roomClass;
    private int durationHours;
    private long personId;

    public Aplication(long aplicationId, String clientName, int places, String roomClass, int durationHours, long personId) {
        this.aplicationId = aplicationId;
        this.clientName = clientName;
        this.places = places;
        this.roomClass = roomClass;
        this.durationHours = durationHours;
        this.personId = personId;
    }

    public long getAplicationId() {
        return aplicationId;
    }

    public void setAplicationId(long aplicationId) {
        this.aplicationId = aplicationId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
