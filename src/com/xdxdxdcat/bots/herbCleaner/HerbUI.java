package com.xdxdxdcat.bots.herbCleaner;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HerbUI extends Stage {

    public HerbUI(HerbCleaner script)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new herbController(script, this));
            final Parent root = loader.load(HerbCleaner.class.getResourceAsStream("UI.fxml"));
            final Scene scene = new Scene(root);
            setResizable(false);
            setTitle("Herb Cleaner");
            setScene(scene);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        show();
    }
}
