package dateStructure.chapt03;

import dateStructure.chapt02.Position;

public interface Sequence extends Vector, List{
    Position rank2Pos(int r) throws ExceptionBoundaryViolation;
    int pos2Rank(Position p) throws ExceptionBoundaryViolation;
}
