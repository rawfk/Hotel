import java.util.HashMap;
import java.util.Map;

public class RoomManager {
    private Map<String, Room> rooms;

    public RoomManager() {
        rooms = new HashMap<>();
    }


    public void addRoom(String roomNumber, String size, int price) {
        Room room = new Room(roomNumber, size, price);
        rooms.put(roomNumber, room);
    }
    public void deleteRoom(String roomNumber) {
        if (rooms.containsKey(roomNumber)) {
            if (rooms.get(roomNumber).isReserved()) {
                System.out.println(roomNumber + "호실은 이미 예약된 방이므로 삭제할 수 없습니다.");
            } else {
                rooms.remove(roomNumber);
                System.out.println(roomNumber + "호실이 성공적으로 삭제되었습니다.");
            }
        } else {
            System.out.println("해당 방이 존재하지 않습니다.");
        }
    }
    //수정사항 방이 예약된방일시 추가 방지



    public void showRoomList() {//isReserved 여부를 확인하고 예약 되어있지 않은 방만 보여주기
        System.out.println("[방 목록]");
        for (Room room : rooms.values()) {
            if (!room.isReserved()) {
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.println("방 번호: " + room.getRoomNumber() + "호실");
                System.out.println(room.getSize() + "룸");
                System.out.println("가격: " + room.getPrice() + "원");
            }
        }
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
    }
    public void getRoomInfo(String roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room != null&&room.isReserved()==false) {
            //이 방이 이미 예약됬는지,그리고 null조회하지 않게
            System.out.println("[방 정보]");
            System.out.println("방 번호: " + room.getRoomNumber() + "호실");
            System.out.println(room.getSize() + "룸");
            System.out.println("가격: " + room.getPrice() + "원");
        } else if(room.isReserved()==true){
            System.out.println(room.getRoomNumber()+"호실은 이미 예약된 방입니다");
        }
        else {
            System.out.println("존재하지 않는 방입니다");
        }
    }


    public Map<String, Room> getRooms() {
        return rooms;
    }
}
