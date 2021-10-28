/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_calculadora;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author berme
 */
public class Ejercicio_calculadora  extends JFrame
        implements ActionListener {
// implements ActionListener implica la subscripció als esdeveniments
// sobre el JFrame. L'únic que gestionam són els clicks als botons del teclat

    private JTextField t; //display de la calculadora
    char op = ' '; //operador
    String nump = "", num = ""; //nump significa nombre precedent

    public Ejercicio_calculadora() {
        setTitle("Calculadora Bàsica");
        setSize(500, 500);
        setLocationRelativeTo(null);//La calculadora surt al centre
        setDefaultCloseOperation(Ejercicio_calculadora.EXIT_ON_CLOSE);
        initContents();
    }

    private void initContents() {
//Es definiexen dos panells un pels botons i l'altre per la pantalla
        JPanel jpTeclat = new JPanel();
        jpTeclat.setLayout(new GridLayout(5, 4));
//        jpTeclat.setLayout(new FlowLayout());
        String jlbBotons[] = {"7", "8", "9", "/", "4", "5", "6", "*", "1",
            "2", "3", "-", "0", ".", "=", "+", "C","M+","M-","MR"};
        for (int i = 0; i < jlbBotons.length; i++) {
            JButton boto = new JButton(jlbBotons[i]);
            boto.addActionListener(this);
            boto.setFont(new Font("arial", 0, 50));
            jpTeclat.add(boto);
        }
        t = new JTextField();
        t.setHorizontalAlignment(JTextField.RIGHT);
        t.setEditable(false); //No es pot escriure directament s'han d'usar
        //els botons
        t.setFont(new Font("arial", 2, 25));

        JPanel jpPantalla = (JPanel) getContentPane();

        jpPantalla.setLayout(new BorderLayout());
        jpPantalla.add(t, BorderLayout.NORTH);
        jpPantalla.add(jpTeclat, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent evt) {
        char c = ((JButton) evt.getSource()).getText().charAt(0);
        //getSource retorna un objecte, es fa el casting a botó
        //del botó s'agafa l'string que l'identifica
        //de l'string n'extreu el caràcter

        //Construeix el numero
        if (c >= '0' && c <= '9') {
            if (num.equals("")) {
                t.setText(num);
            }
            num = num + c;
            t.setText(num);
            //afegeix el punt decimal
        } else if (c == '.') {
            if (num.equals("")) {
                num = "0.";
                t.setText(num);
            } else if (!num.contains(".")) {
                num = num + ".";
                t.setText(num);
            }
            //esborra
        } else if (c == 'C') {
            nump = "";
            num = "";
            op = ' ';
            t.setText(num);
            //realitza els càlculs
        } else if (c == '=') {
            calc();
            //nombres negatius
        } else if (c == '-' && (op == ' ') && num.equals("")) {
            num = num + c;
            t.setText(num);
            //operadors
        } else {
            if (op == ' ') {
                op = c;
                if (!num.equals("")) {
                    nump = num;
                }
                num = "";
            } else {
                calc();
                op = c;
            }
        }
        if(Character.toString(c).equals("MR")){
            op='w';
        }
        if(Character.toString(c).equals("M+")){
            
        }
    }

    private void calc() {
        if (!num.equals("") && !nump.equals("")) {
            float a = Float.parseFloat(nump);
            float b = Float.parseFloat(num);
            float r = 0;
            if (op == '+') {
                r = a + b;
            } else if (op == '-') {
                r = a - b;
            } else if (op == '*') {
                r = a * b;
            } else if (op == '/') {
//                r = a / (b != 0 ? b : 1);
                r = a / b;
            }
            else if(op=='w'){
                r=a;
            }
            nump = Float.toString(r);
            t.setText(nump);
        }
        num = "";
    }

    public static void main(String[] args) {
        new Ejercicio_calculadora().setVisible(true);
    }

    
    
}
