import Hash.HashItemManager;

public class TestMain {
    public static void main(String[] args) {
        HashItemManager hashItemManager = new HashItemManager("L");
        System.out.println(hashItemManager.getHashItems().length);
        System.out.println();
    }

}
