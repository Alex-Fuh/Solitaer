import java.util.Scanner;

public class Spielfeld {
    static Scanner scanner = new Scanner(System.in);
    private int groesse; 
    private char[][] feld;

    public Spielfeld(int groesse) {
        this.groesse = groesse;
        feld = new char[groesse][groesse];
    }

    public void starteSpiel() {
        for (int i = 0; i < groesse; i++) {
            for (int j = 0; j < groesse; j++) {
                feld[i][j] = '#';
            }
        }
        feld[groesse / 2][groesse / 2] = ' ';
        ausgabeSpielfeld();
        while (true) {
            if (!zug()) {
                System.out.println("Keine gültigen Züge mehr. Spiel beendet.");
                break;
            }
        }
    }

    public void ausgabeSpielfeld() {
        System.out.println("Aktuelles Spielfeld:");

        System.out.print("  ");
        for (int j = 0; j < groesse; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < groesse; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < groesse; j++) {
                System.out.print(feld[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean zug() {
        System.out.println("Geben Sie die Koordinaten des Pins ein, den Sie bewegen möchten (z.B. 2 3), zuerst die Zeile wählen:");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        System.out.println("Geben Sie die Koordinaten des Ziels ein (z.B. 2 1), zuerst die Zeile wählen:");
        int zielX = scanner.nextInt();
        int zielY = scanner.nextInt();

        if (!istGueltigerZug(startX, startY, zielX, zielY)) {
            System.out.println("Ungültiger Zug. Bitte erneut versuchen.");
            return true;
        }

        feld[zielX][zielY] = '#';
        feld[startX][startY] = ' ';
        feld[(startX + zielX) / 2][(startY + zielY) / 2] = ' ';

        ausgabeSpielfeld();
        return true;
    }

    private boolean istGueltigerZug(int startX, int startY, int zielX, int zielY) {
        if (!istImFeld(startX, startY) || !istImFeld(zielX, zielY)) {
            return false;
        }

        if (feld[zielX][zielY] != ' ') {
            return false;
        }

        if (feld[startX][startY] != '#') {
            return false;
        }

        int dx = Math.abs(startX - zielX);
        int dy = Math.abs(startY - zielY);

        if ((dx == 2 && dy == 0) || (dx == 0 && dy == 2)) {
            int zwischenX = (startX + zielX) / 2;
            int zwischenY = (startY + zielY) / 2;
            if (feld[zwischenX][zwischenY] == '#') {
                return true;
            }
        }
        return false;
    }

    private boolean istImFeld(int x, int y) {
        return x >= 0 && x < groesse && y >= 0 && y < groesse;
    }
}
