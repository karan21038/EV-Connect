package com.example.auth_app.ui.profile;

public class ProfileData {

   public String mfgDate;
    public String model;
    public String regtUpto;

    public String getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(String mfgDatesss) {
        mfgDate = mfgDatesss;
    }

    public String getmodel() {
        return model;
    }

    public void setmodel(String model) {
        model = this.model;
    }

    public String getregtUpto() {
        return regtUpto;
    }

    public void setregtUpto(String regtUpto) {
        regtUpto = this.regtUpto;
    }

    public String getU_FastTagBalance() {
        return u_FastTagBalance;
    }

    public void setU_FastTagBalance(String u_FastTagBalance) {
        this.u_FastTagBalance = u_FastTagBalance;
    }

    public String getU_InsuranceAmount() {
        return u_InsuranceAmount;
    }

    public void setU_InsuranceAmount(String u_InsuranceAmount) {
        this.u_InsuranceAmount = u_InsuranceAmount;
    }

    public String getU_OwnerName() {
        return u_OwnerName;
    }

    public void setU_OwnerName(String u_OwnerName) {
        this.u_OwnerName = u_OwnerName;
    }

    public String getU_PolicyHolder() {
        return u_PolicyHolder;
    }

    public void setU_PolicyHolder(String u_PolicyHolder) {
        this.u_PolicyHolder = u_PolicyHolder;
    }

    public String getU_PremiumDate() {
        return u_PremiumDate;
    }

    public void setU_PremiumDate(String u_PremiumDate) {
        this.u_PremiumDate = u_PremiumDate;
    }

    public String getU_RegistrationNo() {
        return u_RegistrationNo;
    }

    public void setU_RegistrationNo(String u_RegistrationNo) {
        this.u_RegistrationNo = u_RegistrationNo;
    }

    public String getU_RenewalDate() {
        return u_RenewalDate;
    }

    public void setU_RenewalDate(String u_RenewalDate) {
        this.u_RenewalDate = u_RenewalDate;
    }

    public String getU_age() {
        return u_age;
    }

    public void setU_age(String u_age) {
        this.u_age = u_age;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getWallet_amount() {
        return wallet_amount;
    }

    public void setWallet_amount(String wallet_amount) {
        this.wallet_amount = wallet_amount;
    }

    public String u_FastTagBalance;

    public ProfileData(String MfgDate, String Model, String RegtUpto, String u_FastTagBalance, String u_InsuranceAmount, String u_OwnerName, String u_PolicyHolder, String u_PremiumDate, String u_RegistrationNo, String u_RenewalDate, String u_age, String u_email, String u_name, String wallet_amount) {
        this.mfgDate = MfgDate;
        this.model = Model;
        this.regtUpto = RegtUpto;
        this.u_FastTagBalance = u_FastTagBalance;
        this.u_InsuranceAmount = u_InsuranceAmount;
        this.u_OwnerName = u_OwnerName;
        this.u_PolicyHolder = u_PolicyHolder;
        this.u_PremiumDate = u_PremiumDate;
        this.u_RegistrationNo = u_RegistrationNo;
        this.u_RenewalDate = u_RenewalDate;
        this.u_age = u_age;
        this.u_email = u_email;
        this.u_name = u_name;
        this.wallet_amount = wallet_amount;
    }

    public String u_InsuranceAmount;
    public String u_OwnerName;
    public String u_PolicyHolder;
    public String u_PremiumDate;
    public String u_RegistrationNo;
    public String u_RenewalDate;
    public String u_age;
    public String u_email;
    public String u_name;
    public String wallet_amount;

    public ProfileData() {

    }




}
