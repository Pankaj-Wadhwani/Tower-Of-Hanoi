package towerOfHanoi;


import javafx.scene.control.ListView;

import java.util.Stack;

public class StackHandler {
    public Stack<Ring> getStack1() {
        return stack1;
    }

    public Stack<Ring> getStack2() {
        return stack2;
    }

    public Stack<Ring> getStack3() {
        return stack3;
    }

    private Stack<Ring> stack1=new Stack<>(),stack2=new Stack<>(),stack3=new Stack();
    private  int numRings;
    public StackHandler(int numRings) {
        this.numRings = numRings;
        System.out.println(numRings);
    }

    public void push(ListView<Ring> stack, Ring ring) {
        getStack(stack).push(ring);

    }
    public void pop(ListView<Ring> stack) {
        getStack(stack).pop();

    }

    public boolean checkIsValid(Ring content, ListView<Ring> listView) {
        try {
            Stack<Ring> stack=getStack(listView);
//            System.out.println(stack);
            if (stack!=null && !stack.isEmpty()){
                int source=toInt(content.getName());
                int target =toInt(stack.peek().getName());
//                System.out.println(source);
//                System.out.println(target);
                if(source<target){
                    return true;
                }

                return false;
            }
            return true;
        }catch (Exception e){
            System.out.println(e);
            System.out.println(e.getCause());

            return false;
        }


    }
    public int toInt(String s){
        return Integer.parseInt(s);
    }
    public Stack<Ring> getStack(ListView<Ring> listView) {

        if (listView.getId().equals("stack1"))
            return stack1;
        else if (listView.getId().equals("stack2"))
            return stack2;
        else if (listView.getId().equals("stack3"))
            return stack3;
        return null;

    }

    public boolean checkIfWon() {
        if(stack3.size()==numRings)
            return true;
        return false;
    }
}
