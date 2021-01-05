package designMode.ModesOf21kinds;


class Context {
    public final static OpenningState openningState = new OpenningState();
    public final static ClosingState closingState = new ClosingState();
    public final static RunningState runningState = new RunningState();
    public final static StoppingState stoppingState = new StoppingState();

    public LiftState getLiftState() {
        return liftState;
    }

    private LiftState liftState;

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close() {
        this.liftState.close();
    }

    public void run() {
        this.liftState.run();
    }

    public void stop() {
        this.liftState.stop();
    }
}

abstract class LiftState {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();
}

class OpenningState extends LiftState {

    @Override
    public void open() {
        System.out.println("电梯门开启");
    }

    @Override
    public void close() {
        //状态修改
        super.context.setLiftState(Context.closingState);
        //动作委托CloseState 来执行
        super.context.getLiftState().close();
    }

    @Override
    public void run() {
        //do nothing
    }

    @Override
    public void stop() {
        //do nothing
    }
}

class ClosingState extends LiftState {

    @Override
    public void open() {
        //状态修改
        super.context.setLiftState(Context.openningState);
        //动作委托CloseState 来执行
        super.context.getLiftState().open();
    }

    @Override
    public void close() {
        System.out.println("电梯门关闭");
    }

    @Override
    public void run() {
        //状态修改
        super.context.setLiftState(Context.runningState);
        //动作委托CloseState 来执行
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        //状态修改
        super.context.setLiftState(Context.stoppingState);
        //动作委托CloseState 来执行
        super.context.getLiftState().stop();
    }
}

class RunningState extends LiftState {

    @Override
    public void open() { }

    @Override
    public void close() { }

    @Override
    public void run() {
        System.out.println("电梯在运行...");
    }

    @Override
    public void stop() {
        //状态修改
        super.context.setLiftState(Context.stoppingState);
        //动作委托CloseState 来执行
        super.context.getLiftState().stop();
    }
}

class StoppingState extends LiftState {

    @Override
    public void open() {
        //状态修改
        super.context.setLiftState(Context.openningState);
        //动作委托CloseState 来执行
        super.context.getLiftState().open();
    }

    @Override
    public void close() { }

    @Override
    public void run() {
        //状态修改
        super.context.setLiftState(Context.runningState);
        //动作委托CloseState 来执行
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停止了。。");
    }
}


public class StateModel {
    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}

class Context1 {

    public static final State STATE1 = new ConcretState();
    public static final State STATE2 = new ConcretState2();

    private State currentState;
    public void setCurrenttState(State state) {
        this.currentState = state;
        this.currentState.setContext(this);
    }

    // 行为委托
    public void handle1(){
        this.currentState.handle1();
    }

    public void handle2(){
        this.currentState.handle2();
    }

}
abstract class State {
    protected Context1 context;

    public void setContext(Context1 context) {
        this.context = context;
    }

    abstract void handle1();
    abstract void handle2();
}

class ConcretState extends State {

    @Override
    void handle1() {
        // 本状态下必须处理的逻辑
    }

    @Override
    void handle2() {
        //设置当前状态为 state2
        super.context.setCurrenttState(Context1.STATE2);
        // 过度到 state2 状态，由Context 实现
        super.context.handle2();
    }
}

class ConcretState2 extends State {

    @Override
    void handle1() {
        //设置当前状态为 state1
        super.context.setCurrenttState(Context1.STATE1);
        // 过度到 state2 状态，由Context 实现
        super.context.handle1();
    }

    @Override
    void handle2() {
        // 本状态下必须处理的逻辑

    }
}