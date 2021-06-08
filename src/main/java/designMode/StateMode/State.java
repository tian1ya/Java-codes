package designMode.StateMode;

public interface State {
    void insertCoin();
    void returnCoin();
    void turnCrank();
    void dispense();
    void printState();
}
