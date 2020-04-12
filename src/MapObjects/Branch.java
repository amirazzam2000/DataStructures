package MapObjects;

public class Branch extends Rectangle {
    private Node child;
    private static int ID = 0;

    public Branch(Node child, int x1, int y1, int x2, int y2) {
        super(ID++, x1, y1, x2, y2);
        super.addCenter();
        this.child = child;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public void resize(){
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Rectangle r: child.getChildren()) {
            if(r != null){
                if (r.getX1() < minX)
                    minX = r.getX1();
                if (r.getX2() > maxX)
                    maxX = r.getX2();
                if (r.getY2() < minY)
                    minY = r.getY2();
                if (r.getY1() > maxY)
                    maxY = r.getY1();
            }
        }
        super.setX1(minX);
        super.setX2(maxX);
        super.setY1(maxY);
        super.setY2(minY);
        super.addCenter();
    }


}
