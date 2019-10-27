package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataModel;
import model.IndicatorType;

import java.util.Scanner;

/**
 * Instantiates a JavaFX application which creates a model of the data.
 * Uses the model to instantiate an object of type  javafx.scene.chart.LineChart
 * via the GraphView class. Then sets up the scene with basic modification to
 * the stage.
 *
 * @author Foothill College, Bita Mazloom, Selahittin Saytaş
 */
public class ChartGraph extends Application
{
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.INVALID_INPUT;
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.GDP_PER_CAPITA;
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.SCHOOL_ENROLLMENT;

	// Work on the attributes of FEMALE_POPULATION data
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.FEMALE_POPULATION;
	public static Scanner keyboard = new Scanner(System.in);


	/**
	 * Called by launch method of Application
	 * @param stage: Stage
	 */
	@Override
	public void start(Stage stage)
	{
		// NOTE: Make sure to use relative path instead of specifying the entire path
		//       such as (/Users/alicew/myworkspace/so_on_and_so_forth).

		// Example of invalid input file
		final String [] INVALID_INPUT = {"resources/childrenEnrolled_invalid.csv"};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT = { "resources/gdpPerCapita.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT = { "resources/childrenEnrolledInPrimary.csv",
				"resources/childrenEnrolledInSecondary.csv"
		};

		final String [] ENROLLMENT_INPUT_PRIMARY = { "resources/childrenEnrolledInPrimary.csv"
		};

		final String [] ENROLLMENT_INPUT_SECONDARY = {
				"resources/childrenEnrolledInSecondary.csv"
		};

		final String [] FEMALE_POPULATION = { "resources/female_population.csv"
		};

		//Extra Credit:
		int selection = -1;
		boolean menu = true;

		while(menu)
		{
			String userRequest = "";
			try
			{
				System.out.println("Select an indicator to parse. Enter a number between 0 to 4.");
				System.out.println("Available indicators are:");
				System.out.println("0. Invalid data");
				System.out.println("1. GDP per Capita");
				System.out.println("2. School Enrollment Primary");
				System.out.println("3. School Enrollment Secondary");
				System.out.println("4. World Female Population");
				userRequest = keyboard.nextLine();
				selection = Integer.parseInt(userRequest);
				if (selection >= 0 && selection <=4)
					break;
				else
					System.out.printf("Invalid input %s. ", userRequest);
			}
			catch (NumberFormatException ne)
			{
				System.out.printf("Invalid input %s. ", userRequest);
			}
		}
//		final String [] filenames;


		// Determines the input file pathname.
		IndicatorType selectedIndicator;
		if (selection == 1)
		{
			selectedIndicator = IndicatorType.GDP_PER_CAPITA;
//			filenames = GDP_INPUT;
		}
		else if (selection == 2)
		{
			selectedIndicator = IndicatorType.SCHOOL_ENROLLMENT_PRIMARY;
		}
		else if (selection == 3)
		{
			selectedIndicator = IndicatorType.SCHOOL_ENROLLMENT_SECONDARY;
		}
		else if (selection == 4)
		{
			selectedIndicator = IndicatorType.FEMALE_POPULATION;

		}

		else
		{
			selectedIndicator = IndicatorType.INVALID;
//			filenames = INVALID_INPUT;
		}
		//

		// Displays graph* of data by country
		// TODO: Define the view such that it takes the model as input.
		//       Construct the x and y axis using a NumberAxis, label the axis.
		GraphView graphView = null;

		// Provides access to CSV data
		switch(selectedIndicator)
		{
			case INVALID:
				System.out.print("Invalid Data");
				System.exit(0);
				break;
			case GDP_PER_CAPITA:
				DataModel gdpModel = new DataModel(GDP_INPUT);
				graphView = new GraphView(gdpModel);
				break;
			case SCHOOL_ENROLLMENT_PRIMARY:
				DataModel enrollmentModel = new DataModel(ENROLLMENT_INPUT_PRIMARY);
				graphView = new GraphView(enrollmentModel);
				break;
			case SCHOOL_ENROLLMENT_SECONDARY:
				DataModel enrollmentModelS = new DataModel(ENROLLMENT_INPUT_SECONDARY);
				graphView = new GraphView(enrollmentModelS);
				break;
			case FEMALE_POPULATION:
				DataModel populationModel = new DataModel(FEMALE_POPULATION);
				graphView = new GraphView(populationModel);
				break;
			default:
				System.out.println("WARNING: Invalid indicator selected. Exiting program.");
				System.exit(0);
		}
		// END Added code for Extra Credit

		// TODO: Define the update() method for the model such that:
		//       - Gets all the data** from the model
		//       - Creates a random list of elements from the data.
		//       - Traverses each element and creates a series (i.e. line)
		//       - Adds the series*** to it's Observablelist.

		//   * Here, displays graph of Indicator data by country.
		//
		//  ** Here, our data is composed of Country objects.
		//
		// *** Get an instance of javafx.collections.ObservableList via a call
		//     to getData() method.
		// https://docs.oracle.com/javafx/2/api/javafx/scene/chart/XYChart.html#getData()
		graphView.update();

		// holds the graph
		//Pane topPane = new Pane();	// TODO: try different *Pane layouts
		//topPane.getChildren().add(graphView);

		// Creates a scene and adds the graph to the scene.
		Scene scene = new Scene(graphView);

		// Places the scene in the stage
		stage.setScene(scene);

		// Set the stage title
		stage.setTitle(selectedIndicator.getLabel());

		switch(selectedIndicator)
		{
			case GDP_PER_CAPITA:
				stage.setTitle("GDP Per Capita Data");
				break;
			case SCHOOL_ENROLLMENT_PRIMARY:
				stage.setTitle("Primary Enrollment Data (%)");
				break;
			case SCHOOL_ENROLLMENT_SECONDARY:
				stage.setTitle("Secondary Enrollment Data (%)");
				break;
			case FEMALE_POPULATION:
				stage.setTitle("World Female Population (%)");
				break;
			default:
				// file is not a known type
				break;
		}

		// Display the stage
		stage.show();
	}

	/**
	 * Launches a standalone JavaFx App
	 */
	public static void main(String[] args)
	{
		launch();
	}
}