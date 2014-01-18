package ea;

import javabbob.JNIfgeneric;

public class Evaluator
{
	public final int MAX_FUN_EVALS;

	private final JNIfgeneric fgeneric;

	public Evaluator(JNIfgeneric fgeneric, int maxFunEvals)
	{
		this.fgeneric = fgeneric;
		MAX_FUN_EVALS = maxFunEvals;
	}

	public double evaluate(Solution solution)
	{
		return fgeneric.evaluate(solution.feat);
	}

	public double getBestObservedScore()
	{
		return fgeneric.getBest();
	}

	public int getFunEvals()
	{
		return (int) fgeneric.getEvaluations();
	}

	public double getTarget()
	{
		return fgeneric.getFtarget();
	}

	public boolean hasReachedMaxFunEvals()
	{
		return fgeneric.getEvaluations() >= MAX_FUN_EVALS;
	}

	public boolean hasReachedTarget()
	{
		return fgeneric.getBest() < fgeneric.getFtarget();
	}

	/** @param a Solution a.
	 * @param b Solution b.
	 * @return True, if a is better than b. */
	public boolean isBetter(Solution a, Solution b)
	{
		return evaluate(a) < evaluate(b);
	}
}
