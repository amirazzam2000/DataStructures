import MapObjects.ObjectManger;

public class TestMain {
    public static void main(String[] args) {
        ObjectManger objects = new ObjectManger();

        objects.addCenters();

        System.out.println(objects.getObjects()[0].getId());
    }
}
