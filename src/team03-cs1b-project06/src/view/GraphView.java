package view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;

import model.Country;
import model.DataModel;
import model.IndicatorType;
import model.LinkedList;

import java.util.Iterator;
import java.util.Scanner;

/**
 * The GraphView class extends the parameterized LineChart
 * from javafx.scene package. This class will create a data
 * series for each Country object
 */
public class GraphView extends LineChart<Number, Number>
{
    // Years
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
        super(
                new NumberAxis(model.getModel()[0].getStartYear(), model.getModel()[0].getEndYear(),10), //model.getModel()[0].getStartYear(), model.getModel()[0].getEndYear(),57
                new NumberAxis()
        );
        this.model = model;
        xAxis = (NumberAxis)getXAxis();
        yAxis = (NumberAxis)getYAxis();
        xAxis.setLabel(this.model.getXAxisName());
        xAxis.setForceZeroInRange(false);
        yAxis.setLabel(this.model.getYAxisName());

        ScrollPane scrollPane = new ScrollPane(getLegend());
        scrollPane.setStyle("-fx-border-color: black");
        //scrollPane.setFitToWidth(false);
        this.setLegend(scrollPane);
        this.setLegendVisible(true);
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
    public Series<Number, Number> seriesFromCountry(Country object)
    {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(object.getName());

        int j = 0;

        if (model.getIndicatorType() == IndicatorType.SCHOOL_ENROLLMENT_SECONDARY){
            j = 1;
        }

        // Populating the series with data
        for(int i = object.getStartYear(); i <= object.getEndYear(); i++)
        {
            series.getData().add(new XYChart.Data<Number, Number>(i, object.getIndicatorForYear(i).getData()[j]));
        }

        return series;
    }

    /**
     * The method goes through the list of Country objects and
     * creates a series from each element.
     */
    public void update()
    {
        XYChart.Series<Number, Number> someSeries;// = new XYChart.Series<>();
        Country country[] = model.getModel();
        //CountrySelector countrySelector = new CountrySelector(country, 10);
        LinkedList<Country> countryLinkedList = createRandomListOfCountries(country);

        Iterator<Country> testItr = countryLinkedList.iterator();
        Country aCountry = testItr.next();
        int count = 0;

        while(testItr.hasNext())
        {
            country[count] = (Country)aCountry;
            someSeries = seriesFromCountry(aCountry);
            aCountry = testItr.next();
            this.getData().add(someSeries);
            count++;
        }
        country[count] = (Country)aCountry;
        someSeries = seriesFromCountry(aCountry);
        this.getData().add(someSeries);
    }

    private static LinkedList<Country> createRandomListOfCountries(Country[] allCountries)
    {
        Scanner keyboard = new Scanner(System.in);
        int requestedSize;

        System.out.println("How many countries do you want to add to the list?");
        requestedSize = Integer.parseInt(keyboard.nextLine());
        CountrySelector randomCountries = new CountrySelector(allCountries, requestedSize);
        LinkedList<Country> selectedCountries = randomCountries.selectCountries();

        System.out.println(selectedCountries.toString());
        System.out.println("Done with creating random linked list of countries.\n");
        return selectedCountries;
    }
}
