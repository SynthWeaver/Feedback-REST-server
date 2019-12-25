package database.tables;

import database.DBMethods;
import model.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackTable {

    DBMethods dbMethods = new DBMethods();

    //
    // MAIN QUERIES
    //

    public ResponseEntity<Object> createFeedback(Feedback feedback) throws SQLException {
        String query = "INSERT INTO feedback (name, feedback) VALUES (?, ?)";

        PreparedStatement preparedStatement = dbMethods.getPreparedStatementFromQuery(query);
        preparedStatement.setString(1, feedback.getName());
        preparedStatement.setString(2, feedback.getFeedback());

        preparedStatement.executeUpdate();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public Feedback readFeedback(String id) throws SQLException {
        String query = "SELECT * FROM feedback WHERE id = ?";
        PreparedStatement preparedStatement = dbMethods.getPreparedStatementFromQuery(query);

        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return new Feedback(resultSet);
        }else {
            throw new RuntimeException("Resultset has no data");
        }
    }

    public List<Feedback> readFeedbacks() throws SQLException {
        String query = "SELECT * FROM feedback";
        PreparedStatement preparedStatement = dbMethods.getPreparedStatementFromQuery(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Feedback> feedbacks = new ArrayList<>();
        while (resultSet.next()) {
            feedbacks.add(new Feedback(resultSet));
        }

        return feedbacks;
    }

    public ResponseEntity<Object> updateFeedback(String oldFeedbackId, Feedback newFeedback) throws Exception {

        String query = "UPDATE feedback SET name = ?, feedback = ? WHERE id = ?";
        PreparedStatement preparedStatement = dbMethods.getPreparedStatementFromQuery(query);

        preparedStatement.setString(1, newFeedback.getName());
        preparedStatement.setString(2, newFeedback.getFeedback());
        preparedStatement.setString(3, oldFeedbackId);
        preparedStatement.executeUpdate();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<Object> deleteFeedback(String id) throws Exception {
        String query = "DELETE FROM feedback WHERE id = ?";
        PreparedStatement preparedStatement = dbMethods.getPreparedStatementFromQuery(query);

        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
