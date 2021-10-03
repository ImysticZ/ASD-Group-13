package uts.asd.model;

import java.io.Serializable;

public class Enquiry implements Serializable{
    
    private int enquiryID;
    private String question;
    private String reply;
    private boolean resolved;
    private int userID;

    public Enquiry(int enquiryID, String question, String reply, boolean resolved, int userID){
        this.enquiryID = enquiryID;
        this.question = question;
        this.reply = reply;
        this.resolved = resolved;
        this.userID = userID;
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

    public int getUserID(){
        return userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }
}