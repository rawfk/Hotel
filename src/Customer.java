import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 고객 - 이름, 전화번호, 소지금
 */
public class Customer {
    private String name;
    private String phone;
    private long money;
    private List<UUID> reservationNumberList;

    public Customer(String name, String phone, long money) {
        this.name = name;
        this.phone = phone;
        this.money = money;
        this.reservationNumberList = new ArrayList<>();
    }

}
