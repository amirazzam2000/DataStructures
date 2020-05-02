# P4_amir.azzam_sonia.leal_felipe.perez_arcadia.youlten

We used JAVA SDK 13

to run the files you only have to press the run button. a menu like this will show up :
<i>
1. dijkstra menu
2. RTree menu
3. BTree menu
4. exit.
</i>

<B> to Run Dijkstra </B>

When you select option 1, you will have to select the size of the file you would like to run. To do so, just
 type : 'S' (small), 'M' (medium), 'L' (large), or 'T' (testing file)
 
Then you will have to wait until the files are parsed. After this is done, enter the starting and ending rooms, and your desired path will be printed.

 the output is in the order you should visit the nodes, with the
 probability of finding an enemy at each level

<B> to Run the RTree </B>

When you select option 2, the first thing the program will do is to reload the dataset (so if you exit this option and enter it again the data sets will be reset to the default datasets)
then another menu will show up: 
<i>
1. visualise data set.
2. see surrounding objects and pick them up. (the element will be delete from the tree)
3. go back to menu.
</i>

You can select option 1 to visualize the data set. The format of which is the following:
    -We add one more tab for each new level
    - If a node is a branch, we add a 'B'
    -  If a node is a leaf, we ad an 'L' 

In option 2 you will be able to introduce your current location,  and the program will return the ids of all the map objects you collide with. Then it will offer you the option to pick the item up by selecting its id. Additionally, the item will be deleted it if you pick it up.


<B> to Run the BTree </B>


