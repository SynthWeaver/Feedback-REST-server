package controller.mapping;

import database.tables.FeedbackTable;
import model.Feedback;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class FeedbackMapping {
    private FeedbackTable feedbackTable = new FeedbackTable();

    @ResponseBody
    @RequestMapping(value = "/create/feedback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createFeedback(@RequestBody Feedback feedback) throws SQLException {
        return feedbackTable.createFeedback(feedback);
    }

    @ResponseBody
    @GetMapping("/read/feedback/{id}")
    public Feedback readUser(@PathVariable("id") String id) throws SQLException{
        return feedbackTable.readFeedback(id);
    }

    @ResponseBody
    @GetMapping("/read/feedbacks")
    public List<Feedback> readFeedbacks() throws SQLException{
        return feedbackTable.readFeedbacks();
    }

    @ResponseBody
    @RequestMapping(value = "/update/feedback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateFeedback(@RequestBody List<Feedback> feedbacks) throws Exception {
        Feedback oldFeedback = feedbacks.get(0);
        Feedback newFeedback = feedbacks.get(1);

        return feedbackTable.updateFeedback(oldFeedback, newFeedback);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/feedback/{id}", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Object> deleteFeedback(@PathVariable String id) throws Exception {
        return feedbackTable.deleteFeedback(id);
    }
}