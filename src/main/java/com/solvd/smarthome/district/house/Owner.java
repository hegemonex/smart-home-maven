package com.solvd.smarthome.district.house;

import com.solvd.smarthome.timeadapter.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Owner {

    private String name;
    private String email;
    private String phone;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate memberSince;

    public Owner(String name, String email, String phone, LocalDate memberSince) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.memberSince = memberSince;
    }

    public Owner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public String ownerInfo() {
        return "Owner: " + name + " | Email: " + email + " | Member since: " + memberSince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner owner)) return false;
        return Objects.equals(email, owner.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}