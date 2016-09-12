package androidgo.finalapp.model;

/**
 * Authors: Pallavi Santhosh Kumar
 *          Akansha Patel
 *          Shyama Sankar Vellore
 *          Ketki Haridas
 * Date: 08/16/2016
 * Contact: Class that represents a contact
 */
public class Contact {
    private String name;
    private String phoneNumber;

    /**
     * Contact: Empty constructor
     */
    public Contact() {
    }

    /**
     * Contact: Create contact with input name and phone number
     * @param name Contact name
     * @param phoneNumber Contact number
     */
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * getName: Get the contact name
     * @return Contact name
     */
    public String getName() {
        return name;
    }

    /**
     * setName: Set the contact name
     * @param name contact name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getPhoneNumber: Get the phone number
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * setPhoneNumber: Set the phone number
     * @param phoneNumber phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}