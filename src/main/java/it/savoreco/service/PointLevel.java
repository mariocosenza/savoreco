package it.savoreco.service;

public class PointLevel {
        public static int calcolaLivello(int punti) {
            if(punti > 0) {
                return (int)(Math.log10(punti));
            } else {
                return 0;
            }
        }
}
