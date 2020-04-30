import MapObjects.*;
import MapObjects.Point;
import MapObjects.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        ObjectManger objects = new ObjectManger();

        objects.addCenters();

        RTree rTree = new RTree(3);

        for (MapObject object: objects.getObjects()) {
            rTree.addObject(object, rTree.getRoot());
        }

        Queue<MapObject> objectQueue = rTree.findObject(new Point(50,750));
        objectQueue.add(rTree.findObject(new Point(20,500)));

        rTree.showGraph();

        if (!objectQueue.isEmpty()){
            Scanner sc = new Scanner(System.in);
            int id;
            boolean exit = false;
            System.out.println("which element would you like to pic up " +
                    "(please select the id of the element or write a " +
                    "character to exit )");
            do {
                try {
                    id = sc.nextInt();
                    MapObject aux =
                            objectQueue.popObjectByValue(objects.getObjectsById(id));
                    if (aux != null) {
                        rTree.deleteElement(aux);
                        System.out.println("deleted successfully!");
                    }
                    else
                        System.out.println("you are only allowed to pick " +
                                "element from the list above!");
                    if(objectQueue.isEmpty()) {
                        exit = true;
                        System.out.println(System.lineSeparator() +
                                "you don't have any more objects around you ");

                    }
                }catch (InputMismatchException e){
                    exit = true;
                }
            }while(!exit);
        }
        else {
            System.out.println("Sorry No objects where found!");
        }
        System.out.printf("");

       /* class Panel extends JPanel {
            RTree rTree;
            public Panel(RTree tree){
                rTree = tree;
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(4000, 4000);
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.scale(1.0, -1.0);
                g2d.translate(200, -2000);

                g2d.setColor(new Color(31, 21, 1));
                g2d.drawLine(0,0,1000,0);
                g2d.drawLine(0,0,0,700);
                Queue<Rectangle> r = new Queue<>();
                rTree.findAllObjects(r,rTree.getRoot());
                Rectangle aux;
                while (!r.isEmpty()){
                    aux = r.pop();
                    if(aux instanceof MapObject){
                        g2d.setColor(new Color(234, 158, 7));
                        g2d.drawRect(aux.getX1(),aux.getY1(),
                                (aux.getX2() - aux.getX1())
                                ,(aux.getY1() - aux.getY2()));
                    }
                    else{
                        if (aux == rTree.getRoot().getChild().getChild(0) || aux == rTree.getRoot().getChild().getChild(1) || aux == rTree.getRoot().getChild().getChild(2) )
                            g2d.setColor(new Color(250, 0, 0));
                        else
                            g2d.setColor(new Color(170, 102, 204));
                        g2d.drawRect(aux.getX1() - 5,aux.getY1() - 5,
                                (aux.getX2() - aux.getX1()) +5
                                ,(aux.getY1() - aux.getY2()) +5 );
                    }
                }
            }
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Panel rects = new Panel(rTree);
                JFrame frame = new JFrame("Rectangles");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JScrollPane sp = new JScrollPane(rects);
                frame.add(sp);
                frame.setSize(1000, 700);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
*/
    }

}
