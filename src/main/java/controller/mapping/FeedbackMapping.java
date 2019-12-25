package controller.mapping;

import database.tables.FeedbackTable;
import model.User;
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
    @RequestMapping(value = "/create/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user) throws SQLException {
        return feedbackTable.createFeedback(user);
    }

    @ResponseBody
    @GetMapping("/read/user/{email}")
    public User readUser(@PathVariable("email") String email) throws SQLException{
        return feedbackTable.readFeedback(email);
    }

    @ResponseBody
    @GetMapping("/read/users")
    public List<User> readUsers() throws SQLException{
        return feedbackTable.readFeedbacks();
    }

    @ResponseBody
    @RequestMapping(value = "/update/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@RequestBody List<User> users) throws Exception {
        User oldUser = users.get(0);
        User newUser = users.get(1);

        return feedbackTable.updateFeedback(oldUser, newUser);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUser(@RequestBody User user) throws Exception {
        return feedbackTable.deleteFeedback(user);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean login(@RequestBody User user) throws Exception {
        return feedbackTable.login(user);
    }
}