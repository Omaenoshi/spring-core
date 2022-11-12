package com.yet.spring.logger;

import com.yet.spring.bean.Event;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DBLogger implements EventLogger{
    private static final String SQL_ERROR_STATE_SCHEMA_EXISTS = "X0Y68";
    private static final String SQL_ERROR_STATE_TABLE_EXISTS = "X0Y32";

    private JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void destroy() {
        int total = getTotal();
        System.out.println("Total  in the DB: " + total);

        List<Event> all = getAll();
        String allEventIds = all.stream()
                .map(Event::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("All DB Event ids: " + allEventIds);
    }

    private int getMaxId() {
        Integer count = jdbcTemplate.queryForObject("select max(id) from events", Integer.class);
        return count != null ? count.intValue() : 0;
    }

    @Override
    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO events (id, date, msg) VALUES (?,?,?)", getMaxId() + 1, event.getDate(),
                event.toString());
        System.out.println("Saved to DB event with id " + event.getId());
    }

    public int getTotal() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from events", Integer.class);
        return count != null ? count.intValue() : 0;
    }

    public List<Event> getAll() {
        return jdbcTemplate.query("select * from events", new RowMapper<Event>() {
            @Override
            public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                String msg = rs.getString("msg");
                return new Event(id, new Date(date.getTime()), msg);
            }
        });
    }
}
