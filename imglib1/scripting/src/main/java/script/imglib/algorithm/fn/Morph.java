package script.imglib.algorithm.fn;

import mpicbg.imglib.algorithm.roi.StatisticalOperation;
import mpicbg.imglib.algorithm.roi.StructuringElement;
import mpicbg.imglib.image.Image;
import mpicbg.imglib.outofbounds.OutOfBoundsStrategyFactory;
import mpicbg.imglib.outofbounds.OutOfBoundsStrategyValueFactory;
import mpicbg.imglib.type.numeric.RealType;

/** Morphological operations such as Open, Close, Erode and Dilate.
 *  The operation takes any of CUBE, BALL, BAR, each with a length.
 *  In ImageJ, the equivalent would be a CUBE with length 3.
 *  
 *  The {@code lengthDim} is only useful for BAR, and specifies
 *  in which axis to grow the bar.
 * 
 * 
 */
public abstract class Morph<T extends RealType<T>> extends Image<T>
{
	static public enum Shape { BALL, CUBE, BAR };

	static public final Shape BALL = Shape.BALL;
	static public final Shape CUBE = Shape.CUBE; 
	static public final Shape BAR = Shape.BAR; 

	private Morph(final Image<T> img) {
		super(img.getContainer(), img.createType());
	}

	public Morph(final Object fn, final Class<?> c, final Shape s, final int shapeLength, final int lengthDim, final float outside) throws Exception {
		this(Morph.<T>process(c, fn, s, shapeLength, lengthDim, outside));
	}

	@SuppressWarnings("unchecked")
	private final static <R extends RealType<R>> Image<R> process(final Class<?> c, final Object fn, final Shape s, final int shapeLength, final int lengthDim, final float outside) throws Exception {
		final Image<R> img = AlgorithmUtil.wrap(fn);
		StructuringElement strel;
		switch (s) {
		case BALL:
			// CAREFUL: for ball shapeLength is the radius!
			strel = StructuringElement.createBall(img.getNumDimensions(), shapeLength);
			break;
		case BAR:
			strel = StructuringElement.createBar(img.getNumDimensions(), shapeLength, lengthDim);
			break;
		case CUBE:
		default:
			strel = StructuringElement.createCube(img.getNumDimensions(), shapeLength);
			break;
		}
		R t = img.createType();
		t.setReal(outside);
		StatisticalOperation<R> morph = (StatisticalOperation<R>) c.getConstructor(Image.class, StructuringElement.class, OutOfBoundsStrategyFactory.class)
				.newInstance(img, strel, new OutOfBoundsStrategyValueFactory<R>(t));
		if (!morph.process()) { // !checkInput becomes true? TODO
			throw new Exception(morph.getClass().getSimpleName() + ": " + morph.getErrorMessage());
		}
		return morph.getResult();
	}
}
