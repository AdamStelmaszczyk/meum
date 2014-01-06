package ea;

import ea.distribution.Distribution;

public class EA
{
	public final int NP_TO_DIM_RATIO = 10;
	public final int DIM;
	public final int NP;
	private final Evaluator evaluator;
	private final Populations populations;
	private final Distribution distribution;

	public EA(Evaluator evaluator, Distribution distribution, int dim)
	{
		DIM = dim;
		NP = NP_TO_DIM_RATIO * dim;
		this.evaluator = evaluator;
		populations = new Populations(evaluator.MAX_FUN_EVALS / NP);
		this.distribution = distribution;
	}

	public void optimize()
	{
		populations.add(new Population(NP, DIM));
		do
		{
			final Population newPopulation = new Population(NP, DIM);
			for (int i = 0; i < NP; i++)
			{
				final Solution a = select();
				final Solution b = select();
				newPopulation.solutions[i] = a.crossover(b).mutate();
			}
			succesion(newPopulation);
		}
		while (!evaluator.hasReachedTarget() && !evaluator.hasReachedMaxFunEvals());
	}

	private Solution select()
	{
		final int popIndex = distribution.sample(populations.size()) - 1;
		final Population pop = populations.get(popIndex);
		return pop.getRandom();
	}

	private void succesion(Population newPopulation)
	{
		final Population lastPopulation = populations.get(populations.size() - 1);
		for (int i = 0; i < NP; i++)
		{
			if (evaluator.isBetter(lastPopulation.solutions[i], newPopulation.solutions[i]))
			{
				newPopulation.solutions[i] = new Solution(lastPopulation.solutions[i]);
			}
		}
		populations.add(newPopulation);
	}
}
