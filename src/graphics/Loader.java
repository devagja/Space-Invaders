package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loader {

	/**
	 * Carga la imagen
	 * 
	 * @param directorio
	 *            ruta de la imagen
	 * @return BufferedImage con la imagen descodificada
	 */
	public static BufferedImage cargarImagen(String directorio) {
		try {
			return ImageIO.read(new File(directorio));
		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * Carga la sonido
	 * 
	 * @param directorio
	 *            ruta de la imagen
	 * @return BufferedImage con la imagen descodificada
	 */
	public static Clip cargarSonido(String directorio) {
		try {
			Clip clip;

			clip = AudioSystem.getClip();

			clip.open(AudioSystem.getAudioInputStream(new File(directorio)));
			return clip;
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		return null;
	}
}