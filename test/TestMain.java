import Hash.HashItemManager;
import Hash.HashMapFriends;

public class TestMain {
    public static void main(String[] args) {
        HashItemManager hashItemManager = new HashItemManager("S");
        System.out.println(hashItemManager.getHashItems().length);
        HashMapFriends hashMapFriends = new HashMapFriends(hashItemManager);
        System.out.println();
    }

}
