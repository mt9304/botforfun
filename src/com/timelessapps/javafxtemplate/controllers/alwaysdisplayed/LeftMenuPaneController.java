/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelessapps.javafxtemplate.controllers.alwaysdisplayed;

import com.timelessapps.javafxtemplate.Main;
import com.timelessapps.javafxtemplate.services.SceneHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LeftMenuPaneController implements Initializable
{
    Stage stage = Main.getMainStage();
    SceneHelper sceneHelper = new SceneHelper();
    
    @FXML
    private Button homeButton, applicationButton, apiDatabaseButton, _generalButton, logsButton;

    @FXML
    private void highlightButton(MouseEvent event)
    {
        String nodeID = sceneHelper.getSourceID(event.getSource());
        Node eventNode = sceneHelper.getNodeById(nodeID);
        eventNode.setStyle("-fx-background-color: #555764;");
    }
    
    @FXML
    private void unHighlightButton(MouseEvent event)
    {
        String nodeId = sceneHelper.getSourceID(event.getSource());
        Node eventNode = sceneHelper.getNodeById(nodeId);
        eventNode.setStyle("-fx-background-color:  #3A3C43;");
    }
    
    @FXML
    private void menuButtonClicked(MouseEvent event)
    {
        String buttonName = sceneHelper.getSourceName(event.getSource());
        //String nodeId = sceneHelper.getSourceID(event.getSource());
        //Label pageNameLabel = sceneHelper.getLabelById(nodeId);
        sceneHelper.changeLabelName("pageNameLabel", buttonName);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
}
