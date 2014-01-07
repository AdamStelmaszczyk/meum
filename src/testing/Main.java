package testing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javabbob.JNIfgeneric;
import ea.EA;
import ea.Evaluator;
import ea.distribution.Distribution;

public class Main
{
	public final static int FUNCTIONS[] =
	{ 15, 16, 19, 20, 21, 22, 24 };
	public final static int RUNS = 15;
	public final static int FUN_EVALS_TO_DIM_RATIO = 10000;
	public final static int SEED = 1;
	public final static Random rand = new Random(SEED);
	public final static double DOMAIN_MIN = -5.0;
	public final static double DOMAIN_MAX = 5.0;
	public final static String DEFAULT_ALGORITHM_NAME = Distribution.names[0];
	public final static int DEFAULT_DIM = 10;

	/** Main method for running the whole BBOB experiment. */
	public static void main(String[] args)
	{
		Distribution distribution = Distribution.fromName(DEFAULT_ALGORITHM_NAME);
		if (args.length >= 1)
		{
			distribution = Distribution.fromName(args[0]);
		}

		int dim = DEFAULT_DIM;
		if (args.length >= 2)
		{
			dim = Integer.parseInt(args[1]);
		}

		if (args.length == 0)
		{
			args = new String[1];
			args[0] = DEFAULT_ALGORITHM_NAME;
		}
		final String filename = args[0] + "_" + dim;

		// Sets default locale to always have 1.23 not 1,23 in files
		Locale.setDefault(Locale.US);

		// Loads the library cjavabbob at the core of JNIfgeneric. Throws runtime errors if the library is not found.
		final JNIfgeneric fgeneric = new JNIfgeneric();

		// Creates the folders for storing the experimental data.
		if (JNIfgeneric.makeBBOBdirs(filename, false))
		{
			System.out.println("BBOB data directory '" + filename + "' created");
		}
		else
		{
			System.out.println("Error! BBOB data directory '" + filename + "' was NOT created, stopping");
			return;
		}

		fgeneric.setNoiseSeed(SEED);

		final long startTime = System.currentTimeMillis();
		printDate();

		for (final int fun : FUNCTIONS)
		{
			for (int run = 1; run <= RUNS; run++)
			{
				fgeneric.initBBOB(fun, run, dim, filename, new JNIfgeneric.Params());

				final int MAX_FUN_EVALS = FUN_EVALS_TO_DIM_RATIO * dim;
				final Evaluator evaluator = new Evaluator(fgeneric, MAX_FUN_EVALS);
				final EA ea = new EA(evaluator, distribution, dim);
				ea.optimize();

				final double distance = fgeneric.getBest() - fgeneric.getFtarget();
				final int fes = (int) fgeneric.getEvaluations();
				final int seconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
				System.out.printf("%dD f%d run %3d FEs = %7d best-target = %15.8f %ds\n", dim, fun, run, fes, distance,
						seconds);

				fgeneric.exitBBOB();
			}
			printDate();
		}
		System.out.println("---- " + dim + "-D done ----");
	}

	private static void printDate()
	{
		System.out.println(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
	}
}
