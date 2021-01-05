package thread.chapt1.chapt4;

import java.util.LinkedList;

public class EventQueue {

    private final int max;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;


    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                try {
                    console("the queue is full");

                    // 调用 wait 方法，线程进入阻塞
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }


    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                console("the queue is empty");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event firstEvent = eventQueue.removeFirst();
            this.eventQueue.notify();
            console("the event " + firstEvent + " is handled.");

            return firstEvent;
        }

    }


    private void console(String msg) {
        System.out.printf("%s:%s\n", Thread.currentThread().getName(), msg);
    }

}
