import Hash.HashItem;
import Hash.HashItemManager;
import Hash.HashMapFriends;

public class TestMain {
    public static void main(String[] args) {
        HashItemManager hashItemManager = new HashItemManager("M");
        System.out.println(hashItemManager.getHashItems().length);
        HashMapFriends hashMapFriends = new HashMapFriends(hashItemManager);

        HashItem item = hashMapFriends.find("Player 1");

        System.out.println(item.getName()+ " , " +
                item.getKda()+ " , " +
                item.getGames());

        hashMapFriends.delete("Player 1");


        hashMapFriends.print();

        System.out.println();
    }

}
