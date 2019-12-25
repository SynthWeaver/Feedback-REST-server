package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Feedback {
    private int id;
    private String name;
    private String feedback;

    public Feedback(int id, String name, String feedback) {
        this.id = id;
        this.name = name;
        this.feedback = feedback;
    }

    public Feedback(ResultSet rs) throws SQLException {
        this(
                (Integer) rs.getObject("id"),
                rs.getObject("name").toString(),
                rs.getObject("feedback").toString()
        );
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFeedback() {
        return feedback;
    }
}
