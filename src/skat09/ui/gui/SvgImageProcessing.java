package skat09.ui.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.batik.dom.svg12.SVG12DOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.apache.commons.io.FileUtils;

/**
 * SVG image processing class.
 * 
 * @since 20.06.2015 00:22:36
 * 
 * @author Sebastian Schlatow <ssc@openmailbox.org>
 *
 */
public class SvgImageProcessing {

	public SvgImageProcessing() {
		
	}
	
	/**
	 * Get the image for the given file name.
	 * 
	 * @param filename , the name of the requested SVG file without ".svg" extension.
	 * @return the image
	 * 
	 * @since 20.06.2015 00:24:20
	 * 
	 * @author Sebastian Schlatow <ssc@openmailbox.org>
	 */
	public static Image getImage(String filename) {
		File svgFile;
		try {
			svgFile = new File(SvgImageProcessing.class.getClassLoader().getResource("img/app/" + filename + ".svg").toURI());
			try {
				return rasterize(svgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage rasterize(File svgFile) throws IOException {

	    final BufferedImage[] imagePointer = new BufferedImage[1];

	    // Rendering hints can't be set programatically, so
	    // we override defaults with a temporary stylesheet.
	    // These defaults emphasize quality and precision, and
	    // are more similar to the defaults of other SVG viewers.
	    // SVG documents can still override these defaults.
	    String css = "svg {" +
	            "shape-rendering: geometricPrecision;" +
	            "text-rendering:  geometricPrecision;" +
	            "color-rendering: optimizeQuality;" +
	            "image-rendering: optimizeQuality;" +
	            "}";
	    File cssFile = File.createTempFile("batik-default-override-", ".css");
	    FileUtils.writeStringToFile(cssFile, css);

	    TranscodingHints transcoderHints = new TranscodingHints();
	    transcoderHints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, Boolean.FALSE);
	    transcoderHints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION,
	            SVG12DOMImplementation.getDOMImplementation());
	    transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI,
	            SVGConstants.SVG_NAMESPACE_URI);
	    transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, "svg");
	    transcoderHints.put(ImageTranscoder.KEY_USER_STYLESHEET_URI, cssFile.toURI().toString());

	    try {

	        TranscoderInput input = new TranscoderInput(new FileInputStream(svgFile));

	        ImageTranscoder t = new ImageTranscoder() {

	            @Override
	            public BufferedImage createImage(int w, int h) {
	                return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	            }

	            @Override
	            public void writeImage(BufferedImage image, TranscoderOutput out)
	                    throws TranscoderException {
	                imagePointer[0] = image;
	            }
	        };
	        t.setTranscodingHints(transcoderHints);
	        t.transcode(input, null);
	    }
	    catch (TranscoderException ex) {
	        ex.printStackTrace();
	        throw new IOException("Couldn't convert " + svgFile);
	    }
	    finally {
	        cssFile.delete();
	    }

	    return imagePointer[0];
	}
}
