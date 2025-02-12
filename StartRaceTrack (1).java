package org.example.assignment3;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartRaceTrack extends Application
{
    //ImageView obCar ;
//    ArrayList<Player> obPlayers = new ArrayList<>();
//    Game obGame;
    CarPane obTrack;
    CarPane obTrack2;

    Timeline obTimeRace;
    Timeline obTimeRace2;

    ControlBox obControl;
    Stage obMainStage;


    MenuItem miStart;
    MenuItem miPause;

    MenuItem miRestart;


    @Override
    public void start(final Stage obStage) throws Exception
    {

        //Race car and Track
        obTrack = new CarPane();

        //Reference Car and Track
        obTrack2 = new CarPane();
        obTrack2.setStyle("-fx-background-color: lightgrey");

        //Set up the reference car's animation
        obTimeRace2 = new Timeline(new KeyFrame(Duration.millis(100), x ->
        {
            obTrack2.move();
        }));
        obTimeRace2.setCycleCount(Timeline.INDEFINITE);
        obTimeRace2.play();

        //Set up the race car's animation
        obTimeRace = new Timeline(new KeyFrame(Duration.millis(100), x -> obTrack.move()));
        obTimeRace.setCycleCount(Timeline.INDEFINITE);
        obTimeRace.play();


        VBox obCarBox = new VBox(40);

        obCarBox.setAlignment(Pos.TOP_CENTER);

        obCarBox.getChildren().addAll(obTrack2, obTrack);
        obCarBox.setPadding(new Insets(40, 0, 0, 0));


        //A border pane, top area for menu bar, bottom for the game control, center for tracks/cars
        BorderPane obPane = new BorderPane(obCarBox);
        obPane.setPadding(new Insets(10,0,10,0));

        obPane.setTop(createMenuBar());

        // Create and add the game control, and the menu bar to the obPane
        obControl = new ControlBox();
        obPane.setBottom(obControl);


        obControl.setOperatorAndNum();

        obControl.setOnKeyPressed(e ->{
            if (e.getCode() == KeyCode.ENTER) {
                obControl.removeLabel();

                if (obControl.checkAnswer()) {
                    //obTrack.setDx();
                    obTrack.speedUp();
                }
                if (!obControl.checkAnswer()) {
                    obTrack.slowDown();
                }
            }

            obControl.setOperatorAndNum();
            obControl.changeTheNumber();
        });


        Scene obScene = new Scene(obPane, 1200, 400);
        //Bind tracks' width with the scene
        obScene.widthProperty().addListener(e -> {
            obTrack.setW(obScene.getWidth());
            obTrack2.setW(obScene.getWidth());
        });

        //Register the listener when the current game is finished
        obTrack.raceFinishedProperty().addListener(e -> {
            obTrack.resetRace();
            obTrack2.resetRace();
            popup(obTrack);
        });

        obTrack2.raceFinishedProperty().addListener(e -> {
            obTrack.resetRace();
            obTrack2.resetRace();
            popup(obTrack2);
        });

        obStage.setScene(obScene);



        obStage.setTitle("Racing in Saskatchewan");

        obMainStage = obStage;

        obMainStage.show();


    }


    private void popup(CarPane car) {
        final Stage dialogStage = new Stage();
        VBox dialogBox = new VBox(40);


        Button continueGame = new Button("continue");
        Button exitGame = new Button("Exit");

        dialogBox.setPadding(new Insets(30, 30, 30, 30));
        if (obTrack ==car) {
            dialogBox.getChildren().add(new Text( "Congrats! " +
                    "You win. "));
        } else {
            dialogBox.getChildren().add(new Text( "Sorry! " +
                    "You loose." +
                    "     Computer Wins "));
        }
        HBox resBox = new HBox(40);
        resBox.getChildren().addAll(continueGame, exitGame);

        dialogBox.getChildren().add(resBox);

        continueGame.setOnAction(e ->{
            obTrack.setDx(1/10);
            obTrack2.setDx(2);
            miStart.setDisable(true);
            miPause.setDisable(false);
            dialogStage.close();

        });

        exitGame.setOnAction(e ->{
            obMainStage.close();
            dialogStage.close();
        });

        Scene dialogScene = new Scene(dialogBox, 350, 200);

        dialogStage.setScene(dialogScene);

        dialogStage.show();


    }

    private MenuBar createMenuBar()
    {
        MenuBar obBar = new MenuBar();

        obBar.getMenus().addAll(createFileMenu(), createHelpMenu());

        return obBar;
    }

    private Menu createFileMenu()
    {
        Menu mnFile = new Menu("File");
        miStart = new MenuItem("Start");

        miPause = new MenuItem("Pause");
        miRestart = new MenuItem("Restart");


        SeparatorMenuItem obSpacer = new SeparatorMenuItem();

        MenuItem miExit = new MenuItem("Exit");

        mnFile.getItems().addAll(miStart, miPause,miRestart, obSpacer, miExit);
        miRestart.setVisible(false);
        miPause.setDisable(true);
        //Set event handler for start menu item
        miStart.setOnAction(e ->{
            obTrack.setDx(1/10);
            obTrack2.setDx(2);
            miStart.setDisable(true);
            miPause.setDisable(false);
        });

        miPause.setOnAction(e ->{
            obTrack.setDx(0);
            obTrack2.setDx(0);
            miPause.setDisable(true);
            miStart.setDisable(true);
            miPause.setVisible(false);
            miRestart.setVisible(true);

        });

        miRestart.setOnAction(e -> {
            obTrack.setDx(1/10);
            obTrack2.setDx(2);
            miStart.setDisable(true);
            miRestart.setDisable(false);
            miPause.setVisible(true);
            miRestart.setVisible(false);
            miPause.setDisable(false);
        });

        miExit.setOnAction(e -> obMainStage.close());
        return mnFile;
    }

    private Menu createHelpMenu()
    {
        Menu mnHelp = new Menu("Help");

        MenuItem miAbout = new MenuItem("About");

        mnHelp.getItems().add(miAbout);

        // Set up the event handler
        miAbout.setOnAction(e ->
        {
            final Stage dialogStage = new Stage();

            dialogStage.setTitle("About the author of the game");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(obMainStage);

            VBox dialogBox = new VBox(40);

            dialogBox.setPadding(new Insets(30, 30, 30, 30));
            dialogBox.getChildren().add( new Text("This game is developed by:\n\n       Mona Salari\n     " +
                    "  SDC Student\n       Saskatchewan Polytechnic\n       SK, Canada"));


            HBox obHBox = new HBox(40);
            Button obButtonClose = new Button("Close");

            obHBox.getChildren().add(obButtonClose);

            dialogBox.getChildren().add(obHBox);

            obButtonClose.setOnAction(t -> dialogStage.close());

            Scene dialogScene = new Scene(dialogBox, 350, 200);

            dialogStage.setScene(dialogScene);

            dialogStage.show();
        });

        return mnHelp;
    }



}
