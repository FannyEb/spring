package net.eicnam.fip1.ptt.backend.bodyrequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBody
{
    private String email;
    private String password;
}
