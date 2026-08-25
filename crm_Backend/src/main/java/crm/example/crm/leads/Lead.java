package crm.example.crm.leads;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "leads")
public class Lead {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String name;
private String email;
private String phone;
private String source;
private String company;
@Enumerated(EnumType.STRING)
private LeadStatus status;
@Column(name = "created_at") 
private LocalDateTime createdAt;
@Column(name = "updated_at") 
private LocalDateTime updatedAt;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
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

public String getSource() {
    return source;
}

public void setSource(String source) {
    this.source = source;
}

public String getCompany() {
    return company;
}

public void setCompany(String company) {
    this.company = company;
}

public LeadStatus getStatus() {
    return status;
}

public void setStatus(LeadStatus status) {
    this.status = status;
}

public LocalDateTime getCreatedAt() {
    return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}

public LocalDateTime getUpdatedAt() {
    return updatedAt;
}

public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
}

}