/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBcontext;

import java.util.Random;

/**
 *
 * @author Acer
 */
public class Capcha {
        public String createCapcha() {
        String capcha = "";
        for (int i = 1; i <=6; i++) {
            Random r = new Random();
            capcha+=r.nextInt(10);
        }
        return capcha;
    }
}
