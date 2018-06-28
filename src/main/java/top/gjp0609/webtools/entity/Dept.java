package top.gjp0609.webtools.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import top.gjp0609.webtools.common.validation.Groups;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class Dept {
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
//    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
//    private List<User> userList;
}
