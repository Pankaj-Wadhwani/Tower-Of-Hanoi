package towerOfHanoi;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ListView<Ring> stack1,stack2,stack3;

    @FXML
    Label movesCount,minimumMoves,status;
    @FXML
    ComboBox numRingsComboBox;


    public int getNumRings() {
        return numRings;
    }

    public void setNumRings(int numRings) {
        this.numRings = numRings;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private int flag=0;
    private int numRings = 3;
    private  StackController stackController;
    private void doStartingWork(int numRings){
        this.numRings=numRings;

        stackController=new StackController(this.numRings,this);
        stackController.setRings(stack1,this.numRings);
        minimumMoves.setText((int)(Math.pow(2,this.numRings)-1)+"");
//        this.setLabelTextColor(this.status,"#000000");
//        stackController.setCount(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doStartingWork(3);
        String arr[]={"3","4","5","6","7","8"};
        numRingsComboBox.setItems(FXCollections.observableArrayList(arr));

        // Do not Allow multiple-selection in lists
        stack1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        stack2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        stack3.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

//        stack1.getItems().addAll(this.getRingList());
    }
//    @FXML
//    public void btnPressed(DragEvent ae){
//        Dragboard db=stack1.startDragAndDrop(TransferMode.MOVE);
//
//        System.out.print("hw");
//        ae.consume();
//    }
    public void restart(int numRings){
        stackController.restart();
        doStartingWork(numRings);
        setMovesCount(0);
    }
    @FXML
    void comboBoxActionPerformed(ActionEvent ae){
        restart(Integer.parseInt(numRingsComboBox.getSelectionModel().getSelectedItem().toString()));
    }
    @FXML
    void handleDragDetected(MouseEvent event) {
        stackController.writelog("Event on Source: drag detected");
        stackController.dragDetected(event,(ListView<Ring>) event.getSource());
        if (flag == 0) {
            setLabelTextColor(status,"#F4F4F4");
            flag=1;
        }
    }
    @FXML
    void handleDragOver(DragEvent event) {

            stackController.writelog("Evwent on Source: drag over");
            stackController.dragOver(event, (ListView<Ring>) event.getSource());

    }
    @FXML
    void handleDragDropped(DragEvent event) {

        stackController.writelog("Event on Source: drag dropped");
        stackController.dragDropped(event,(ListView<Ring>) event.getSource());
    }
    @FXML
    void handleDragDone(DragEvent event) {
        stackController.writelog("Event on Source: drag done");
        stackController.dragDone(event,(ListView<Ring>) event.getSource());
    }

//    @FXML
//    public void btnPressed1(DragEvent ae){
//        System.out.print("hw");
//    }
    @FXML
    public void restartButtonHandler(ActionEvent ae){
        restart(numRings);
    }


    public void setMovesCount(int count) {
        movesCount.setText(count+"");
    }

    public void setLabelTextColor(Label label, String s) {
        System.out.println(s);
        label.setTextFill(Color.web(s));
    }
}

/* winning code remaining*/