package ma.emsi.contact_2;

public class ContactModel
{
    private String name, phone,image; private int id;
    public ContactModel( int id,String name, String phone, String image) {
        this.name = name;this.phone = phone;this.image = image;this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getImage() {return image;}
    public void setImage(String image) {this.image = image;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
}
