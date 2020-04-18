import MapObjects.MapObject;
import MapObjects.ObjectManger;
import MapObjects.RTree;

public class TestMain {
    public static void main(String[] args) {
        ObjectManger objects = new ObjectManger();

        objects.addCenters();

        RTree rTree = new RTree(3);

        for (MapObject object: objects.getObjects()) {
            rTree.addObject(object);
        }

        System.out.printf("");
    }
}
