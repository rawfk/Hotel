import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private List<Reservation> reservations;

    public ReservationManager() {
        this.reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {//예약 추가하고 방의 예약상태 수정
        reservations.add(reservation);
        reservation.getreservedRoom().setReserved(true);
    }
    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean cancelReservation(String roomNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationNumber() != null
                    && reservation.getreservedRoom() != null
                    && reservation.getreservedRoom().getRoomNumber().equals(roomNumber)) {
                // 방 번호가 일치확인하고 취소하기, 예약된 방 역시 취소
                reservations.remove(reservation);
                reservation.getreservedRoom().setReserved(false);
                return true;
            }
        }
        return false; //일치안할시
    }

}
