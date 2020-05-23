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

        int i = Integer.MIN_VALUE;
        for (List<HashItem> items :
                hashMapItems) {
            if (items != null){
                if (i < items.size()){
                    i = items.size();
                }
            }
        }
        System.out.println( "Max elements in one bucket is : " + i);
    }

    private int hasFunction(String key){
        int pos = 0;
        for (int i = 0; i < key.length(); i++) {
            pos += ((int)key.charAt(i)) * Math.pow(2,i) ;
        }
        if (key.length() > 1)
            pos += ((int)key.charAt(key.length() - 1)) * 3;

        return pos%size;
    }

    public HashItem find(String key){
        int pos = hasFunction(key);

        if (hashMapItems[pos].size() == 1){
            return hashMapItems[pos].get(0);
        }else {
            for (int i = 0; i < hashMapItems[pos].size(); i++) {
                if(hashMapItems[pos].get(i).getName().equals(key))
                    return hashMapItems[pos].get(i);
            }
        }
        return null;
    }

    public boolean delete(String key){
        int pos = hasFunction(key);
        if (hashMapItems[pos] != null) {
            if (hashMapItems[pos].size() == 1) {
                hashMapItems[pos].delete(0);
                return true;
            } else {
                for (int i = 0; i < hashMapItems[pos].size(); i++) {
                    if (hashMapItems[pos].get(i).getName().equals(key)) {
                        hashMapItems[pos].delete(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void print(){
        int i = 0;
        for (List<HashItem> item :
                hashMapItems) {
            System.out.print(i + " : ");
            if (hashMapItems[i] != null) {
                for (int j = 0; j < hashMapItems[i].size(); j++) {
                    System.out.print(hashMapItems[i].get(j).getName());
                    if (j < hashMapItems[i].size() - 1)
                        System.out.print(" , ");
                }
            }
            System.out.println();
            i++;
        }
    }

}
