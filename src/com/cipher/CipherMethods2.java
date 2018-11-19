/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cipher;

/**
 *
 * @author ziad
 */
public class CipherMethods2 {

    protected static String encrypt(StringBuilder text, final String key) {
        String res = "", Text;
        Text = text.toString();
        Text = Text.toUpperCase();
        for (int i = 0, j = 0; i < Text.length(); i++) {
            char c = Text.charAt(i);
            if (c < 'A' || c > 'Z') {
                continue;
            }
            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        ComCipher.write(res);
        //JOptionPane.showMessageDialog(null, res);
        return res;
    }

    protected static String decrypt(StringBuilder text, final String key) {
        String res = "", Text;
        Text = text.toString();
        Text = Text.toUpperCase();
        for (int i = 0, j = 0; i < Text.length(); i++) {
            char c = Text.charAt(i);
            if (c < 'A' || c > 'Z') {
                continue;
            }
            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        ComCipher.write(res);
      //  JOptionPane.showMessageDialog(null, res);
        return res;
    }

}
