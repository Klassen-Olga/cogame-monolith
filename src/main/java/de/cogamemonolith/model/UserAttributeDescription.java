package de.cogamemonolith.model;


/*
 * Describes standardized messages and attribute sizes for api validation and swagger documentation
 * Changes to the size of the attributes will be directly reflected in the documentation.
 * */

public class UserAttributeDescription {

    public static final int userNameSize = 2;
    public static final int phoneNumberSize = 4;
    public static final int citySize = 2;
    public static final int countrySize = 4;
    public static final int occupationNameSize = 5;
    public static final int placeOfOccupationSize = 4;
    public static final int passwordSize = 6;

    public static final String userName = "Name should have at least " + userNameSize + " characters";
    public static final String dateOdBirth = "Date must be in the past";
    public static final String sex = "Allowed values: MALE, FEMALE, OTHER";
    public static final String phoneNumber = "Phone number should be at least" + phoneNumberSize + " digits";
    public static final String city = "City should contain al least " + citySize + " character";
    public static final String country = "Country should contain al least " + countrySize + " character";
    public static final String occupationName = "Occupation name should be at least " + occupationNameSize + " characters e.g. 'Student', 'Worker'";
    public static final String placeOfOccupation = "Place of occupation should be at least " + placeOfOccupationSize + " characters e.g. 'Student', 'Worker'";
    public static final String password = "Password should be longer than " + passwordSize + " characters";
    public static final String email = "Email should be unique and have form example@ex.com";
    public static final String preferencesList = "Optional info and can be initial empty";


}
