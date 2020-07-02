# Question 1:Implementing: Lists
![alt tag](https://raw.githubusercontent.com/uwoece-se2205b-2017/lab-01-lists-gui-introduction-JedraPeake/master/Capture.PNG?token=AXFuEdHL6FB_2EdnHzdaEwTnTQwgCKxMks5YnLTPwA%3D%3D)
### 1)In an ideal scenario, what other methods should be overridden to improve performance?
The iterator on both lists

### 2)Why is the iteration of an ArrayList and LinkedList not the same speed (not complexity, actual speed)? Which one is most likely faster?
Iteration of my linkedlist appears to be quicker in speed, probably because the insert and remove operations give good performance O(1) in LinkedList compared to ArrayListO(n). Hence if there is a requirement of frequent addition and deletion in application then LinkedList is a best choice, which is why it might be faster. 

### 3)Is the big-Oh complexity of the four main operations identical between the List types (add(), remove(), get() and set())?
###### add() and remove() -same
The add and remove for the Arraylist has a time complexity of O(n), because the remaining elements in an array have to be shifted left or right, however the linkedlist has to traverse from node to node if its node is not at the front or back of the list, therefore at a worst case O(n) complexity. 
###### get() and set() -different
The get and set for the Arraylist has a time complexity of O(1), as they can just access an element at the n'th spot, however the linkedlist has to traverse from node to node, therefore it has an O(n) complexity.

### 4)Describe the benefit(s) of the tests provided.
Unit Tests allow the coder to make big changes to code quickly. You then can instantly find out  whether or not the code works through multiple scenarios through testing, it can also help you identify errors just as fast and hopefully get it up and runnig. In addition, unit tests help you really understand the design of the code you are working on. Instead of writing code to do just do something for one problem, you are starting by outlining all the conditions you are subjecting the code to (the contracts) and what outputs you'd expect from that.

# Question 2:Creating a simple User Interface
![alt tag](https://raw.githubusercontent.com/uwoece-se2205b-2017/lab-01-lists-gui-introduction-JedraPeake/master/success.PNG?token=AXFuEcCHl2T1jNOLDiq059Ca6ksasplgks5YnLVEwA%3D%3D)
![alt tag](https://raw.githubusercontent.com/uwoece-se2205b-2017/lab-01-lists-gui-introduction-JedraPeake/master/failed.PNG?token=AXFuESphsif0TIuOgbdKmURQdBdAnGAvks5YnLUrwA%3D%3D)

### 1)Why is FXML used over raw code?
This separation of the presentation and application logic is attractive to web developers because they can assemble a UI that leverages Java components without mastering the code for fetching and filling in the data. The code is also a lot easier to follow and read.

### 2)What is the benefit of using SceneBuilder?
You can drag and drop UI components to a JavaFX Content pane, and the tool generates the FXML code that can be used in an IDE, so you dont have to code it.

### 3)What are a callbacks and why does JavaFX use them?
The interface looks like this:
```
public interface Callback<P,R> {
    public R call(P param);
}
```
We end up writing less code. No specialized interface, no default implementations.
