package week09_part01.practice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;final

/**
 * Creates a LineChart for Foothill College enrollment history.
 * @author Foothill College, Bita M.
 */
public class ChartEnrollmentHistory extends Application
{
    @Override
    public void start(Stage stage)
    {
        FoothillCollege fc = new FoothillCollege();
        stage.setTitle("Enrollment History");

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        xAxis.setForceZeroInRange(false);
        yAxis.setLabel("Enrollment Count");

        // TODO: Complete the definition of LineChart object as follows:
        //       - Create a new object of type LineChart with a Number x-axis and y-axis
        //       - Set the title of the series to be descriptive based on
        //         the data (see FoothillCollege class)
        //       - Define an XYChart.Series
        //       - Populate the series with data from the FoothillCollege instance fc
        //       - add the series to the LineChart object
        //       - add the LineChart object to the Scene graph

        //LineChart<Number, Number> lineChart = null;
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Foothill College Enrollment Data");

        //Defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Data");

        //Populating the series
        for(FoothillCollege.EnrollmentYear year : fc.getEnrollmentHistory())
        {
            series.getData().add(new XYChart.Data<Number, Number>(year.getYear(), year.getEnrollment()));
        }


        // TODO: Add the lineChart object to the scene.
        //Adding the series to the LineChart object
        Scene scene = new Scene(lineChart, 1000, 700);
        lineChart.getData().add(series);
        //Scene scene = null;

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}