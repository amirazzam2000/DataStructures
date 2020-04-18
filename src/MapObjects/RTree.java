package MapObjects;

import java.text.BreakIterator;

public class RTree {
    private Branch root;

    public RTree(int order) {
        this.root = new Branch(new Node(order),null,0,0,0,0);

    }


    /*
    * Steps to insert:
    *   - find the closest Branch to the new object
    *   - try to add the object (branch.getNode().addChild) -> if it fits
    *           done. else :
    *       - break the leaf Branch (store all leaves in an array[4]).
    *       - delete the branch that contains the node.
    *       - create two new Branch.
    *       - divide the objects between the branches
    *           (find the two relatively far away rectangle, then make them the
    *               first rectangle in the nodes then start from there)
    *       - for each rectangle left check to whom its closer and add it there
    *       - add the branches to the FATHER node (remember to run the resize
    *               after each add)
    *       - repeat the process with the new Branch on the higher level
    *       - if it doesn't fit do the process and add them to the CURRENT node
    *
    * */
    public boolean addObject(MapObject object) {
        Branch currentNode = null;
        int i = root.getChild().minDistanceFromBranches(object);
        int p = i;
        int g = p;

        /* if the root is not a leaf */
        if (root.getChild().getChild(0) != null && !root.getChild().isLeaf()){
            root.resize();
            currentNode = (Branch) root.getChild().getChild(i);
            /* get the closest rectangle in the current branch to the MapObject
              we are trying to add */
            g = p;
            p = i; // remember the last step we did
            i = currentNode.getChild().minDistanceFromBranches(object);

            /* while the node is not a leaf */
            while (!currentNode.getChild().getChild(i).isLeaf()) {
                currentNode.resize();
                /* go deeper in the tree finding the closest leaf Node to the
                MapObject */

                currentNode = (Branch) currentNode.getChild().getChild(i);
                g=p;
                p=i;
                i = currentNode.getChild().minDistanceFromBranches(object);

            }

            /* if you can NOT add the MapObject to the leaf node because it
            doesn't fit */
            if(!currentNode.getChild().addChild(object)){

                /*add all leafs to an array so you can distribute them */
                Rectangle[] brokenItems = new Rectangle[Node.MAX_ORDER + 1];
                brokenItems[0] = object;

                int j = 1;
                for (Rectangle child: currentNode.getChild().getChildren()) {
                    brokenItems[j++] = child; // adding MapObjects to the array
                }

                /* delete all the information in the Node and then delete the
                 node */
                Branch fatherNode = currentNode.getFatherNode();
                if (currentNode.getFatherNode() == null)
                    fatherNode = root;

                fatherNode.getChild().deleteBranch(currentNode);
                //currentNode.getChild().deleteAllChildren();
                //currentNode.deleteNode();

                Rectangle[] aux = new Rectangle[2];
                Branch[] auxBranches = new Branch[2];

                aux = Rectangle.findFarAwayNode(brokenItems);
                auxBranches[0] = new Branch(new Node(),fatherNode, aux[0].getX1(),
                        aux[0].getY1(),aux[0].getX2(),aux[0].getY2());
                auxBranches[0].getChild().addChild(aux[0]);
                aux[0].setFatherNode(auxBranches[0]);

                auxBranches[1] = new Branch(new Node(),fatherNode, aux[1].getX1(),
                        aux[1].getY1(),aux[1].getX2(),aux[1].getY2());
                auxBranches[1].getChild().addChild(aux[1]);
                aux[1].setFatherNode(auxBranches[1]);

                for (Rectangle left: (brokenItems)) {
                    if (left != aux[0] && left != aux[1]){
                        if (left.getDistance(auxBranches[0]) > left.getDistance(auxBranches[1])){
                            auxBranches[1].getChild().addChild(left);
                            auxBranches[1].resize();
                            auxBranches[1].getChild().getChild(left).setFatherNode(auxBranches[1]);
                        }
                        else{
                            auxBranches[0].getChild().addChild(left);
                            auxBranches[0].resize();
                            auxBranches[0].getChild().getChild(left).setFatherNode(auxBranches[0]);
                        }
                    }
                }

                fatherNode.getChild().addChild(auxBranches[0]);
                fatherNode.resize();

                if(!fatherNode.getChild().addChild(auxBranches[1])){
                    insertBranch(fatherNode, auxBranches[1]);
                }
                fatherNode.resize();

            }

        }
        else{
            if(!root.getChild().addChild(object)){

                /*add all leafs to an array so you can distribute them */
                Rectangle[] brokenItems = new Rectangle[Node.MAX_ORDER + 1];
                brokenItems[0] = object;

                int j = 1;
                for (Rectangle child: root.getChild().getChildren()) {
                    brokenItems[j++] = child; // adding MapObjects to the array
                }

                /* delete all the information in the Node and then delete the
                 node */
                root.getChild().deleteAllChildren();

                Rectangle[] aux = new Rectangle[2];
                Branch[] auxBranches = new Branch[2];
                aux = Rectangle.findFarAwayNode(brokenItems);
                auxBranches[0] = new Branch(new Node(),root, aux[0].getX1(),
                        aux[0].getY1(),aux[0].getX2(),aux[0].getY2());
                auxBranches[0].getChild().addChild(aux[0]);
                aux[0].setFatherNode(auxBranches[0]);

                auxBranches[1] = new Branch(new Node(),root, aux[1].getX1(),
                        aux[1].getY1(),aux[1].getX2(),aux[1].getY2());
                auxBranches[1].getChild().addChild(aux[1]);
                aux[1].setFatherNode(auxBranches[1]);

                for (Rectangle left: (brokenItems)) {
                    if (left != aux[0] && left != aux[1]){
                        if (left.getDistance(auxBranches[0]) > left.getDistance(auxBranches[1])){
                            auxBranches[1].getChild().addChild(left);
                            auxBranches[1].resize();
                            auxBranches[1].getChild().getChild(left).setFatherNode(auxBranches[1]);
                        }
                        else{
                            auxBranches[0].getChild().addChild(left);
                            auxBranches[0].resize();
                            auxBranches[0].getChild().getChild(left).setFatherNode(auxBranches[0]);
                        }
                    }
                }

                root.getChild().addChild(auxBranches[0]);
                root.getChild().addChild(auxBranches[1]);

            }
            root.resize();
        }

        return true;
    }

