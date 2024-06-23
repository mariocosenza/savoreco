package it.savoreco.service;

public class PointLevel {
        public static int calcolaLivello(int punti) {
            if(punti > 10) {
                return (int)(Math.log10(punti));
            } else if(punti > 0) {
                return 1;
            } else {
                return 0;
            }
        }
}
