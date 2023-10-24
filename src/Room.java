/**
 * 객실 : 크기, 숙박비
 */
public class Room {
    //혹시 나중에 정보 뽑아 올 때 필요할 수 있으므로 포함 시킴.
    private int roomNumber;
    private double size;
    private int price;
    //예약 여부 체크용
    private boolean isReserved;

    public Room(int roomNumber, double size, int price) {
        this.roomNumber = roomNumber;
        this.size = size;
        this.price = price;
    }

}
