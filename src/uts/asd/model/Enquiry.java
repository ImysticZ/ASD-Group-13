package uts.asd.model;

import java.io.Serializable;

public class Enquiry implements Serializable{
    
    private int enquiryID;
    private String question;
    private String reply;
    private boolean resolved;

    public Enquiry(int enquiryID, String question, String reply, boolean resolved){
        this.enquiryID = enquiryID;
        this.question = question;
        this.reply = reply;
        this.resolved = resolved;
    }

    public int getEnquiryID(){
        return enquiryID;
    }

    public void setEnquiryID(int enquiryID){
        this.enquiryID = enquiryID;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getReply(){
        return reply;
    }

    public void setReply(String reply){
        this.reply = reply;
    }

    public boolean getResolved(){
        return resolved;
    }

    public void setResolved(boolean resolved){
        this.resolved = resolved;
    }
}