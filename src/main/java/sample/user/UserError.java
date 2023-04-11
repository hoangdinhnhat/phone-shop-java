/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author Lenovo
 */
public class UserError {
    private String userIDError;
    private String fullNameError;
    private String roleIDError;
    private String emailError;
    private String passwordError;
    private String confirmError;
    private String captchaError;

    public UserError() {
        this.userIDError = "";
        this.fullNameError = "";
        this.roleIDError = "";
        this.emailError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.captchaError = "";
    }

    public UserError(String userIDError, String fullNameError, String roleIDError, String emailError, String passwordError, String confirmError, String captchaError) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.roleIDError = roleIDError;
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.captchaError = captchaError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getCaptchaError() {
        return captchaError;
    }

    public void setCaptchaError(String captchaError) {
        this.captchaError = captchaError;
    }
}
