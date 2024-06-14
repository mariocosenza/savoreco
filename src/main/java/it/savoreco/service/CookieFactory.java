package it.savoreco.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;


public class CookieFactory {
    private static Cookie makeCookie(String name, String value) {
        var cookie = new Cookie(name, value);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setAttribute("SameSite", "strict");
        cookie.setMaxAge(60 * 60 * 24); //24h
        return cookie;
    }
    public static Cookie newUserCookie(HttpSession session) {
       return makeCookie("user", session.getId());
    }

    public static Cookie newSellerCookie(HttpSession session) {
        return makeCookie("seller", session.getId());
    }

    public static Cookie newModeratorCookie(HttpSession session) {
        return makeCookie("moderator", session.getId());
    }
}
