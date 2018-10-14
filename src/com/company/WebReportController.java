package com.company;


import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.WritableImage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class WebReportController {
    @FXML
    WebView view;
    @FXML
    WebEngine engine;
    public static ObservableList<Point> observableListF, observableListG;
    public static double maxFunc;
    public static LineChart<Number, Number> lineChart;
    private static double a, b;

    public static void returnValues(ObservableList<Point> obsF, ObservableList<Point> obsG, double Maximum, LineChart<Number, Number> numberLineChart, double Left, double Right) {
        observableListF = obsF;
        observableListG = obsG;
        maxFunc = Maximum;
        lineChart = numberLineChart;
        a = Left;
        b = Right;
    }
    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("HTML-файлы (*.html)", "*.html"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Все файли (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }
    public WritableImage asPng(LineChart<Number,Number> lineChart, String path) {
        WritableImage image = lineChart.snapshot(new SnapshotParameters(), null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void initialize() {
        File file = new File("D:\\Курсовая(2)\\reportOfprogram.html");
        engine = view.getEngine();
        try {
            engine.load(file.toURI().toURL().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void saveMyReport() {
        FileChooser fileChooser = getFileChooser("Save HTML-file");
        File file;
        if ((file = fileChooser.showSaveDialog(null))!=null){
            try {
                new Function_F_x().saveReport(file.getName(),observableListF,observableListG,a,b,maxFunc,
                        asPng(lineChart,"D:\\Курсовая(2)\\mySchedule.png"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }
}
