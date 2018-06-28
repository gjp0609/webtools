package top.gjp0609.webtools.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import top.gjp0609.webtools.common.validation.Groups;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Null(groups = Groups.Add.class)
    @NotNull(groups = {Groups.Edit.class, Groups.Delete.class})
    private Long id;
    @Column(name = "name")
    @NotBlank(groups = {Groups.Add.class})
    @Size(min = 1, max = 30, groups = {Groups.Add.class, Groups.Edit.class})
    private String name;
    //    @JsonIgnore
    @Column(name = "password")
    @NotBlank(groups = {Groups.Add.class})
    @Size(min = 8, max = 16, groups = {Groups.Add.class, Groups.Edit.class})
    private String password;

//    @ManyToOne
//    @JoinColumn(name = "dept_id")
////    @JsonBackReference
//    private Dept dept;

    @Column(name = "deptId")
    private Long deptId;

    public User(
            @NotBlank(groups = {Groups.Add.class})
            @Size(min = 1, max = 30, groups = {Groups.Add.class, Groups.Edit.class}) String name,
            @NotBlank(groups = {Groups.Add.class})
            @Size(min = 8, max = 16, groups = {Groups.Add.class, Groups.Edit.class}) String password,
            Long deptId) {
        this.name = name;
        this.password = password;
        this.deptId = deptId;
    }

    public User() {
    }
}
