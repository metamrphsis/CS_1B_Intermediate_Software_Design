package view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.Country;
import model.DataModel;

/**
 * The GraphView class extends the parameterized LineChart
 * from javafx.scene package. This class will create a data
 * series for each Country object
 */
public class GraphView extends LineChart
{
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private DataModel model;

    /**
     * The constructor initializes the axis and data model
     * of the current instance. Receives one argument
     * @param model A DataModel object and sets it to the
     *              model instance variable
     */
    public GraphView(DataModel model)
    {
        super(new NumberAxis(), new NumberAxis());
        this.model = model;
    }

    /**
     * The method creates a Series object with all the years and
     * corresponding data of the current Country object.
     * Receives one argument. Returns a Series<Number, Number> object.
     * The Series should be based on the Indicator data of the
     * Country object.
     * @param object    A Country object whose data is to be converted
     *                  to a series
     * @return  A Series<Number, Number> object
     */
    public Country seriesFromCounry(Country object)
    {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(object.getName());
        series.getData().add(new XYChart.Data<>(object.getStartYear(), object.getEndYear()));
    }

    /**
     * The method goes through the list of Country objects and
     * creates a series from each element.
     */
    public void update()
    {
        XYChart.Series<Number, Number> someSeries = new XYChart.Series<>();
        this.getData().add(someSeries);
    }
}
