import java.awt.*;
import javax.swing.*;

public class Tris extends JFrame {
    private final JButton[][] celle = new JButton[3][3];
    private final JLabel stato = new JLabel("Turno: X", SwingConstants.CENTER);
    private char giocatoreCorrente = 'X';
    private boolean partitaFinita = false;

    public Tris() {
        setTitle("Tris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(420, 500);
        setLocationRelativeTo(null); // centra la finestra sullo schermo
        setLayout(new BorderLayout(10, 10)); // imposta un layout BorderLayout con 10px di spazio tra i componenti

        stato.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(stato, BorderLayout.NORTH);

        JPanel griglia = new JPanel(new GridLayout(3, 3, 8, 8));
        griglia.setBackground(new Color(230, 235, 240));

        Font fontCella = new Font("SansSerif", Font.BOLD, 56);
        for (int riga = 0; riga < 3; riga++) {
            for (int colonna = 0; colonna < 3; colonna++) {
                JButton cella = new JButton("");
                cella.setFont(fontCella);
                cella.setFocusPainted(false);

                final int rr = riga;
                final int cc = colonna;
                cella.addActionListener(e -> gioca(rr, cc));

                celle[riga][colonna] = cella;
                griglia.add(cella);
            }
        }

        add(griglia, BorderLayout.CENTER);
    }

    private void gioca(int riga, int colonna) {
        if (partitaFinita || !celle[riga][colonna].getText().isEmpty()) {
            return;
        }

        celle[riga][colonna].setText(String.valueOf(giocatoreCorrente));

        if (haVinto(giocatoreCorrente)) {
            stato.setText("Ha vinto: " + giocatoreCorrente);
            partitaFinita = true;
            return;
        }

        if (tabellonePieno()) {
            stato.setText("Pareggio!");
            partitaFinita = true;
            return;
        }

        giocatoreCorrente = (giocatoreCorrente == 'X') ? 'O' : 'X';
        stato.setText("Turno: " + giocatoreCorrente);
    }

    private boolean haVinto(char simbolo) {
        String s = String.valueOf(simbolo);

        for (int i = 0; i < 3; i++) {
            if (celle[i][0].getText().equals(s) && celle[i][1].getText().equals(s) && celle[i][2].getText().equals(s)) {
                return true;
            }
            if (celle[0][i].getText().equals(s) && celle[1][i].getText().equals(s) && celle[2][i].getText().equals(s)) {
                return true;
            }
        }

        return (celle[0][0].getText().equals(s) && celle[1][1].getText().equals(s) && celle[2][2].getText().equals(s))
                || (celle[0][2].getText().equals(s) && celle[1][1].getText().equals(s) && celle[2][0].getText().equals(s));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // SwingUtilities → classe utility di Swing, invokeLater(...) → dice: “esegui questo codice appena possibile nel thread grafico", () -> { ... } → è una lambda (funzione anonima) che contiene il codice da eseguire
            Tris finestra = new Tris();
            finestra.setVisible(true);
        });
    }
}
