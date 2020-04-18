package MapObjects;


public class Node {
    private Rectangle[] children;

    public static int MAX_ORDER;
    public static int MIN_ORDER;


    public Node( int order){
        MAX_ORDER = order;
        MIN_ORDER = (int)Math.ceil((double) MAX_ORDER * 0.3);
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
    public Rectangle getChild(Rectangle child){
        for (int i = 0; i < MAX_ORDER; i++) {
            if(children[i] == child){
                return children[i];
            }
        }
        return null;
    }

    /**
     * adds a rectangle to the Node
     * @param r the rectangle to be added
     * @return if the rectangle is added successfully
     */
    public boolean addChild(Rectangle r){
        for (int i = 0; i < children.length; i++) {
            if (children[i] == null) {
                children[i] = r;
                return true;
            }
        }
        return false;
    }

    /**
     * @return if the current Node is a leaf or not
     */
    public boolean isLeaf(){

        for (Rectangle child: children) {
            if (child instanceof MapObject)
                return true;
        }

        return false;
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

    /**
     * gets which {@link Rectangle} is the closest to the given {@link Rectangle}
     * @param object the rectangle we are interested in knowing it's closest
     *               {@link Rectangle} from this {@link Node}
     * @return the number of the {@link Rectangle} from this {@link Node}
     *              closest to the <b><i>object</i><b/>
     */
    public int minDistanceFromBranches(Rectangle object){
        int minDistance = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = 0; i < children.length; i++) {
            if (children[i]!= null) {
                if (object.getDistance(children[i]) < minDistance) {
                    minDistance = object.getDistance(children[i]);
                    pos = i;
                }
            }
        }
        return pos;
    }

    public void deleteAllChildren (){
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
    }

    public void deleteBranch(Rectangle child){
        for (int i = 0; i < this.children.length; i++) {
            if (children[i] == child){
                children[i] = null;
                break;
            }
        }
    }


}
