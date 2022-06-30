package org.example.web.api;

import org.example.Services.FeedbackService;
import org.example.Services.Models.Feedback;
import org.example.web.api.models.FeedbackInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/feedback")
public class FeedbackController {
    private FeedbackService feedbackService;

    public FeedbackController (FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> listOfFeedbacks(
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        return feedbackService.listOfFeedbacks(page, pageSize);
    }

    @GetMapping(value = "/{feedback_ID}")
    public Feedback getFeedback(@PathVariable Integer feedback_ID) {
        return feedbackService.getFeedback(feedback_ID);
    }

    @PostMapping
    public Feedback createFeedback(@RequestBody FeedbackInput feedbackInput) {
        return feedbackService.createFeedback(feedbackInput.content, feedbackInput.sent_by);
    }

    @DeleteMapping(value = "/{feedback_ID}")
    public void deleteFeedback(@PathVariable Integer feedback_ID) {
        feedbackService.deleteFeedback(feedback_ID);
    }
}