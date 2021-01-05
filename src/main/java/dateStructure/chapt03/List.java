package dateStructure.chapt03;

import dateStructure.chapt02.Position;

public interface List {
    int getSize();

    boolean isEmpty();

    Position first();

    Position last();

    Position getNext(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    Position getPrev(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    Position insertFirst(Object e);

    Position insertLast(Object e);

    Position insertBefore(Position position, Object e) throws ExceptionPositionInvalid;

    Position insertAfter(Position position, Object e) throws ExceptionPositionInvalid;

    Object remove(Position p) throws ExceptionPositionInvalid;

    Object removeFirst();

    Object removeLast();

    Object replace(Position p, Object obj) throws ExceptionPositionInvalid;

    Iterator positions();

    Iterator elements();

}
