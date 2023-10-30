import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class HotelApplication {
    //list for signup users
     static List<Customer> customerList;
     //list for login user
     static Customer listLogin;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReservationManager reservationManager = new ReservationManager();
        RoomManager roomManager = new RoomManager();
        roomManager.addRoom("1", "싱글", 50000);
        roomManager.addRoom("2", "더블", 75000);
        roomManager.addRoom("3", "스위트", 100000);
        Map<String, Room> rooms = roomManager.getRooms();
        Hotel hotel= new Hotel(rooms);
        customerList = new ArrayList<>();
        customerList.add(new Customer("admin", "010-1234-1234", 0, "admin", "admin"));
        Customer customer;
        HotelManager hotelManager;
        while(true) {
            customer = start();
            hotelManager = new HotelManager(customer, reservationManager, roomManager, rooms, sc,hotel);
            hotelManager.run();
        }
    }
    
    public static Customer start(){
        Scanner sc = new Scanner(System.in);
        System.out.println("호텔에 오신것을 환영합니다\n" +
                "어떤것을 하시겠습니까?\n" +
                "1.로그인         2. 회원가입");
        int select1 = sc.nextInt();
        String name = "";
        String phone = "";
        String regExp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
        int cash = 0;
        String id = "";
        String pw = "";


        if (select1 == 1) {    //로그인
            System.out.print("ID : ");
            String loginId = sc.next();

            System.out.print("PW : ");
            String loginPw = sc.next();
            listLogin = loginChecking(loginId, loginPw);    //지금 로그인한 정보들을 갖고있는 리스트

            System.out.println("이름 : "+listLogin.getName());
            System.out.println("폰넘버 : "+listLogin.getPhone());
            System.out.println("소지금 : "+listLogin.getCash());
            System.out.println("아이디 : "+listLogin.getId());
            System.out.println("패스워드 : "+listLogin.getPw());

            return listLogin;

        } else if (select1 == 2) {             //회원가입
            System.out.print("이름 : ");
            name = sc.next();
            System.out.print("전화번호(-로 구분해주세요) : ");
            while (true) {
                phone = sc.next();
                if (phone.toString().matches(regExp)) {
                    phone = phone;
                    break;
                } else {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    System.out.print("전화번호(-로 구분해주세요) : ");
                }
            }
            System.out.print("소지금(숫자만 입력) : ");
            cash = sc.nextInt();

            System.out.print("사용하실 아이디 입력 : ");
            id = sc.next();

            if (id.equals("admin")) {             //관리자 회원가입 못하게
                System.out.println("부적합한 아이디입니다. 다시 입력해주세요");
            } else {             //아이디 존재하는지 확인하기
                for (int i = 0; i < customerList.size(); i++) {
                    if (id.equals(customerList.get(i).getId())) {
                        System.out.println("아이디가존재합니다. 다시 입력해주세요");
                        break;
                    }
                }
            }
        }
            System.out.print("사용하실 비밀번호 입력 : ");
            pw = sc.next();
            Customer customer2 = new Customer(name, phone, cash, id, pw);
            customerList.add(customer2);
            System.out.println("회원가입 완료 !!");
            return customer2;
        }

    public static Customer loginChecking(String id ,String pw){
        List<Customer> lists= new ArrayList<>();

        for(int i=0; i<customerList.size(); i++) {        //회원인지 아닌지 확인하기    //두번째는 회원정보(로그인)에 있는지 확인 없으면 다시백
            if (id.equals(customerList.get(i).getId()) && pw.equals(customerList.get(i).getPw())) {

                Customer customer = new Customer(customerList.get(i).getName(), customerList.get(i).getPhone(), customerList.get(i).getCash()
                        , customerList.get(i).getId(), customerList.get(i).getPw());
                lists.add(customer);
                System.out.println("로그인");
                break;

            }
        }
        return lists.get(0);
    }
}