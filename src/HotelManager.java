import java.util.List;
import java.util.Map;
import java.util.Scanner;

class HotelManager {
    private Customer cus;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private Map<String, Room> rooms;
    private Scanner scanner;
    private Hotel hotel;

    public HotelManager(Customer cus, ReservationManager reservationManager, RoomManager roomManager, Map<String, Room> rooms, Scanner scanner,Hotel hotel) {
        this.cus = cus;
        this.reservationManager = reservationManager;
        this.roomManager = roomManager;
        this.rooms = rooms;
        this.scanner = scanner;
        this.hotel=hotel;
    }


    public void run() {
        boolean flag = true;
        while (flag) {
            printMainMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    if (cus.getId().equals("admin")){
                        addRoom();
                    }else {
                        makeReservation();
                    }
                    break;
                case 2:
                    viewReservations();
                    break;
                case 3:
                    if (cus.getId().equals("admin")){
                        roomManager.showRoomList();
                        deleteRoom();
                    }else {
                        viewReservations();
                        cancelReservation();
                    }
                case 4: flag = false;
                    break;
                default:
                    System.out.println("올바른 메뉴를 선택하세요.");
            }
        }
    }

    private void printMainMenu() {
        if(cus.getId().equals("admin")) {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println("[관리자 서비스를 호출하셨습니다]");
            System.out.println("1. 방 추가하기");
            System.out.println("2. 전체 예약조회");
            System.out.println("3. 방 삭제하기");
            System.out.println("4. 로그아웃");
        }
        else {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println("어서오세요 원하시는 서비스를 선택해주세요.");
            System.out.println("1. 예약하기");
            System.out.println("2. 예약 조회");
            System.out.println("3. 예약 취소");
            System.out.println("4. 로그아웃");
        }
    }

    private int getUserChoice() {
        System.out.println("메뉴를 선택하세요: ");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        return scanner.nextInt();
    }

    private void makeReservation() {
        roomManager.showRoomList();
        System.out.println("예약할 방 번호를 입력하세요: ");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        String roomKey = scanner.next();
        if (!rooms.containsKey(roomKey)) {
            System.out.println("해당 방 번호가 존재하지 않습니다. 다시 입력해주세요.");
            return; // 방번호가 존재하지 않을 경우 메서드 종료
        }

        //예약 가능한 방을 선택하고 예약을 처리하는 기능
      if(cus.getCash()<rooms.get(roomKey).getPrice()){
          System.out.println("해당방을 예약하기위한 소지금이 부족합니다.");
          //현재 고객의 보유한 돈과 방의 가격을 비교하는 부분
      }
      else if (rooms.containsKey(roomKey) && !rooms.get(roomKey).isReserved()) {//방 예약 여부
            Reservation newReservation = new Reservation(rooms.get(roomKey), cus.getName(),cus.getPhone(), "2023-10-25T17:13:40+00:00",cus.getId());
            reservationManager.addReservation(newReservation);
            //예약 객체를 받은 정보로 생성하고 예약 매니저의 리스트로 넘겨주는 기능
            int price=rooms.get(roomKey).getPrice();
            cus.subCustomerCash(price);
            hotel.addAsset(price);
            //호텔과 고객의 소지금 차감
            System.out.println("예약이 완료되었습니다.");
        } else {
            System.out.println("해당 방은 예약할 수 없습니다.");
        }
    }

    private void viewReservations() {
        List<Reservation> reservations = reservationManager.getReservations();
        if (!reservations.isEmpty()&&cus.getId().equals("admin")) {
            for (Reservation reservation : reservations) {
                String reservationInfo = reservation.getAllReservationInfo();
                System.out.println(reservationInfo);
            }
        }else if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                String reservationInfo = reservation.getReservationInfo(cus.getId());
                System.out.println(reservationInfo);
            }
        }
        else {
            System.out.println("예약 정보가 없습니다.");
            System.out.println("메뉴로 돌아갑니다");
        }
    }

    private void cancelReservation() {
        List<Reservation> reservations = reservationManager.getReservations();
        while (true) {
            if (!reservations.isEmpty()) {
                System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
                System.out.println("취소할 방 번호를 입력하세요. 이전 화면으로 가시려면 00번을 눌러주세요");
                String cancelRoomNum = scanner.next();
                if (cancelRoomNum.equals("00")){
                    break;
                }else {//예약 취소기능 돈 차감과 돌아가기 예외처리
                    if (rooms.containsKey(cancelRoomNum)) {
                        int price = rooms.get(cancelRoomNum).getPrice();
                        cus.addCustomerCash(price);
                        hotel.subAsset(price);
                        boolean canceled = reservationManager.cancelReservation(cancelRoomNum);
                        if (canceled) {
                            System.out.println("예약이 취소되었습니다.");
                            break;
                        } else {
                            System.out.println("예약을 찾을 수 없습니다. 방번호를 확인해주십시요");
                        }
                    } else {
                        System.out.println("방번호를 확인해주십시요. 해당 방은 존재하지 않습니다.");
                    }
                }
            }
            else {
                return;
            }
        }
    }
    private void addRoom(){
        System.out.println("추가하실 방 번호를 입력하여 주십시요");
        String roomNum = scanner.next();

        if (rooms.containsKey(roomNum)) {
            System.out.println("이미 입력하신 방 번호와 같은 방이 존재합니다");
        } else {
            System.out.println("해당 방의 사이즈를 입력하여 주십시요");
            String roomSize = scanner.next();

            int price = 0;
            boolean validPrice = false;

            while (!validPrice) {
                System.out.println("해당 방의 가격을 입력하여 주십시요 (정수로 입력): ");
                if (scanner.hasNextInt()) {
                    price = scanner.nextInt();
                    validPrice = true;
                } else {
                    System.out.println("올바른 가격을 입력해주세요.");
                    scanner.next();
                }
            }

            roomManager.addRoom(roomNum, roomSize, price);
            System.out.println("성공적으로 방이 추가되었습니다");
        }
    }

    private void deleteRoom(){
        System.out.println("목록중 삭제하실 방 번호를 입력하여 주십시요");
        String roomNum=scanner.next();
        roomManager.deleteRoom(roomNum);
    }

}