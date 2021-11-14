package de.cogamemonolith.model;

/*
 * Describes standardized messages and attribute sizes for api validation and swagger documentation
 * Changes to the size of the attributes will be directly reflected in the documentation.
 * */

public class EventAttributeDescription {

    public static final int nameSize = 3;
    public static final int streetSize = 5;
    public static final int citySize = 3;
    public static final int countrySize = 4;
    public static final int activitiesSize = 1;
    public static final int activityNameSize = 3;
    public static final int participantsNumberMin = 2;

    public static final String name = "Name should have at least " + nameSize + " characters";
    public static final String dateAndTime = "Date and time of the event should be in the future";
    public static final String street = "Street should have at least " + streetSize + " characters";
    public static final String city = "City should have at least " + citySize + " characters";
    public static final String country = "Country should have at least " + countrySize + " characters";
    public static final String activities = "At least " + activitiesSize + " activity required";
    public static final String activityArt = "Allowed values: TABLE, ACTIVE, COMPUTER";
    public static final String activityName = "Activity name should contain at least " + activityNameSize + " characters";
    public static final String creatorUserId = "Creator id should not be empty";
    public static final String participants = "A map where key is id and value is name";
    public static final String participantsNumber = "Participants number of any event should at least " + participantsNumberMin + " persons";
}
