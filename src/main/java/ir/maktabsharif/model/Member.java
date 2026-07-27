package ir.maktabsharif.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member extends BaseModel<Long> {
    @Column(name = "full_name")
    private String fullName;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    public Member(String fullName, String phone, String email, int yearOfBirth) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
    }

    public Member() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int birthYear) {
        this.yearOfBirth = birthYear;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %d | Full Name: %s | Phone: %s
                Email: %s | Year of Birth: %d
                """, this.getId(), fullName, phone, email, yearOfBirth);
    }
}
