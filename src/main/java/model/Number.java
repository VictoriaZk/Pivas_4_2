package model;

public class Number {
    private String accountNumber;
    private String mobilePhoneNumber;
    private String cityPhoneNumber;

    public Number(){}

    public Number(String accountNumber, String mobilePhoneNumber, String cityPhoneNumber) {
        this.accountNumber = accountNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.cityPhoneNumber = cityPhoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getCityPhoneNumber() {
        return cityPhoneNumber;
    }

    public void setCityPhoneNumber(String cityPhoneNumber) {
        this.cityPhoneNumber = cityPhoneNumber;
    }
}
