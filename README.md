# Data Structures

Developed by:
<i>
- Amir Azzam
- Arcadia Youlten
- Felipe Perez
- Sonia Leal
</i>

We used JAVA SDK 13

to run the files you only have to press the run button. a menu like this will show up :
<i>
1. dijkstra menu
2. RTree menu
3. BTree menu
4. Hash Players
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


<B> to Run the BTree of max order 3 (2-3 tree) </B>

When you select option 3 from the main menu, it will reload all the items from the dataset into the tree. (keep this in mind when leaving the BTree menu).

Another menu will pop up

<i>
1. Visualize tree 
2. See if an object is in the shop
3. Remove an object in the shop
4. Go back to menu.
</i>

Here, you can visualize the tree and search/delete shop objects from it.

When choosing either the search or the delete options, you will be promted to enter if you want to search/delete the item by its name or by its price, and then the result will show (wether the item was deleted/found).

To verify the result, one can re-select the visualize option to see the changes.

<B> to Run the hashMap </B>

first you will have to select the file size :
To do so, just
 type : 'S' (small), 'M' (medium), or 'L' (large)
 
 then after the data is loaded you will be able to see what is the maximum
  element count in the buckets of the hashMap.
  
  then you will see a menu like this: 
  
  
<i>

1. Search for a Player
2. Delete a Player
3. Print HashMap
4. Back
</i>

if you select option 1, it will ask you to enter the username, then it will
 search the hashMap for that name (as we use the name as the key of the map)

then if you select option 2, it will ask you as well to enter the username, then it will
search the hashMap for that name (as we use the name as the key of the map
) and delete it if found. 

then you can select option 3 to print the content of the map, the content
 will be displayed in this format:
 
(number of the hashMap bucket) : (the elements in that bucket)

Finally, you can select option 4 to go back to the menu.

Note: that none of the information is saved after you exit the Hash players
 option, as every time you enter this option again the information is reloaded.
