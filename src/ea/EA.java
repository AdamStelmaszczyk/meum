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
			final Population mutants = new Population(NP, DIM);
			for (int i = 0; i < NP; i++)
			{
				final Solution a = select();
				final Solution b = select();
				mutants.solutions[i] = a.crossover(b).mutate();
			}
			populations.add(mutants);
		}
		while (!evaluator.hasReachedTarget() && !evaluator.hasReachedMaxFunEvals());
	}

	private Solution select()
	{
		final int popIndexA = distribution.sample(populations.size()) - 1;
		final int popIndexB = distribution.sample(populations.size()) - 1;
		final Solution a = populations.get(popIndexA).getRandom();
		final Solution b = populations.get(popIndexB).getRandom();
		if (evaluator.isBetter(a, b))
		{
			return a;
		}
		return b;
	}
}
