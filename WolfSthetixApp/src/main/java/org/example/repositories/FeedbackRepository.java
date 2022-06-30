package org.example.repositories;

import org.example.repositories.models.FeedbackDAO;

import java.util.List;
public interface FeedbackRepository {
    FeedbackDAO createFeedback(String content, Integer sent_by);
    FeedbackDAO getFeedback(int feedback_ID);
    List<FeedbackDAO> listOfFeedbacks(int page, int pageSize);
    void deleteFeedback(int feedback_ID);
}