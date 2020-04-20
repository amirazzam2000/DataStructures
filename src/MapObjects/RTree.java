package MapObjects;

import java.awt.*;

public class RTree {
    private Branch root;

    public RTree(int order) {
        this.root = new Branch(new Node(order),null,0,0,0,0);

    }

    private Branch getLeafCurrentBranch(MapObject object){
        Branch currentNode = null;
        int i = root.getChild().minDistanceFromBranches(object);
        if (root.getChild().getChild(0) != null && !root.getChild().isLeaf()) {
            root.resize();
            currentNode = (Branch) root.getChild().getChild(i);
            /* get the closest rectangle in the current branch to the MapObject
              we are trying to add */

            i = currentNode.getChild().minDistanceFromBranches(object);

            /* while the node is not a leaf */
            while (!currentNode.getChild().getChild(i).isLeaf()) {
                currentNode.resize();
                /* go deeper in the tree finding the closest leaf Node to the
                MapObject */

                currentNode = (Branch) currentNode.getChild().getChild(i);

                i = currentNode.getChild().minDistanceFromBranches(object);

            }
        }
        else{
            currentNode = root;
        }

        return currentNode;
    }


    public void addObject(Rectangle object, Branch currentNode) {

        if (object instanceof MapObject)
                currentNode = getLeafCurrentBranch((MapObject) object);

        if(!currentNode.getChild().addChild(object)) {
            Branch fatherNode = currentNode.getFatherNode();
            if (currentNode.getFatherNode() == null) {
                fatherNode = root;
            }

            /*add all leafs to an array so you can distribute them */
            Rectangle[] brokenItems = new Rectangle[Node.MAX_ORDER + 1];
            brokenItems[0] = object;

            int j = 1;
            for (Rectangle child : currentNode.getChild().getChildren()) {
                brokenItems[j++] = child; // adding MapObjects to the array
            }

            if (currentNode != root) {
                /* delete all the information in the Node and then delete the
                 node */
                fatherNode.getChild().deleteBranch(currentNode);

            } else {
                /* delete all the information in the Node */
                currentNode.getChild().deleteAllChildren();

            }


            Rectangle[] aux = new Rectangle[2];
            Branch[] auxBranches = new Branch[2];


            aux = Rectangle.findFarAwayNode(brokenItems);
            auxBranches[0] = new Branch(new Node(), fatherNode, aux[0].getX1(),
                    aux[0].getY1(), aux[0].getX2(), aux[0].getY2());
            auxBranches[0].getChild().addChild(aux[0]);
            aux[0].setFatherNode(auxBranches[0]);

            auxBranches[1] = new Branch(new Node(), fatherNode, aux[1].getX1(),
                    aux[1].getY1(), aux[1].getX2(), aux[1].getY2());
            auxBranches[1].getChild().addChild(aux[1]);
            aux[1].setFatherNode(auxBranches[1]);

            for (Rectangle left : (brokenItems)) {
                if (left != aux[0] && left != aux[1]) {
                    if (left.getDistance(auxBranches[0]) > left.getDistance(auxBranches[1])) {
                        auxBranches[1].getChild().addChild(left);
                        auxBranches[1].resize();
                        auxBranches[1].getChild().getChild(left).setFatherNode(auxBranches[1]);
                    } else {
                        auxBranches[0].getChild().addChild(left);
                        auxBranches[0].resize();
                        auxBranches[0].getChild().getChild(left).setFatherNode(auxBranches[0]);
                    }
                }
            }

            fatherNode.getChild().addChild(auxBranches[0]);
            fatherNode.resize();

            if (!fatherNode.getChild().addChild(auxBranches[1])) {
                addObject( auxBranches[1], fatherNode);
            }
            fatherNode.resize();
        }
        currentNode.resize();
    }

    public Queue<MapObject> findObject(Point p){
        Queue<MapObject> objects = new Queue<>();
        Branch currentBranch = root;
        System.out.println("· you are colliding with these objects : ");
        findObjectRecursive(objects, currentBranch, p );



        return objects;
    }

    private void findObjectRecursive(Queue<MapObject> objects,
                                          Branch currentBranch, Point p) {
        for (int i = 0; i < Node.MAX_ORDER ; i++) {
            if (currentBranch.getChild().isLeaf()){
                Rectangle[] mapObjects = currentBranch.getChild().getChildren();
                for (Rectangle mapObject: mapObjects) {
                    if(mapObject != null && mapObject.isInside(p)){
                        objects.add((MapObject) mapObject);
                        System.out.println("    - " + mapObject.getId());
                    }
                }
                break;
            }else{
                if (currentBranch.getChild().getChild(i) != null && currentBranch.isInside(p))
                findObjectRecursive(objects,
                        (Branch)currentBranch.getChild().getChild(i) ,
                        p);
            }
        }
    }

    public Branch getRoot() {
        return root;
    }


    public void deleteElement(Rectangle object) {
        Branch fatherNode = object.getFatherNode();
        if (fatherNode == null){
            root.getChild().deleteChild(object);
            root.resize();
        }else{
            fatherNode.getChild().deleteChild(object);
            fatherNode.resize();
            if(fatherNode.getChild().isEmpty())
                deleteElement(fatherNode);
        }
    }

    public void findAllObjects(Queue<Rectangle> objects,
                                     Branch currentBranch) {
        for (int i = 0; i < Node.MAX_ORDER ; i++) {
            if (currentBranch.getChild().isLeaf()){
                Rectangle[] mapObjects = currentBranch.getChild().getChildren();
                for (Rectangle mapObject: mapObjects) {
                    if(mapObject != null){
                        objects.add(mapObject);
                    }
                }
                break;
            }else{
                if (currentBranch.getChild().getChild(i) != null ) {
                    objects.add(currentBranch.getChild().getChild(i));
                    findAllObjects(objects,
                            (Branch) currentBranch.getChild().getChild(i));
                }
            }
        }
    }

}
