package uts.asd.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

public class Validator implements Serializable {
    private String emailPattern = "[a-zA-Z0-9]{3,}"; //hard coded
    private String namePattern = "^[a-zA-Z0-9]{3,}";
    private String passwordPattern = "^[a-zA-Z0-9!@#$&]{4,}";
    private String phonePattern = "([0]{1}[4]{1}[0-9]{8})*";
    private String addressPattern = "^[a-zA-Z0-9!@#$&]{4,}";
    private String cardPattern = "^[0-9]{16}";
    private String cvcPattern = "^[0-9]{3}";
    private String typePattern = "[csa]"; //hard coded


    public Validator() {}

    public boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);
        return match.matches();
    }

    public boolean checkEmpty(String email, String password) {
        return email.isEmpty() || password.isEmpty();
    }

    public boolean validateEmail(String email) {
        if (email.contains("@") | email.contains(".")){
            return true;
        }
        return false;
    }

    public boolean validateFirstName(String name) {
        return validate(namePattern, name);
    }

    public boolean validateLastName(String name) {
        return validate(namePattern, name);
    }

    public boolean validatePassword(String password) {
        return validate(passwordPattern, password);
    }
    
    public boolean validatePhone(String phone) {
        return validate(phonePattern, phone);
    }
    
    public boolean validateType(String type) {
        if (type.equals("c") | type.equals("s") | type.equals("a")){
            return true;
        }
        return false;
    }
    
    public boolean validateAddress(String address) {
        return validate(addressPattern, address);
    }
    public boolean validateCard(String cardNo) {
        return validate(cardPattern, cardNo);
    }
    public boolean validateCVC(String cvc) {
        return validate(cvcPattern, cvc);
    }
    
    public void clear(HttpSession session) {
        session.setAttribute("emailErr", "Enter Email");
        session.setAttribute("passErr", "Enter Password");
        session.setAttribute("existErr", "");
        session.setAttribute("fnameErr", "Enter First Name");
        session.setAttribute("lnameErr", "Enter Last Name");
        session.setAttribute("phoneErr", "Enter Phone");
        session.setAttribute("addressErr", "Enter Address");
        session.setAttribute("typeErr", "Enter Type");
        session.setAttribute("enquiryEmptyErr", "");
    }
    public void clearCode(HttpSession session) {
        session.setAttribute("codeErr", "Enter Code");
    }
}

