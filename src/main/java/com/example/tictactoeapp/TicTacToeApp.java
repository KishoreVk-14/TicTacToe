package com.example.tictactoeapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApp extends Application {

    private Label playerXScoreLabel,playerYScoreLabel;

    private boolean playerXturn=true;

    private Button buttons[][]=new Button[3][3];

    private int playerXScore=0,playerYScore=0;


    public BorderPane content(){
        BorderPane borderPane=new BorderPane();
        borderPane.setPadding(new Insets(20));
        Label label=new Label("Tic Tac Toe App");
        label.setStyle("-fx-font-size:24pt;-fx-font-weight:bold;");
        borderPane.setTop(label);

        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button=new Button();
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size:24pt;-fx-font-weight:bold;");
                button.setOnAction(actionEvent -> buttonClicked(button));
                buttons[i][j]=button;
                gridPane.add(button,j,i);
            }
        }
        borderPane.setCenter(gridPane);

        HBox scoreBoard=new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLabel=new Label("Player X : 0");
        playerXScoreLabel.setStyle("-fx-font-size:16pt;-fx-font-weight:bold;");
        playerYScoreLabel=new Label("Player Y : 0");
        playerYScoreLabel.setStyle("-fx-font-size:16pt;-fx-font-weight:bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerYScoreLabel);
        borderPane.setBottom(scoreBoard);

        return borderPane;
    }

    public void buttonClicked(Button button){
        if(button.getText().equals("")){
            if(playerXturn){
                button.setText("X");
            }else{
                button.setText("O");
            }
            playerXturn=!playerXturn;

            checkWinner();
        }
        return;

    }

    public void checkWinner(){
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText()) && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()){

                String winner=buttons[row][0].getText();
                showWinner(winner);
                updateScore(winner);
                resetBoard();

            }

        }


        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText()) && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()){

                String winner=buttons[0][col].getText();
                showWinner(winner);
                updateScore(winner);
                resetBoard();

            }

        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[2][0].getText().isEmpty()){

            String winner=buttons[2][0].getText();
            showWinner(winner);
            updateScore(winner);
            resetBoard();
        }

        boolean tie=true;
        for(Button row[]: buttons){
            for(Button button:row){
                if(button.getText().isEmpty()){
                    tie=false;
                    break;
                }
            }

        }
        if(tie){
            showTie();
            resetBoard();
        }

    }

    private  void showWinner(String winner){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Congratulations " + winner +  "Won the game!!!");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private  void showTie(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Match Tie!!!");
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXScore++;
            playerXScoreLabel.setText("Player X :"+playerXScore);
        }else{
            playerYScore++;
            playerYScoreLabel.setText("Player Y :"+playerYScore);
        }

    }


    private void resetBoard(){
        for(Button row[]:buttons){
            for(Button button:row){
                button.setText("");
            }
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(content());
        stage.setTitle("Tic Tac Toe App!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}