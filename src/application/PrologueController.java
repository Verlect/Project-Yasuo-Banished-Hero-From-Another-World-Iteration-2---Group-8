/*
 * Project Name: Project Yasuo Banished Hero From Another Hero
 * Author: Bismarck Leung, David Tran
 */

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PrologueController extends Application{
	private Main app;
	private int currentText = 0;
	private Stage stage;
	private Pane root;
	private FXMLLoader loader = new FXMLLoader();
	MediaPlayer musicData = new MediaPlayer(new Media(Paths.get("src/sounds/prologue.mp3").toUri().toString()));
	
	@FXML
	private Label dialogueText;
	
	ArrayList<String> dialogue = new ArrayList<String>();
 	

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Initiates the program and starts playing the music
	 * @param app
	 */
	public void linkToApplication(Main app) {
		this.app = app;
		musicData.setCycleCount(MediaPlayer.INDEFINITE);
		musicData.play();
	}
	
	/**
	 * Initiates the dialogue for the scene
	 */
	public void setDialogue() {
		dialogueText.setText("PROLOGUE\n" + app.user.getName() + ", you're an aspiring computer scientist... ");
		dialogue.add("who has been working hard this past week preparing for an upcoming coding entrance exam.");
		dialogue.add("If you pass, you will be enrolled in the most prestigious university; Kinonatsu University of Coding and Technology.");
		dialogue.add("Unfortunately, when the results came back, you find yourself to have failed miserably. ");
		dialogue.add("Encased in an aura of melancholy,you confine yourself in your room, sulking in depression.");
		dialogue.add("As you thought about the transpired events that have led you to this sorrowful state, your mind starts to drift.");
		dialogue.add("As your mind melds with the abyss, you see only darkness in front of you. Until?..");
		dialogue.add("A blast of primordial energy flashes in front of you, so bright that it burned your eyes. ");
		dialogue.add("When you came to, you realized you were no longer in your bedroom? You weren?t too sure where you were at all..");
	}
	
	/**
	 * Check if there is more dialouge, if there is, then display it,
	 * if not, then move onto the next scene
	 * @param event
	 */
	public void next(KeyEvent event) {
		if (currentText < dialogue.size()) {
			dialogueText.setText(dialogue.get(currentText));
			currentText++;
		} else if(currentText >= dialogue.size()) {
			try {
				musicData.pause();
				root = (Pane)loader.load(new FileInputStream("src/fxml/Scene1.FXML"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene1Controller scene1controller = loader.getController();
				scene1controller.linkToApplication(this.app);
				scene1controller.setDialogue();
				
				Scene scene = new Scene(root,1320,703);
				scene.getStylesheets().add(getClass().getResource("Scene1.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
				scene.getRoot().requestFocus();
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
