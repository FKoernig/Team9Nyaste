/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojekt;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author isakj
 */
public class Validator {
    
    private InfDB idb;
    
    public Validator() {
        idb = TestProjekt.getDB();
    }
    
    public boolean validateNewPost(String title, String text, String category) {
        boolean valid = true;
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(null, "F�ltet \"Titel\" m�ste vara ifyllt");
            valid = false;
        } else if (text.length() < 50) {
            JOptionPane.showMessageDialog(null, "Inl�gget �r f�r kort");
            valid = false;
        } else if (text.length() > 2500) {
            JOptionPane.showMessageDialog(null, "Inl�gget f�r inte vara l�ngre �n 2500 tecken");
            valid = false;
        } else if(title.length() > 50) {
            JOptionPane.showMessageDialog(null, "Titeln f�r inte vara l�ngre �n 50 tecken");
        }else if (category.equals("-- Kategori --")) {
            JOptionPane.showMessageDialog(null, "V�nligen v�lj en kategori");
            valid = false;
        }
        return valid;
    }
    
    public static boolean tfIsNotEmpty(JTextField... textFields) {

        // S�tter resultat till sant
        boolean result = true;

        for (JTextField textField : textFields) {
        // If-sats som kontrollerar om rutan �r tom
            if (textField.getText().isEmpty()) {
                result = false;
                break;
            }
        }
        if (!result) {
            JOptionPane.showMessageDialog(null, "En eller flera textrutor �r tomma!");
        }

        // Returnerar resultat
        return result;
    }
    
     public static boolean passwordIsNotEmpty(JPasswordField... passwordFields) {

        // S�tter resultat till sant
        boolean result = true;

        for (JPasswordField passwordField : passwordFields) {
            String password = new String(passwordField.getPassword());
        // If-sats som kontrollerar om rutan �r tom
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "F�ltet 'L�senord' �r tomt");
                passwordField.requestFocus();
                result = false;
                break;
            }
        }
        // Returnerar resultat
        return result;
    }
     
    public boolean validateRegistration(String userName, String email) {
        boolean valid = false;
        try {
            valid = true;
            String query = "SELECT Anvandare.ANVANDAR_NAMN as anvardareNamn, Granskning.ANVANDARNAMN as granskningNamn FROM ANVANDARE, Granskning";
            ArrayList<HashMap<String, String>> results = idb.fetchRows(query);
            if (results != null) {
                for (HashMap<String, String> result : results) {
                    String name = result.get("ANVANDARENAMN");
                    String name2 = result.get("GRANSKNINGNAMN");
                    if (userName.equalsIgnoreCase(name) || userName.equalsIgnoreCase(name2)) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, "Anv�ndarnamnet �r upptaget");
                        break;
                    } 
                }
            }
        } catch(InfException ie) {
            System.out.println(ie.getMessage());
        }
        return valid;
    }
        
    public boolean validateComment(String text) {
        boolean valid = true;
        int length = text.length();
        if(length < 1) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Kommentaren �r f�r kort");
        } else if (length > 200) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Kommentaren �r f�r l�ng");
        }
        return valid;
    }
    
    public boolean validateInvite(String motesID, String anvandarnamn, String anvandarID){
        boolean valid = true;
        String motesAdmin = "";
        try {
            String query = "select count(*) from mote where mid = " + motesID;
            String test = idb.fetchSingle(query);
            if (!idb.fetchSingle(query).equals("0")) {
                motesAdmin = idb.fetchSingle("select anvandar_id from mote where mid =" +  motesID);
                System.out.println("AID: " + anvandarID + ", MotesAdmin: " + motesAdmin);
                if (anvandarID.equals(motesAdmin)) {
                    String query2 = "select count(*) from anvandare where anvandar_namn = '" + anvandarnamn + "'";
                    System.out.println("Test: " + idb.fetchSingle(query2));
                    if (idb.fetchSingle(query2).equals("0")) {
                        JOptionPane.showMessageDialog(null, "Ogiltigt anv�ndarnamn");
                        valid = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Du �r inte administrat�r f�r detta m�te");
                    valid = false;
                }               
            } else {
                JOptionPane.showMessageDialog(null, "Ogiltigt m�tes-id");
                valid = false;
            }
        } catch(InfException ie) {
            System.out.println(ie.getMessage());
        }
        return valid;
    }
}
