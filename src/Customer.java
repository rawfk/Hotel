import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * 고객 - 이름, 전화번호, 소지금
 */
public class Customer {
    private String name;
    private String phone;
    private int cash;
    private List<UUID> reservationNumberList;
    private final String id;
    private final String pw;


    public Customer(String name, String phone, int cash, String id, String pw) {
        this.name = name;
        this.phone = phone;
        this.cash = cash;
        this.id = id;
        this.pw = pw;
        this.reservationNumberList = new ArrayList<>();
    }


    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public int getCash(){
        return cash;
    }

    public void setCash(int cash){
        this.cash = cash;
    }

    public String getId(){
        return id;
    }

    public String getPw(){
        return pw;
    }
    public void subCustomerCash(int roomPrice){

        cash-=roomPrice;

    }
    public void addCustomerCash(int roomPrice){

        cash+=roomPrice;

    }
}
