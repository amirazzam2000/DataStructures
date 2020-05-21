package Hash;

import Hash.List;

public class HashMapFriends {
    private List<HashItem>[] hashMapItems;
    private final int size;

    public HashMapFriends(HashItemManager hashItemManager) {
        size = (int)(hashItemManager.getHashItems().length * 0.7);
        this.hashMapItems =
                new List[(int)(hashItemManager.getHashItems().length * 0.7)];

        for (HashItem item: hashItemManager.getHashItems()) {
            if (hashMapItems[hasFunction(item.getName())] == null)
                hashMapItems[hasFunction(item.getName())] = new List<>();
            hashMapItems[hasFunction(item.getName())].add(item);
        }

        System.out.println();
    }

    private int hasFunction(String key){
        int pos = 0;
        for (int i = 0; i < key.length(); i++) {
            pos += ((int)key.charAt(i)) * Math.pow(2,i);
        }

        return pos%size;
    }
}
