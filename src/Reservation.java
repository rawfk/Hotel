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

    public Reservation(Room reservedRoom, String customerName, String customerPhone, String creationDate) {
        this.reservedRoom = reservedRoom;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.creationDate = creationDate;
        this.reservationNumber = UUID.randomUUID();
    }
}