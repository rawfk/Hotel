import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 호텔 : 객실多, 보유 자산
 */
public class Hotel {

    //방번호-방(객체)
    private Map<String, Room> roomMap;
    //보유 자산
    private long asset;
    public Hotel() {
        roomMap = new HashMap<>();
        asset = 0;
    }

}
