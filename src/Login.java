import java.util.ArrayList;
import java.util.List;

public class Login {
    private static List customerList;
    private Boolean login;      //

    public Login(){
    }
    public Login(List<Customer> customerList){
        this.customerList=customerList;
    }
    public Login(Boolean login){
        this.login=login;
    }



    public List<Customer> getCustomerList(){
        return customerList;
    }
    public Boolean getLogin(){
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
}
