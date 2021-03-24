package com.moeny.ussd.util;

public class Util {

    private Util() {

    }

    public static String convertAmount (String country, double amount) {

        String totalAmount = "";
        if (Country.KENYA.getDescription().equalsIgnoreCase(country)) {

            totalAmount = (amount * 6.10) + " KES";
        } else if (Country.MALAWI.getDescription().equalsIgnoreCase(country)) {

            totalAmount = amount * 42.50 + " MWK";
        }
        return totalAmount;
    }

    public static String getCountry(String countryId) {

        String country = null;
        if (countryId.equalsIgnoreCase("1")) {
            country = Country.KENYA.getDescription();
        } else if (countryId.equalsIgnoreCase("2")) {
            country = Country.MALAWI.getDescription();
        }
        return country;
    }
}
