import java.util.HashMap;
import java.util.Map;

/**
 * 호텔 : 객실多, 보유 자산
 */
public class Hotel {

    //방번호-방(객체)
    private Map<String, Room> roomMap;
    //보유 자산
    private long asset;
    public Hotel(Map<String, Room> rooms) {
        roomMap = rooms;
        asset = 0;
    }
    public void addAsset(int price){
        this.asset+= (long)price;
    }
    public void subAsset(int price){
        this.asset-= (long)price;
    }

}
