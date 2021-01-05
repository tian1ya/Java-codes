package designMode.sixPrinciples.SRP;



interface Phone {
    void dial(String phoneNumber);

    void chat(Object o);

    void hangup();
}

interface ConnectionManager {
    void dial(String phoneNumber);

    void hangup();
}

interface dataTransfer {
    void dataTransferer(ConnectionManager connectionManager);
}

interface PhoneV2 extends ConnectionManager, dataTransfer {

}
