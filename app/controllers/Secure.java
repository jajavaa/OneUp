package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class Secure extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context context) {
        return context.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return redirect(routes.LoginController.login());
    }

}
