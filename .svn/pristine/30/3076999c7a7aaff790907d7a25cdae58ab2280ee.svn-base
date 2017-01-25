package com.asap.catalog.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.asap.security.Role;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * Represents a person to whom bugs can be assigned.
 *
 * @author Aleksei Sapunov
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("User")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends Component implements Serializable {

    private Locale userLocale;
    private String token;
    @OneToMany(mappedBy = "user")
    private List<UserPassword> userPasswords;
    private Boolean locked;
    private Boolean firstLogOn;

    /**
     * @return the senderAddress
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * @param senderAddress the senderAddress to set
     */
    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    /**
     * @return the senderPassword
     */
    public String getSenderPassword() {
        return senderPassword;
    }

    /**
     * @param senderPassword the senderPassword to set
     */
    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    /**
     * @return the userLocale
     */
    public Locale getUserLocale() {
        return userLocale;
    }

    /**
     * @param userLocale the userLocale to set
     */
    public void setUserLocale(Locale userLocale) {
        this.userLocale = userLocale;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the userPasswords
     */
    public List<UserPassword> getUserPasswords() {
        return userPasswords;
    }

    /**
     * @param userPasswords the userPasswords to set
     */
    public void setUserPasswords(List<UserPassword> userPasswords) {
        this.userPasswords = userPasswords;
    }

    /**
     * @return the locked
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * @param locked the locked to set
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * @return the firstLogOn
     */
    public Boolean getFirstLogOn() {
        return firstLogOn;
    }

    /**
     * @param firstLogOn the firstLogOn to set
     */
    public void setFirstLogOn(Boolean firstLogOn) {
        this.firstLogOn = firstLogOn;
    }

    public enum UserType {

        /**
         * Standard user, standard dashboard
         */
        STANDARD,
        /**
         * User has limited access to functionality
         */
        LIMITED;
    }
    private String acceptMails;
    @Column(/*unique = true,*/nullable = false, updatable = true)
    private String username;
    private String firstName;
    private String senderAddress;
    private String senderPassword;
    private String lastName;
    @Column(updatable = true)
    private String email;
    @Column(nullable = false, updatable = true)
    private String password;
    @Column(nullable = false, updatable = true)
    private Role role = Role.GUEST;
    private String phone;
    private String mobile;
    private String street;
    private String street2;
    private String postalCode;
    private String city;
    private String country;
    private String cvr;
    private String company;
    private String deactivated;
    @Column(name = "facebook_id")
    private String facebookId;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;
    /* @OneToMany(mappedBy = "user")
    private List<Step> steps;
     */
    // TODO: If it's needs
    //  private String country;
    @Column(name = "user_type", length = 10)
    @Enumerated(EnumType.STRING)
    private String userType;

    /**
     * Default constructor.
     */
    public User() {
        this.setRole(Role.USER);
        approved = new Integer(0);
        setCreated(new Date());
    }

    /**
     * Constructs a well formed person.
     */
    public User(String username, String password, String first, String last,
            String email) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFirstName(first);
        this.setLastName(last);
        this.setEmail(email);
        this.setRole(Role.GUEST);
        setCreated(new Date());
    }

    public UserPassword addToPasswordList() {
        UserPassword up = new UserPassword();
        up.setChangedAt(new Date());
        up.setPassword(this.getPassword());
        up.setUser(this);
        return up;
    }

    public String encryptPassword(String passwordToHash) {
        String generatedPassword = passwordToHash;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return generatedPassword;
    }

    /**
     * Gets the username of the person.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the person's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the person's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the person's unencrypted password.
     */
    public String getPassword() {
        return password;
    }

    public int getPasswordLength() {
        return getPassword().length();
    }

    /**
     * Sets the person's unencrypted password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Equality is determined to be when the ID numbers match.
     */
    public boolean equals(Object obj) {
        return (obj instanceof User) && this.getId() == ((User) obj).getId();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String toString() {
        return getId() + "";
    }

    public String getTitle() {
        return getUsername();
    }

    public String getFullname() {
        String res = "";
        if (getFirstName() != null) {
            res = res + getFirstName() + " ";
        }
        if (getLastName() != null) {
            res = res + getLastName();
        }
        return res;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    private String cap;

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public boolean eq(User user) {
        return this.getId().intValue() != user.getId().intValue();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public String getAcceptMails() {
        return acceptMails;
    }

    public void setAcceptMails(String acceptMails) {
        this.acceptMails = acceptMails;
    }
    private String currency;
    private Integer approved;
    private Double discount = new Double(0);

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(String deactivated) {
        this.deactivated = deactivated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    private static final long serialVersionUID = 1786056904951369604L;

    /**
     * @return the facebookId
     */
    public String getFacebookId() {
        return facebookId;
    }

    /**
     * @param facebookId the facebookId to set
     */
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
