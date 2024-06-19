package it.savoreco.service;

import jakarta.servlet.http.Cookie;


public class CookieFactory {
    public static Cookie makeCookie(String name, String value) {
        var cookie = new Cookie(name, value);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setAttribute("SameSite", "strict");
        cookie.setMaxAge(60 * 60 * 24); //24h
        return cookie;
    }

}