    private void insertBranch(Branch currentNode,
                              Branch branchToBeAdded) {

        Branch fatherNode = currentNode.getFatherNode();
        if (currentNode.getFatherNode() == null){
            fatherNode = root;
        }

        /*add all leafs to an array so you can distribute them */
        Rectangle[] brokenItems = new Rectangle[Node.MAX_ORDER + 1];
        brokenItems[0] = branchToBeAdded;

        int j = 1;
        for (Rectangle child: currentNode.getChild().getChildren()) {
            brokenItems[j++] = child; // adding MapObjects to the array
        }

        if(currentNode != root){
                /* delete all the information in the Node and then delete the
                 node */
            fatherNode.getChild().deleteBranch(currentNode);

        }
        else{
                /* delete all the information in the Node */
            currentNode.getChild().deleteAllChildren();

        }


        Rectangle[] aux = new Rectangle[2];
        Branch[] auxBranches = new Branch[2];


        aux = Rectangle.findFarAwayNode(brokenItems);
        auxBranches[0] = new Branch(new Node(),fatherNode, aux[0].getX1(),
                aux[0].getY1(),aux[0].getX2(),aux[0].getY2());
        auxBranches[0].getChild().addChild(aux[0]);
        aux[0].setFatherNode(auxBranches[0]);

        auxBranches[1] = new Branch(new Node(),fatherNode, aux[1].getX1(),
                aux[1].getY1(),aux[1].getX2(),aux[1].getY2());
        auxBranches[1].getChild().addChild(aux[1]);
        aux[1].setFatherNode(auxBranches[1]);

        for (Rectangle left: (brokenItems)) {
            if (left != aux[0] && left != aux[1]){
                if (left.getDistance(auxBranches[0]) > left.getDistance(auxBranches[1])){
                    auxBranches[1].getChild().addChild(left);
                    auxBranches[1].resize();
                    auxBranches[1].getChild().getChild(left).setFatherNode(auxBranches[1]);
                }
                else{
                    auxBranches[0].getChild().addChild(left);
                    auxBranches[0].resize();
                    auxBranches[0].getChild().getChild(left).setFatherNode(auxBranches[0]);
                }
            }
        }

        fatherNode.getChild().addChild(auxBranches[0]);
        fatherNode.resize();

        if (!fatherNode.getChild().addChild(auxBranches[1])){
            insertBranch(fatherNode, auxBranches[1]);
        }
        fatherNode.resize();

    }



}
