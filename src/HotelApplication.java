import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HotelApplication {
     static Login reallogin =new Login(false);
     static Login login;
     static List<Customer> customerList;
     static List<Customer> listLogin;

    public static void main(String[] args) {

        customerList = new ArrayList<>();
        start();

    }
    
    public static void start(){



        while (true) {


            Scanner sc = new Scanner(System.in);
            System.out.println("호텔에 오신것을 환영합니다\n" +
                    "어떤것을 하시겠습니까?\n" +
                    "1.로그인         2. 회원가입");
            int select1 = sc.nextInt();
            String name;
            String phone;
            String regExp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
            int cash;
            String id;
            String pw;


            if (select1 == 1) {    //로그인
                System.out.print("ID : ");
                String loginId = sc.next();

                System.out.print("PW : ");
                String loginPw = sc.next();
                listLogin = loginChecking(loginId, loginPw);    //지금 로그인한 정보들을 갖고있는 리스트

                for (int i=0; i<listLogin.size(); i++){   //리스트 검색
                    System.out.println("이름 : "+listLogin.get(i).getName());
                    System.out.println("폰넘버 : "+listLogin.get(i).getPhone());
                    System.out.println("소지금 : "+listLogin.get(i).getCash());
                    System.out.println("아이디 : "+listLogin.get(i).getId());
                    System.out.println("패스워드 : "+listLogin.get(i).getPw());

                }



                System.out.println("로그아웃을 하시겠습니까?\n" +
                        "1.예         2. 아니요");
                int outNum = sc.nextInt();
                if(outNum==1){
                    logout();
//                    for (int i=0; i<listLogin.size(); i++){   //리스트 검색
//                        System.out.println("이름 : "+listLogin.get(i).getName());
//                        System.out.println("폰넘버 : "+listLogin.get(i).getPhone());
//                        System.out.println("소지금 : "+listLogin.get(i).getCash());
//                        System.out.println("아이디 : "+listLogin.get(i).getId());
//                        System.out.println("패스워드 : "+listLogin.get(i).getPw());
//
//                    }
                }
                else {
                    break;       //로그인하면 가장큰 와일문 탈출
                }






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

                login = new Login();
                while (true) {
                    System.out.print("사용하실 아이디 입력 : ");
                    id = sc.next();

                    if (id.equals("admin")) {             //관리자 회원가입 못하게
                        System.out.println("부적합한 아이디입니다. 다시 입력해주세요");
                    } else if (!reallogin.getLogin()) {     //처음 만드는거는 통과
                        break;
                    } else {             //아이디 존재하는지 확인하기

                        int a = 0;  //반복문 탈출
                        for (int i = 0; i < login.getCustomerList().size(); i++) {
                            if (id.equals(login.getCustomerList().get(i).getId())) {
                                System.out.println("아이디가존재합니다. 다시 입력해주세요");
                                break;
                            }
                            a++;
                        }
                        if (a == login.getCustomerList().size()) {
                            break;
                        }

                    }
                }


                System.out.print("사용하실 비밀번호 입력 : ");
                pw = sc.next();


                Customer customer2 = new Customer(name, phone, cash, id, pw);
                customerList.add(customer2);
                login = new Login(customerList);
                reallogin.setLogin(true);
                System.out.println("회원가입 완료 !!");


                for (int i = 0; i < login.getCustomerList().size(); i++) {   //아이디 검색
                    System.out.println("아이디 : " + login.getCustomerList().get(i).getId());

                }


            } else {
                System.out.println("잘못된 입력입니다. 종료합니다.");
            }

        }







    }

    public static List<Customer> loginChecking(String id ,String pw){
        login= new Login();
        List<Customer> lists= new ArrayList<>();
        if(id.equals("admin") && pw.equals("admin")){     //관리자인지 아닌지 확인하고 관리자 프로그램 실행 //이퀄스로 admin인진 확인 그리고 패스워드확인
            System.out.println("관리자 프로그램 실행");
        }
        else if(!reallogin.getLogin()){
            System.out.println("일치되는 아이디나 패스워드가 없습니다.");
            System.out.println("다시 시작해주세요.");


        }
        else {
            for(int i=0; i<login.getCustomerList().size(); i++){        //회원인지 아닌지 확인하기    //두번째는 회원정보(로그인)에 있는지 확인 없으면 다시백
                if(id.equals(login.getCustomerList().get(i).getId()) && pw.equals(login.getCustomerList().get(i).getPw())){

                    Customer customer= new Customer(login.getCustomerList().get(i).getName(),login.getCustomerList().get(i).getPhone(),login.getCustomerList().get(i).getCash()
                    ,login.getCustomerList().get(i).getId(),login.getCustomerList().get(i).getPw());
                    lists.add(customer);
                    System.out.println("로그인");
                    break;

                }
            }
        }

        return lists;


    }

    public static void logout(){
        listLogin.clear();
    }



}
