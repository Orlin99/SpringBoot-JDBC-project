package org.example.Services.Models;

public class Feedback {
    public final Integer feedback_ID;
    public final String content;
    public final Integer sent_by;

    public Feedback(Integer feedback_ID, String content, Integer sent_by) {
        this.feedback_ID = feedback_ID;
        this.content = content;
        this.sent_by = sent_by;
    }
    @Override
    public String toString() {
        return "Feedback from User { " +
                "feedback_ID = " + feedback_ID +
                ", content = " + content +
                ", sent_by = " + sent_by + " }";
    }
}
