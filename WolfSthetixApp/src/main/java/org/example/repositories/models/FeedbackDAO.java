package org.example.repositories.models;

public class FeedbackDAO {
    public final Integer feedback_ID;
    public final String content;
    public final Integer sent_by;

    public FeedbackDAO(Integer feedback_ID, String content, Integer sent_by) {
        this.feedback_ID = feedback_ID;
        this.content = content;
        this.sent_by = sent_by;
    }
}
