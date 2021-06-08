package designMode.StateMode;

/*
    状态模式定义：对象行为的变化是由于状态的变化引入，那么即当内部状态发生变化的时候，就会改变对象的行为，而这种改变视乎就改变了整个类。
 */
public class StateMachine {
    final static int SoldOutState = 0; //初始状态
    final static int OnReadyState = 1;  //待机状态
    final static int HasCoin = 2;  //准备状态
    final static int SoldState = 3;  //售出状态

    private int state = SoldOutState; //变量,用于存放当前的状态值
    private int count = 0; //糖果的数目

    public StateMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = OnReadyState;
        }
    }

    //投入硬币行为的时候，通过判断当前的状态来匹配所有的状态.
    public void insertCoin() {
        switch (state) {
            case SoldOutState:
                System.out.println("you can't insert coin,the machine sold out!");
                break;
            case OnReadyState: //只有在待机状态的时候,投入硬币行为正确,并将状态改变为准备状态
                state = HasCoin;
                System.out.println("you have inserted a coin,next,please turn crank!");
                break;
            case HasCoin:
                System.out.println("you can't insert another coin!");
                break;
            case SoldState:
                System.out.println("please wait!we are giving you a candy!");
                break;
        }

    }

    //回退硬币
    public void returnCoin() {
        switch (state) {
            case SoldOutState:
                System.out
                        .println("you can't return,you haven't inserted a coin yet!");
                break;
            case OnReadyState:
                System.out.println("you haven't inserted a coin yet!");
                break;
            case HasCoin:

                System.out.println("coin return!");
                state = OnReadyState;

                break;
            case SoldState:
                System.out.println("sorry,you already have turned the crank!");

                break;
        }

    }

    //转动曲柄
    public void turnCrank() {
        switch (state) {
            case SoldOutState:
                System.out.println("you turned,but there are no candies!");
                break;
            case OnReadyState:
                System.out.println("you turned,but you haven't inserted a coin!");
                break;
            case HasCoin:
                System.out.println("crank turn...!");
                state = SoldState;
                dispense();
                break;
            case SoldState:
                System.out
                        .println("we are giving you a candy,turning another get nothing,!");
                break;
        }

    }

    //触发发放糖果行为
    private void dispense() {
        count = count - 1;
        System.out.println("a candy rolling out!");
        if (count > 0) {
            state = OnReadyState;
        } else {
            System.out.println("Oo,out of candies");
            state = SoldOutState;
        }

    }

    public void printstate() {

        switch (state) {
            case SoldOutState:
                System.out.println("***SoldOutState***");
                break;
            case OnReadyState:
                System.out.println("***OnReadyState***");
                break;
            case HasCoin:

                System.out.println("***HasCoin***");

                break;
            case SoldState:
                System.out.println("***SoldState***");
                break;
        }

    }
}
