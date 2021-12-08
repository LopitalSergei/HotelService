package Laptev.DBTables;

public class Room {
    private int roomNumber;
    private int places;
    private String roomClass;
    private String status;

    public Room(int roomNumber, int places, String roomClass) {
        this.roomNumber = roomNumber;
        this.places = places;
        this.roomClass = roomClass;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
