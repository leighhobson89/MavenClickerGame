package com.fsclicker;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Utils {
    public static void playSound(String fileName) {
        try {
            File f = new File(fileName);
            Media m = new Media(f.toURI().toURL().toString());
            MediaPlayer player = new MediaPlayer(m);
            player.play();
        } catch (Exception e) {
            System.out.println("Cannot load resources: " + e);
        }
    }
}
