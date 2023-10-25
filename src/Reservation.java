import java.util.UUID;

/**
 * 예약 - 객실, 고객 이름, 고객 전화번호, 예약 날짜
 */
public class Reservation {
    private Room reservedRoom;
    private String customerName;
    //전화 번호 제한(XXX-XXXX-XXXX) 정규 표현식
    private String customerPhone;
    //날짜는 ISO 8601 형식으로 조합된 UTC 날짜 및 시간 사용
    //예) 2016-10-27T17:13:40+00:00
    private String reservationDate;
    private UUID reservationNumber;
    public Room getReservedRoom() {
        return reservedRoom;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public UUID getReservationNumber() {
        return reservationNumber;
    }

    public Reservation(Room reservedRoom, Customer customer, String reservationDate) {
        this.reservedRoom = reservedRoom;
        this.customerName = customer.getCustomerName();
        this.customerPhone = customer.getCustomerPhone();
        this.reservationDate = reservationDate;
        this.reservationNumber = UUID.randomUUID();
        customer.addCustomerCash(reservedRoom.getPrice());

    }





    public void cancelReservation(Reservation reservation ,Room room, Customer cus) {

        cus.addCustomerCash(this.reservedRoom.getPrice());
        this.reservedRoom.setReserved(false);
        this.reservationNumber = null;
        this.customerName=null;
    }

}
