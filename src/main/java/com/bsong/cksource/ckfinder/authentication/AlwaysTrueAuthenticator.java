package com.bsong.cksource.ckfinder.authentication;

import com.cksource.ckfinder.authentication.Authenticator;
import javax.inject.Named;

@Named
public class AlwaysTrueAuthenticator implements Authenticator {
    @Override
    public boolean authenticate() {
        return true;
    }
}