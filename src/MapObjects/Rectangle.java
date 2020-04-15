package MapObjects;

import com.google.gson.annotations.SerializedName;

public class Rectangle {

    @SerializedName("id")
    private int id;
    @SerializedName("X1")
    private int x1;
    @SerializedName("Y1")
    private int y1;
    @SerializedName("X2")
    private int x2;
    @SerializedName("Y2")
    private int y2;
    private int centerX;
    private int centerY;

    public Rectangle(int id, int x1, int y1, int x2, int y2) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        addCenter();
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }


    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * adds the center of the rectangle.
     *
     */
    public void addCenter(){
        centerX = (x1+x2)/2;
        centerY = (y1+y2)/2;
    }

    public boolean compare(Rectangle r){
        return r.x1 == this.x1 && r.y1 == this.y1 && r.x2 == this.x2 && r.y2 == this.y2;
    }

    public int getDistance(Rectangle r){
        return (int) Math.sqrt((r.centerX - this.centerX)*(r.centerX - this.centerX) + (r.centerY - this.centerY)*(r.centerY - this.centerY));
    }

    public boolean isInside(Point p){
        return (p.getX() < x2 && p.getX() > x1 & p.getY() > y2 && p.getY() < y1 );
    }

    public boolean isInside(Rectangle r){
        return (r.getCenterX() < x2 && r.getCenterX() > x1 & r.getCenterY() > y2 && r.getCenterY() < y1 );
    }

    public int findArea(){
        return (x2-x1)*(y1-y2);
    }


    public static Rectangle[] findFarAwayNode(Rectangle[] objects){
        Rectangle[] farObjects = new Rectangle[2];

        class Distance{
            int distance;
            int id1;
            int id2;

            public Distance(int distance, int id1, int id2) {
                this.distance = distance;
                this.id1 = id1;
                this.id2 = id2;
            }

            public Distance() {
                distance = Integer.MIN_VALUE;
            }

            public int size(int number){
                int result = 1;
                for (int i = number - 1; i > 1; i--) {
                    result += (i);
                }
                return result;
            }
        }

        Distance maxDistance = new Distance();
        Distance[] distances = new Distance[maxDistance.size(objects.length)];
        int k = 0;
        for (int i = 0; i < objects.length - 1; i++) {
            for (int j = i + 1; j < objects.length; j++) {
                distances[k] =
                        new Distance((objects[i].getDistance(objects[j])),i,j);
                k++;
            }
        }

        for (Distance d: distances) {
            if(d.distance > maxDistance.distance)
                maxDistance = d;
        }

        farObjects[0] = objects[maxDistance.id1];
        farObjects[1] = objects[maxDistance.id2];

        return farObjects;
    }

    /**
     * @return if the current Rectangle is a leaf or not
     */
    public boolean isLeaf(){
        return this instanceof MapObject;
    }
}
