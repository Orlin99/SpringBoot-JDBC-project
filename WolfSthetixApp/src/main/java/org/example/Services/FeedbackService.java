package org.example.Services;

import org.example.Services.Models.Feedback;
import org.example.repositories.FeedbackRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback createFeedback(String content, Integer sent_by) {
        return Mappers.fromFeedbackDAO(feedbackRepository.createFeedback(content, sent_by));
    }

    public Feedback getFeedback(int feedback_ID) {
        return Mappers.fromFeedbackDAO(feedbackRepository.getFeedback(feedback_ID));
    }

    public List<Feedback> listOfFeedbacks(int page, int pageSize) {
        return feedbackRepository.listOfFeedbacks(page, pageSize)
                .stream()
                .map(Mappers::fromFeedbackDAO)
                .collect(Collectors.toList());
    }

    public void deleteFeedback(int feedback_ID) {
        feedbackRepository.deleteFeedback(feedback_ID);
    }
}