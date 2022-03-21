package hello.jejulu.web.controller.host.hostFrom;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter@Setter

public class HostLoginFrom {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
