package ea;

import java.util.Arrays;

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

	@Override
	public String toString()
	{
		return Arrays.toString(solutions);
	}

	public int size()
	{
		return solutions.length;
	}
}
