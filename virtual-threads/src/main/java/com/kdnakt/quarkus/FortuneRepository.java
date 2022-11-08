package com.kdnakt.quarkus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

@ApplicationScoped
public class FortuneRepository {

    static final String SELECT_ALL = "SELECT title FROM fortunes;";

    @Inject
    DataSource ds;
    @Inject
    PgPool pool;

    public List<Fortune> findAllBlocking() {
        List<Fortune> fortunes = new ArrayList<>();
        try (
            var conn = ds.getConnection();
            var ps = conn.prepareStatement(SELECT_ALL);
            var rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                fortunes.add(create(rs));
            }
        } catch (SQLException e) {
            System.err.println("Unable to retrieve fortunes from the database");
            e.printStackTrace();
        }
        return fortunes;
    }

    private Fortune create(ResultSet rs) throws SQLException {
        String title = rs.getString(1);
        System.out.println("LOADED: " + title);
        return new Fortune(title);
    }

    public Uni<List<Fortune>> findAllAsync() {
        return pool.preparedQuery(SELECT_ALL)
                .execute()
                .map(this::createListOfFortunes);
    }
    
    public List<Fortune> findAllAsyncAndAwait() {
        var rows = pool.preparedQuery(SELECT_ALL)
                .executeAndAwait();
        return createListOfFortunes(rows);
    }

    private List<Fortune> createListOfFortunes(RowSet<Row> rows) {
        List<Fortune> fortunes = new ArrayList<>();
        rows.forEach(row -> fortunes.add(new Fortune(row.getString(1))));
        return fortunes;
    }

}
