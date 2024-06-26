package it.savoreco.service;

public class PointLevel {
    public static int calcolaLivello(int punti) {
        if (punti > 10) {
            return (int) (Math.log10(punti));
        } else if (punti > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int minLivello(int livello) {
        if (livello > 1) {
            return (int) Math.pow(10, livello - 1) + 1;
        } else if (livello == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int maxLivello(int livello) {
        if (livello > 1) {
            return (int) Math.pow(10, livello) - 1;
        } else if (livello == 1) {
            return 10;
        } else {
            return 0;
        }
    }
}
