package com.example.auth_app.ui.profile;

public class ProfileData {

   public String MfgDate;
    public String Model;
    public String RegtUpto;
    public String u_FastTagBalance;

    public ProfileData(String MfgDate, String Model, String RegtUpto, String u_FastTagBalance, String u_InsuranceAmount, String u_OwnerName, String u_PolicyHolder, String u_PremiumDate, String u_RegistrationNo, String u_RenewalDate, String u_age, String u_email, String u_name, String wallet_amount) {
        this.MfgDate = MfgDate;
        this.Model = Model;
        this.RegtUpto = RegtUpto;
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
