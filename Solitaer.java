public class Solitaer {

    public static void main(String[] args) {
        Spielfeld spielfeld = new Spielfeld(5);
        spielfeld.starteSpiel();
        Spielfeld.scanner.close();
    }
}
