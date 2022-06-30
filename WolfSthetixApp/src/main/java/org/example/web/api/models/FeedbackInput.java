package org.example.web.api.models;

public class FeedbackInput {
    public final String content;
    public final Integer sent_by;

    public FeedbackInput(String content, Integer sent_by) {
        this.content = content;
        this.sent_by = sent_by;
    }
}
