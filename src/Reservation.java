import java.util.UUID;

/**
 * 예약 - 객실, 고객 이름, 고객 전화번호, 생성일자, 예약번호
 */
public class Reservation {
    private Room reservedRoom;
    private String customerName;
    //전화 번호 제한(XXX-XXXX-XXXX) 정규 표현식
    private String customerPhone;
    //날짜는 ISO 8601 형식으로 조합된 UTC 날짜 및 시간 사용
    //예) 2016-10-27T17:13:40+00:00
    private String creationDate;
    private UUID reservationNumber;

    private String customerId;

    public Reservation(Room reservedRoom, String customerName, String customerPhone, String creationDate,String customerId) {
        this.reservedRoom = reservedRoom;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.creationDate = creationDate;
        this.reservationNumber = UUID.randomUUID();
        this.customerId = customerId;
    }

    public Room getreservedRoom() {//현재 예약 객체에 연결된 예약된 방 호출
        if (reservedRoom != null) {
            return reservedRoom;
        } else {
            return null;
        }
    }



    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getReservationDate() {
        return creationDate;
    }

    public UUID getReservationNumber() {
        return reservationNumber;
    }


    public String getReservationInfo(String currentCustomerId) {
        if (reservedRoom != null && customerId.equals(currentCustomerId)) {
            String reservationInfo = "고객 이름: " + getCustomerName() + "\n" +
                    "고객 전화번호: " + getCustomerPhone() + "\n" +
                    "예약 날짜: " + getReservationDate() + "\n"+
                    "예약 번호: "+ getReservationNumber()+"\n"+
                    "예약된 방:" + getreservedRoom().getRoomNumber()+" 호실 "+getreservedRoom().getSize();
            return reservationInfo;
        } else {
            return "예약 정보가 없거나 다른 고객의 예약입니다.";
        }
    }
    public String getAllReservationInfo() {
        if (reservedRoom != null) {
            String reservationInfo = "고객 이름: " + getCustomerName() + "\n" +
                    "고객 전화번호: " + getCustomerPhone() + "\n" +
                    "예약 날짜: " + getReservationDate() + "\n"+
                    "예약 번호: "+ getReservationNumber()+"\n"+
                    "예약된 방:" + getreservedRoom().getRoomNumber()+" 호실 "+getreservedRoom().getSize();
            return reservationInfo;
        } else {
            return "예약 정보가 없습니다.";
        }
    }
}