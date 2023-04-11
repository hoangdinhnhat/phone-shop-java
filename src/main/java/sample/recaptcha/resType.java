/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.recaptcha;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class resType {
    private boolean success;
    private Date challenge_ts;
    private String apk_package_name;

    public resType() {
    }

    public resType(boolean success, Date challenge_ts, String apk_package_name) {
        this.success = success;
        this.challenge_ts = challenge_ts;
        this.apk_package_name = apk_package_name;
    }

    public boolean isSuccess() {
        return success;
    }

    public Date getChallenge_ts() {
        return challenge_ts;
    }

    public String getApk_package_name() {
        return apk_package_name;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setChallenge_ts(Date challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public void setApk_package_name(String apk_package_name) {
        this.apk_package_name = apk_package_name;
    }
}
