package de.thro.inf.prg3.a09.model;

import de.thro.inf.prg3.a09.model.empire.TieBomber;
import de.thro.inf.prg3.a09.model.empire.TieFighter;
import de.thro.inf.prg3.a09.model.empire.TieInterceptor;
import de.thro.inf.prg3.a09.model.rebellion.AWing;
import de.thro.inf.prg3.a09.model.rebellion.XWing;
import de.thro.inf.prg3.a09.model.rebellion.YWing;
import de.thro.inf.prg3.a09.resource.FxImageLoaderAdapter;
import de.thro.inf.prg3.a09.resource.ResourceLoader;
import de.thro.inf.prg3.a09.util.NameGenerator;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Random;

/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	// private final ResourceLoader<Image> imageResourceLoader;
	private final FxImageLoaderAdapter imageLoader = new FxImageLoaderAdapter();

	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
		// imageResourceLoader = new ResourceLoader<>(Image::new);
	}

	/**
	 * Create a random Fighter
	 *
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 * @return a new Fighter instance
	 */
	public Fighter createFighter() {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		String name = nameGenerator.generateName();

		switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(name, loadImage(cl,"fighter/awing.jpg"));
			case 1:
				return new XWing(name, loadImage(cl, "fighter/xwing.jpg"));
			case 2:
				return new YWing(name, loadImage(cl, "fighter/ywing.jpg"));
			case 3:
				return new TieBomber(name, loadImage(cl, "fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(name, loadImage(cl, "fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(name, loadImage(cl, "fighter/tieinterceptor.jpg"));
		}
	}

	/// store images by pathname, once loaded
	private HashMap<String, Image> cache = new HashMap<>();

	/// load on demand, return from cache
	private Image loadImage(ClassLoader cl, String path) {
		if (!cache.containsKey(path)) {
			// cache.put(path, imageResourceLoader.loadResource(cl, path));
			cache.put(path, imageLoader.loadImage(path));
		}

		return cache.get(path);
	}
}
