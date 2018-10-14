package com.company;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import sun.plugin2.message.Message;


import javax.imageio.ImageIO;
import java.awt.geom.Arc2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class Controller {
    private double maximum;
    private File f1, f2;
    Pattern pattern = Pattern.compile("\\-?(\\d+\\.?\\d*)?");
    private DichotomyMethod dich = new DichotomyMethod();
    @FXML
    private TableView firstColumn, secondColumn;
    ObservableList<Point> observableListF, observableListG;
    @FXML
    private TableColumn<Point, Double> firstTableX, firstTableY, secondTableX, secondTableY;
    ;
    @FXML
    private LineChart<Number, Number> lineChart;
    XYChart.Series seriesF, seriesG, seriesResult;
    XYChart.Series<Number, Number> seriesMax, seriesFPoints, seriesGPoints;
    @FXML
    private TextField fieldB, fildEps, fieldMax, fieldA;
    @FXML
    NumberAxis numXAxis, numYAxis;
    private static boolean doSaved = true;

    @FXML
    void doOpen_F() {
        FileChooser fileChoos = getFileChooser("Open XML-File");
        if ((f1 = fileChoos.showOpenDialog(null)) != null) {
            updatefirstTable();
        }
    }

    @FXML
    void doOpen_G(ActionEvent event) {
        FileChooser fileChoos = getFileChooser("Open XML-File");
        if ((f2 = fileChoos.showOpenDialog(null)) != null) {
            updatesecondTable();
        }
    }

    private void updatefirstTable() {
        doSaved = false;
//       /* if (observableListF == null) {
//            List<Point> list = new ArrayList<Point>();
//            observableListF = FXCollections.observableArrayList(list);
//            observableListF.add(new Point(0.0, 0.0));
//            firstColumn.setItems(observableListG);
//       */
        Function_F_XML F = new Function_F_XML();
        Function_F_x func1 = F.fromXmlToObject(f1.getName());
        List<Point> list = new ArrayList<Point>();
        observableListF = FXCollections.observableList(list);
        for (int i = 0; i < func1.amount(); i++) {
            Point point = new Point(func1.getAx()[i], func1.getAy()[i]);
            list.add(point);
            firstColumn.setItems(observableListF);
            firstTableX.setCellValueFactory(new PropertyValueFactory<>("x"));
            firstTableX.setCellFactory(
                    NewTextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
            firstTableX.setOnEditCommit(k -> updateX(k));
            firstTableY.setCellValueFactory(new PropertyValueFactory<>("y"));
            firstTableY.setCellFactory(
                    NewTextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
            firstTableY.setOnEditCommit(k -> updateY(k));
       /* } else

        {
            observableListF.add(new Point(0.0, 0.0));
        }*/
        }
    }


    private void updatesecondTable() {
        doSaved = false;
       /* if (observableListG == null) {
            List<Point> list = new ArrayList<Point>();
            observableListG = FXCollections.observableArrayList(list);
            observableListG.add(new Point(0.0, 0.0));
            secondColumn.setItems(observableListG);*/
        Function_G_XML G = new Function_G_XML();
        Function_G_x func2 = G.fromXmlToObj(f2.getName());
        List<Point> list = new ArrayList<Point>();
        observableListG = FXCollections.observableList(list);
        for (int i = 0; i < func2.amount(); i++) {
            Point point = new Point(func2.getAx()[i], func2.getAy()[i]);
            list.add(point);
            secondColumn.setItems(observableListG);
            secondTableX.setCellValueFactory(new PropertyValueFactory<>("x"));
            secondTableX.setCellFactory(
                    NewTextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
            secondTableX.setOnEditCommit(k -> updateY(k));
            secondTableY.setCellValueFactory(new PropertyValueFactory<>("y"));
            secondTableY.setCellFactory(
                    NewTextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
            secondTableY.setOnEditCommit(k -> updateY(k));
        /*} else {
            observableListG.add(new Point(0.0, 0.0));
        }*/
        }
    }

    private void updateY(TableColumn.CellEditEvent<Point, Double> k) {
        doSaved = false;
        Point p = (Point) k.getTableView().getItems().get(k.getTablePosition().getRow());
        p.setY(k.getNewValue());
    }

    private void updateX(TableColumn.CellEditEvent<Point, Double> k) {
        doSaved = false;
        Point p = (Point) k.getTableView().getItems().get(k.getTablePosition().getRow());
        p.setX(k.getNewValue());
    }

    private static FileChooser getFileChooser(String s) {
        FileChooser fileChoos = new FileChooser();
        fileChoos.setInitialDirectory(new File("."));
        fileChoos.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML-File(*.xml)", "*.xml"));
        fileChoos.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Все файли (*.*)", "*.*"));
        fileChoos.setTitle(s);
        return fileChoos;
    }

    @FXML
    void addFunc_F() {
        doSaved = false;
        if (observableListF == null) {
            List<Point> list = new ArrayList<Point>();
            observableListF = FXCollections.observableList(list);
            observableListF.add(new Point(0.0, 0.0));
            firstColumn.setItems(observableListF);
            firstTableX.setCellValueFactory(new PropertyValueFactory<>("x"));
            firstTableX.setCellFactory(
                    NewTextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
            firstTableX.setOnEditCommit(k -> updateX(k));
            firstTableY.setCellValueFactory(new PropertyValueFactory<>("y"));
            firstTableY.setCellFactory(
                    NewTextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
            firstTableY.setOnEditCommit(k -> updateY(k));
        } else {
            observableListF.add(new Point(0.0, 0.0));
        }
    }

    @FXML
    void addFunc_G() {
        doSaved = false;
        if (observableListG == null) {
            List<Point> list = new ArrayList<Point>();
            observableListG = FXCollections.observableList(list);
            observableListG.add(new Point(0.0, 0.0));
            secondColumn.setItems(observableListG);
            secondTableX.setCellValueFactory(new PropertyValueFactory<>("x"));
            secondTableX.setCellFactory(
                    NewTextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
            secondTableX.setOnEditCommit(k -> updateX(k));
            secondTableY.setCellValueFactory(new PropertyValueFactory<>("y"));
            secondTableY.setCellFactory(
                    NewTextFieldTableCell.<Point, Double>forTableColumn(new DoubleStringConverter()));
            secondTableY.setOnEditCommit(k -> updateY(k));
        } else {
            observableListG.add(new Point(0.0, 0.0));
        }
    }

    @FXML
    void doDelete_F() {
        if (observableListF == null) {
            return;
        }
        if (observableListF.size() <= 0) {
            observableListF = null;
        }
        if (observableListF.size() > 0) {
            observableListF.remove(observableListF.size() - 1);
        }
    }

    @FXML
    void doDelete_G() {
        if (observableListG == null) {
            return;
        }
        if (observableListG.size() > 0) {
            observableListG.remove(observableListG.size() - 1);
        }
        if (observableListG.size() <= 0) {
            observableListG = null;
        }
    }

    @FXML
    private void doAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About the program...");
        alert.setHeaderText(
                "-------------Development of the application of the graphite interface of the corrosion agent--------\n" +
                        "---------------------for the numerical value of the minimum by the method of dichotomy--------------\n" +
                        "--------------------------------Completed student of the group KH-36E-------------------------------\n" +
                        "------- --------------------------------Shestopalov Vladislav---------------------------------------");
        alert.setContentText("Version 1.0");
        alert.showAndWait();
      /*  Stage stage = new Stage();
        AnchorPane layout;
        stage.setTitle("About the program");
        try {
            FXMLLoader root = FXMLLoader.load(getClass().getResource("about.fxml"));
            layout = (AnchorPane) root.load();
            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    private void BuildFunc() {
        if (!lineChart.getData().isEmpty()) {
            clear();
        }
        try {
            if (fieldA.getText().isEmpty() || fieldB.getText().isEmpty() || fildEps.getText().isEmpty() || fildEps.getText().isEmpty() || observableListG.isEmpty() || observableListF.isEmpty()) {
                throw new Exception();
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("You did not specify the \n" +
                    " boundaries or accuracy of the calculation or the list of points is empty! ");
            alert.showAndWait();
            return;
        }
        seriesF = new XYChart.Series();
        seriesG = new XYChart.Series();
        seriesResult = new XYChart.Series();
        double a, b, eps;
        a = Double.parseDouble(fieldA.getText());
        b = Double.parseDouble(fieldB.getText());
        eps = Double.parseDouble(fildEps.getText());

        try {
            if (a >= b) {
                throw new ClassOfExeption(a, b);
            }
        } catch (ClassOfExeption coe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong data(boundaries)" + "\n please try again");
            alert.showAndWait();
        }
        if (eps < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Wrong data (eps)!\n" +
                    "\nPleas try again.");
            alert.showAndWait();
            return;
        }
        //   Function_F_XML F = new Function_F_XML();
        Function_F_x func1 = new Function_F_x();//F.fromXmlToObject(f1.getName());
        for (int i = 0; i < observableListF.size(); i++) {
            func1.addingPoints(firstTableX.getCellData(i), firstTableY.getCellData(i));
        }
        try {
            CopyingX.wrongValue(func1);
        } catch (CopyingX ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!!");
            alert.setHeaderText("The repeating points of X are in the first function!" + "Pleas enter data again");
            alert.showAndWait();
            return;
        }
        seriesF.setName("Function_F(x)");
        for (double x = (int) a; x <= (int) b; x += 0.1) {
            seriesF.getData().add(new XYChart.Data(x, func1.y(x)));
        }
        // Function_G_XML G = new Function_G_XML();
        Function_G_x func2 = new Function_G_x();//G.fromXmlToObj(f2.getName());
        for (int i = 0; i < observableListG.size(); i++) {
            func2.addingPoints(secondTableX.getCellData(i), secondTableY.getCellData(i));
        }
        try {
            CopyingX.wrongValue(func2);
        } catch (CopyingX ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!!");
            alert.setHeaderText("The repeating second of X are in the first function!" + "Pleas enter data again");
            alert.showAndWait();
            return;
        }

        seriesG.setName("Function_G(x)");
        for (double x = (int) a; x <= (int) b; x += 0.1) {
            seriesG.getData().add(new XYChart.Data(x, func2.y(x)));
        }

        seriesResult.setName("Result:F(x)-G(x)");
        for (double x = (int) a; x <= (int) b; x += 0.1) {
            seriesResult.getData().add(new XYChart.Data(x, func1.y(x) - func2.y(x)));
        }
        seriesFPoints = new XYChart.Series<>();
        seriesGPoints = new XYChart.Series<>();

        for (int i = 0; i < observableListF.size(); i++) {
            if (observableListF.get(i).getX() >= a && observableListF.get(i).getX() <= b)
                seriesFPoints.getData().add(new XYChart.Data<>(observableListF.get(i).getX(), observableListF.get(i).getY()));
        }

        for (int i = 0; i < observableListG.size(); i++) {
            if (observableListG.get(i).getX() >= a && observableListG.get(i).getX() <= b)
                seriesGPoints.getData().add(new XYChart.Data<>(observableListG.get(i).getX(), observableListG.get(i).getY()));
        }

        lineChart.getData().addAll(seriesF, seriesG, seriesResult);
        maxF();
        lineChart.getData().addAll(seriesFPoints, seriesGPoints);
    }

    public void initialize() {
      //  firstColumn.setPlaceholder(new Label());
      //  secondColumn.setPlaceholder(new Label());
        fieldMax.setEditable(false);
        fieldA.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!pattern.matcher(newValue).matches())
                fieldA.setText(oldValue);
        });
        fieldB.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!pattern.matcher(newValue).matches())
                fieldB.setText(oldValue);
        });
        fildEps.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!pattern.matcher(newValue).matches())
                fildEps.setText(oldValue);
        });
    }

    @FXML
    private void clear() {

        lineChart.getData().removeAll(seriesF, seriesG, seriesResult, seriesFPoints, seriesGPoints, seriesMax);
        fieldMax.setText("");
    }

    @FXML
    private void doSave() {
        try {
            Function_F_x function_f_x = new Function_F_x();
            Function_G_x function_g_x = new Function_G_x();
            for (int i = 0; i < observableListF.size(); i++) {
                function_f_x.addingPoints(firstTableX.getCellData(i), firstTableY.getCellData(i));
            }
            for (int i = 0; i < observableListG.size(); i++) {
                function_g_x.addingPoints(secondTableX.getCellData(i), secondTableY.getCellData(i));
            }
            new Function_F_XML().convObjXml(function_f_x, f1.getCanonicalPath());
            new Function_G_XML().convObjXml(function_g_x, f2.getCanonicalPath());
//        Function_F_XML fxml = new Function_F_XML();
//        Function_G_XML fxml_1 =new Function_G_XML();
//            fxml.convObjXml(function_f_x,f1.getCanonicalPath());
//            fxml_1.convObjXml(function_g_x,f2.getCanonicalPath());
            Massege("The result in the file has been saved successfully\n");
        } catch (Exception e) {
            Error("Error,the file was not saved in the file");
        }
    }

    @FXML
    void doClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save your project");
        alert.setHeaderText("Do you want to save ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (result.get() == ButtonType.OK) {
                doSave();
                Platform.exit();
            }
        }
        if (result.get() == ButtonType.CANCEL) {
            Platform.exit();
        }
    }

    @FXML
    void doNew() {
        if (!lineChart.getData().isEmpty()) {
            seriesF.setName(" ");
            seriesG.setName(" ");
            seriesResult.setName(" ");
            lineChart.getData().removeAll(seriesF, seriesG, seriesResult, seriesFPoints, seriesGPoints, seriesMax);
            fieldB.clear();
            fieldA.clear();
            fildEps.clear();
        }
        fieldMax.setText(" ");
        fieldA.setText(" ");
        fieldB.setText(" ");
        observableListF = null;
        observableListG = null;
        firstColumn.setItems(null);
        secondColumn.setItems(null);
        firstColumn.setPlaceholder(new Label(""));
        secondColumn.setPlaceholder(new Label(""));
        f1 = null;
        f2 = null;
    }

    @FXML
    void maxFunc() {
        String maxim = String.valueOf(maximum);
        fieldMax.setText(maxim);
    }

    private void maxF() {
        try {
            if (f1 == null || f2 == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Tatile is empty");
            alert.showAndWait();
            return;
        }
        Function_F_XML F = new Function_F_XML();
        Function_F_x unmarshF = F.fromXmlToObject(f1.getName());
        Function_G_XML G = new Function_G_XML();
        Function_G_x unmarshG = G.fromXmlToObj(f2.getName());
        dich.setBothFunctions(unmarshF, unmarshG);
        try {
            if (fieldA.getText().isEmpty() || fieldB.getText().isEmpty() || fildEps.getText().isEmpty()) {
                throw new Exception();
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("You didn't specify  boundaries or accuracy of calculation!");
            alert.showAndWait();
            return;
        }
        try {
            double dichMeth = dich.dichotomy(Double.parseDouble(fieldA.getText()), Double.parseDouble(fieldB.getText()), Double.parseDouble(fildEps.getText()));
            maximum = dichMeth;
            maximumSeries(dich.getX(), maximum);
        } catch (ClassOfExeption coe) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Incorrect interval boundaries!" + "\nYou must set the interval boundaries again!");
            alert.showAndWait();
        }
    }

    public void maximumSeries(Double x, Double y) {
        seriesMax = new XYChart.Series<>();
        seriesMax.setName("max");
        seriesMax.getData().add(new XYChart.Data<>(x, y));
        lineChart.getData().add(seriesMax);
    }

    @FXML
    private void Massege(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(mess);
        alert.showAndWait();

    }

    @FXML
    private void Error(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(error);
        alert.showAndWait();
    }

    @FXML
    void doHelpProg() {
        Stage stage = new Stage();
        stage.setResizable(false);
        AnchorPane rootLayout;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("WorkProgram.fxml"));
            stage.setTitle("Function Graphs");
            rootLayout = (AnchorPane) loader.load();
            rootLayout.setPrefSize(700, 550);
            stage.setScene(new Scene(new ScrollPane(rootLayout)));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public WritableImage AsPng(LineChart<Number, Number> lineChart, String path) {
        WritableImage image = lineChart.snapshot(new SnapshotParameters(), null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void Myreport() {
        WebReportController.returnValues(observableListF, observableListG, maximum, lineChart, Double.parseDouble(fieldA.getText()), Double.parseDouble(fieldB.getText()));
        try {
            new Function_F_x().saveReport("D:\\Курсовая(2)\\reportOfprogram.html", observableListF, observableListG, Double.parseDouble(fieldA.getText()), Double.parseDouble(fieldB.getText()),
                    maximum, AsPng(lineChart, "D:\\Курсовая(2)\\mySchedule.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void myReport(ActionEvent event) throws IOException {
        if (lineChart.getData().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setHeaderText("Missing required data\n");
            alert.showAndWait();
        } else {
            Myreport();
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("reportHTML.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Report on work");
            stage.show();
        }
    }
}