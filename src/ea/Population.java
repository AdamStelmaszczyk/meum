package ea;

import java.util.Arrays;

import testing.Main;

public class Population
{
	public final Solution[] solutions;

	/** Create random population. */
	public Population(int n, int d)
	{
		solutions = new Solution[n];
		for (int i = 0; i < n; i++)
		{
			solutions[i] = new Solution(d);
		}
	}

	/** @return Deep copy of randomly chosen solution. */
	public Solution getRandom()
	{
		return new Solution(solutions[Main.rand.nextInt(solutions.length)]);
	}

	public int size()
	{
		return solutions.length;
	}

	@Override
	public String toString()
	{
		return Arrays.toString(solutions);
	}
}
