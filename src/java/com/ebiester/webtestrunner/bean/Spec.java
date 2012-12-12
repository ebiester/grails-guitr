package com.ebiester.webtestrunner.bean;

public class Spec {
    private String spec;
    private String message;
    private Boolean isChecked;
    private Boolean isRun;
    private Boolean isSuccess;
    private Boolean isFailure;
    private Boolean isError;

    /**This constructor is used when creating a Spec that has already been run. Thus,
      Checked will always be true.
     */
    public Spec(String spec, Boolean run, Boolean success, Boolean failure, Boolean error) {
        this.spec = spec;
        this.message = message;
        this.isChecked = true;
        this.isRun = run;
        this.isSuccess = success;
        this.isFailure = failure;
        this.isError = error;
    }

    /*This constructor is used when creating a Spec that has not been run. Thus,
     Checked will always be false and message will always be blank.
    */
    public Spec(String spec, Boolean isChecked) {
        this.spec = spec;
        this.isChecked = false;
        this.isRun = false;
        this.isSuccess = false;
        this.isFailure = false;
        this.isError = false;
        this.message = "";
    }

    public Boolean getRun() {
        return isRun;
    }

    public void setRun(Boolean run) {
        isRun = run;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Boolean getFailure() {
        return isFailure;
    }

    public void setFailure(Boolean failure) {
        isFailure = failure;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        this.isChecked = checked;
    }
}
