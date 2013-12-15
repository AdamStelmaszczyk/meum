package ea;

public class EA
{
	public final int N_TO_DIM_RATIO = 10;
	public final int DIM;
	public final int N;
	private final Evaluator evaluator;
	private final Populations populations;

	public EA(Evaluator evaluator, int dim)
	{
		DIM = dim;
		N = N_TO_DIM_RATIO * dim;
		this.evaluator = evaluator;
		this.populations = new Populations(evaluator.MAX_FUN_EVALS);
	}

	public void optimize()
	{
		populations.add(new Population(N, DIM));
		do
		{
			final Population newPopulation = new Population(N, DIM);
//			for (int i = 0; i < N; i++)
//			{
//				final Solution a = select();
//				final Solution b = select();
//				newPopulation.solutions[i] = mutate(crossover(a, b));
//			}
			succesion(newPopulation);
		}
		while (!evaluator.hasReachedTarget() && !evaluator.hasReachedMaxFunEvals());
	}

	private Solution mutate(Object crossover)
	{
		// TODO
		return null;
	}

	private Object crossover(Solution a, Solution b)
	{
		// TODO
		return null;
	}

	private Solution select()
	{
		// TODO
		return null;
	}

	private void succesion(Population newPopulation)
	{
		final Population lastPopulation = populations.get(populations.size() - 1);
		for (int i = 0; i < N; i++)
		{
			if (evaluator.isBetter(lastPopulation.solutions[i], newPopulation.solutions[i]))
			{
				newPopulation.solutions[i] = new Solution(lastPopulation.solutions[i]);
			}
		}
		populations.add(newPopulation);
	}
}
