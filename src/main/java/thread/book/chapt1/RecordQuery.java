package thread.book.chapt1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

interface RowHandler<T> {
    T handle(ResultSet rs);
}

public class RecordQuery {
    private final Connection connection;

    public RecordQuery(Connection connection) {
        this.connection = connection;
    }

    public <T> T query(RowHandler<T> handler, String sql, Object ...params) throws SQLException {


        try (PreparedStatement stmt = connection.prepareStatement(sql);){

        int index = 1;
        for (Object obj : params) {
            stmt.setObject(index++, params);
        }
            ResultSet resultSet = stmt.executeQuery(sql);
            return handler.handle(resultSet);
        }
    }
}
