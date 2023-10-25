/**
 * 고객 - 이름, 전화번호, 소지금
 */
public class Customer {
    private String name;
    private String phone;
    private int cash=0;

    public String getCustomerName(){

        return name;

    }
    public String getCustomerPhone(){
        return phone;

    }
    public void subCustomerCash(int roomPrice){

        cash-=roomPrice;

    }
    public void addCustomerCash(int roomPrice){

        cash+=roomPrice;

    }

}
