package MapObjects;


public class Node {
    private Rectangle[] children;

    public static int MAX_ORDER;
    public static int MIN_ORDER;


    public Node( int order){
        MAX_ORDER = order;
        MIN_ORDER = (int)Math.ceil((double) MAX_ORDER/2);
        children = new Rectangle[MAX_ORDER];
    }

    public Node(){
        children = new Rectangle[MAX_ORDER];
    }





    public Rectangle[] getChildren(){
        return children;
    }

    /**
     * gets a rectangle of the current Node
     * @param num the number of the rectangle requested
     * @return the rectangle requested if exists, null otherwise.
     *
     */
    public Rectangle getChild(int num){
        if (num < (MAX_ORDER) && children[num] != null){
            return children[num];
        }
        return null;
    }

    /**
     * adds a rectangle to the Node
     * @param r the rectangle to be added
     * @return if the rectangle is added successfully
     */
    public boolean addChild(Rectangle r){
        for (Rectangle rectangle: children) {
            if(rectangle == null){
                rectangle = r;
                return true;
            }
        }
        return false;
    }

    /**
     * @return if the current Node is a leaf or not
     */
    public boolean isLeaf(){
        return children instanceof MapObject[];
    }

    /**
     * searches for a matching rectangle and deletes it if found
     * @param r the rectangle to be found
     * @return if the rectangle has been deleted successfully
     */
    public boolean deleteChild(Rectangle r){
        if (isLeaf()) {
            for (int i = 0; i < MAX_ORDER; i++) {
                if (children[i] == r) {
                    if (i != MAX_ORDER - 1){
                        for (int j = i + 1; j < MAX_ORDER ; j++) {
                            children[i] = children[j];
                            i++;
                        }
                    }
                    children[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return returns if the Node is empty or not
     */
    public boolean isEmpty(){
        for (Rectangle r: children) {
            if (r != null)
                return false;
        }
        return true;
    }



}
