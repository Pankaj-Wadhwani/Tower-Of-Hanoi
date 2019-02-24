package towerOfHanoi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.*;

public class StackController {
    private int numRings;
    private int count=0;
    private Controller controller;
    public StackController(int numRings,Controller c){
        controller=c;
//        controller.setMovesCount(count);
        this.numRings=numRings;
        System.out.println(numRings);
        stackHandler=new StackHandler(numRings);
    }
    static final DataFormat StackRing = new DataFormat("Ring");
    private StackHandler stackHandler;
     public void dragDetected(MouseEvent event, ListView<Ring> listView)
    {
        try {
//            System.out.print("hhh");

            // Make sure at least one item is selected

            int ringIndex = listView.getSelectionModel().getSelectedIndex();

            if (ringIndex == -1 || ringIndex!=0) {
//                event.consume();
                return;
            }

            // Initiate a drag-and-drop gesture
            Dragboard dragboard = listView.startDragAndDrop(TransferMode.MOVE);

            // Put the the selected items to the dragboard
            Ring selectedItem = this.getSelectedRing(listView);

            ClipboardContent content = new ClipboardContent();
            content.put(StackRing, selectedItem);

            dragboard.setContent(content);
            event.consume();
        }catch (Exception e){
            System.out.print("Ex = "+e);
            e.printStackTrace();
        }
    }
    public void dragDropped(DragEvent event, ListView<Ring> listView)
    {
        boolean dragCompleted = false;

        // Transfer the data to the target
        Dragboard dragboard = event.getDragboard();

        if(dragboard.hasContent(StackRing))
        {
            Ring ring= (Ring)dragboard.getContent(StackRing);
            listView.getItems().add(0,ring);
            stackHandler.push(listView,ring);

            // Data transfer is successful
            dragCompleted = true;
        }

        // Data transfer is not successful
        event.setDropCompleted(dragCompleted);
        event.consume();
    }
    public void dragOver(DragEvent event, ListView<Ring> listView)
    {
        // If drag board has an ITEM_LIST and it is not being dragged
        // over itself, we accept the MOVE transfer mode
        Dragboard dragboard = event.getDragboard();

        if (event.getGestureSource() != listView && dragboard.hasContent(StackRing) && stackHandler.checkIsValid((Ring)dragboard.getContent(StackRing),listView))
        {
            event.acceptTransferModes(TransferMode.MOVE);


        }

        event.consume();
    }
    public void dragDone(DragEvent event, ListView<Ring> listView)
    {
        // Check how data was transfered to the target
        // If it was moved, clear the selected items
        TransferMode tm = event.getTransferMode();

        if (tm == TransferMode.MOVE)
        {
            //write code of pop
            stackHandler.pop(listView);
            removeSelectedRing(listView);
            count++;
            controller.setMovesCount(count);
            if (stackHandler.checkIfWon())
            {
                System.out.println("won="+count);
                controller.setLabelTextColor(controller.status,"#000000");
                controller.restart(controller.getNumRings());
                controller.setFlag(0);

            }
        }

        event.consume();
    }
    public void removeSelectedRing(ListView<Ring> listView)
    {
        // Get all selected Fruits in a separate list to avoid the shared list issue
        Ring selectedRing = getSelectedRing(listView);



        // Clear the selection
        listView.getSelectionModel().clearSelection();
        // Remove items from the selected list
        listView.getItems().removeAll(selectedRing);
    }
    public Ring getSelectedRing(ListView<Ring> listView)
    {
        // Return the list of selected Fruit in an ArratyList, so it is
        // serializable and can be stored in a Dragboard.
        Ring ring = listView.getSelectionModel().getSelectedItem();

        return ring;
    }
    private ObservableList<Ring> getRingList(ListView<Ring> listView,int numRings)
    {
        ObservableList<Ring> list = FXCollections.observableArrayList();

        for (int i = 0; i < numRings; i++) {
            Ring ring=new Ring(new Integer(i+1).toString());
            list.add(ring);
            ring=new Ring(new Integer(numRings-i).toString());
            stackHandler.push(listView,ring);
//            System.out.println(stackHandler.getStack1().peek());
        }
        return list;
    }
    public void writelog(String text)
    {
//        System.out.append(text+"\n");
    }

    public void setRings(ListView<Ring> stack,int numRings) {
        ObservableList list = this.getRingList(stack,numRings);
        stack.setItems(list);
//        setStack(stack1,list);
    }
    public void restart(){
        System.out.println("ss="+stackHandler.getStack1().size());
        controller.stack1.getItems().remove(0,(stackHandler.getStack1().size()));
        controller.stack2.getItems().remove(0,(stackHandler.getStack2().size()));
        controller.stack3.getItems().remove(0,(stackHandler.getStack3().size()));
    }

    private void checkValidity(ListView dragListView,ListView dropListView){


    }
}
