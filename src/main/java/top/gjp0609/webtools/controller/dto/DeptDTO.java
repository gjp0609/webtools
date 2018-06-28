package top.gjp0609.webtools.controller.dto;

import lombok.Data;
import top.gjp0609.webtools.entity.User;

import java.util.List;

@Data
public class DeptDTO {

    private Long id;
    private Long success = -1L;
    private String name;
    private List<User> userList;

}