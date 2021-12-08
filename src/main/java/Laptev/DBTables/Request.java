package Laptev.DBTables;

public class Request {
    private int places;
    private String room_class;
    private int duration_hours;
    private int room_number;
    private String requestStatus;
//    private long PersonId;
    private Bill bill;

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getRoom_class() {
        return room_class;
    }

    public void setClass(String room_class) {
        this.room_class = room_class;
    }

    public int getDuration_hours() {
        return duration_hours;
    }

    public void setDuration_hours(int duration_hours) {
        this.duration_hours = duration_hours;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

//    public long getPersonId() {
//        return PersonId;
//    }
//
//    public void setPersonId(long personId) {
//        PersonId = personId;
//    }

    @Override
    public String toString() {
        return "Request :\n[places = " + places + ", duration_hours = " + duration_hours + ", room_class = " + room_class +
                ", room_number = " + room_number + ", requestStatus = " + requestStatus + "]\n" + bill;
    }
}
