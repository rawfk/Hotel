/**
 * 객실 : 크기, 숙박비
 */
public class Room {
    //혹시 나중에 정보 뽑아 올 때 필요할 수 있으므로 포함 시킴.
    private String roomNumber;
    private String roomSize;
    private int roomRate;
    //예약 여부 체크용
    private boolean isReserved;

    public Room(String roomNumber, String size, int price) {
        this.roomNumber = roomNumber;
        this.roomSize = size;
        this.roomRate = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getSize() {
        return roomSize;
    }

    public int getPrice() {
        return roomRate;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public boolean isReserved() {
        return isReserved;
    }
}