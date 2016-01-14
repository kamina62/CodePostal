/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import GestionCodePostal.CodePostal;
import java.util.List;

/**
 *
 * @author bastien
 */
public class Test {

    public static void main(String[] args) {
        CodePostal codePostal = new CodePostal("62160");
        
        System.out.println("Ville recupérer en tableau");
        String[] lesVillesArray = codePostal.getLesVillesArray();
        for (String lesVilles : lesVillesArray) {
            System.out.println(lesVilles);
        }

        System.out.println("ville recupérer en liste");
        List<String> lesVillesList = codePostal.getLesVillesList();
        for (String lesVilles : lesVillesList) {
            System.out.println(lesVilles);
        }
    }
}
