package net.imglib2.ops.features.firstorder.moments;

import java.util.Iterator;

import net.imglib2.ops.features.annotations.RequiredInput;
import net.imglib2.ops.features.datastructures.AbstractFeature;
import net.imglib2.ops.features.firstorder.Mean;
import net.imglib2.ops.features.geometric.area.Area;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;

public class Moment2AboutMean extends AbstractFeature
{

	@RequiredInput
	private Iterable< ? extends RealType< ? > > iterable;

	@RequiredInput
	private Mean mean;

	@RequiredInput
	private Area area;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String name()
	{
		return "Moment 2 About Mean";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Moment2AboutMean copy()
	{
		return new Moment2AboutMean();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DoubleType recompute()
	{
		final double mean = this.mean.get().get();
		final double area = this.area.get().get();
		double res = 0.0;

		Iterator< ? extends RealType< ? > > it = iterable.iterator();
		while ( it.hasNext() )
		{
			final double val = it.next().getRealDouble() - mean;
			res += val * val;
		}

		return new DoubleType( res / area );
	}
}