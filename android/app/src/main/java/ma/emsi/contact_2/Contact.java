package ma.emsi.contact_2;

import com.google.gson.annotations.SerializedName;

public class Contact
{
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("note")
    private String note;

    @SerializedName("image")
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
