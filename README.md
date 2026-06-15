# VideoFilters

A [Processing](https://processing.org/)-based video filter framework. Open a webcam feed, a video file, or a still image, then apply image processing filters in real time — greyscale, blur, edge detection, thresholding, K-means clustering, and more.

## Requirements

- **Java 11 or later** — download from [Adoptium](https://adoptium.net/)
- **Maven 3.6+** — download from [maven.apache.org](https://maven.apache.org/download.cgi)
- **GStreamer** — required for webcam and video file playback (see platform notes below)

### Platform notes

| Platform | GStreamer setup |
|---|---|
| **Windows** | GStreamer 0.10 DLLs are bundled in `lib/` — no install needed |
| **macOS** | Install via Homebrew: `brew install gstreamer gst-plugins-base gst-plugins-good` |
| **Linux** | `sudo apt install gstreamer1.0-tools gstreamer1.0-plugins-base gstreamer1.0-plugins-good` |

> **Note:** Still-image mode works on all platforms without GStreamer. Webcam and video playback require it.

## Building

```bash
mvn compile

# Package into a JAR (output: out/video-filters-1.0-SNAPSHOT.jar)
mvn package
```

## Running

Run from the project root so that the `lib/` folder is found at runtime:

```bash
# macOS / Linux
java -jar out/video-filters-1.0-SNAPSHOT.jar

# Windows
java -jar out\video-filters-1.0-SNAPSHOT.jar
```

Or via Maven (classpath is handled automatically):

```bash
mvn exec:java -Dexec.mainClass=Main
```

On launch, a dialog asks whether to use a webcam, load a video file (`.mp4`), or load a still image.

## Controls

| Key | Action |
|---|---|
| `F` | Load a new filter (type the class name in the prompt) |
| `S` | Toggle between original and filtered view |
| `P` | Pause / resume |
| Mouse click | Passed to the active filter (e.g. color picker for threshold filters) |

## Project Structure

```
VideoFilters/
├── src/
│   ├── Main.java                  # Entry point — Processing sketch, handles input and draw loop
│   ├── DImage.java                # Image wrapper: pixel array access, channel separation
│   ├── PixelFilter.java           # Interface all filters implement
│   ├── Clickable.java             # Optional interface for filters that respond to mouse/key
│   ├── RGB.java                   # Simple RGB data class
│   ├── Point.java                 # 2D point data class
│   ├── DoNothingFilter.java       # Pass-through (identity) filter
│   ├── DarkenFilter.java          # Reduces brightness
│   ├── Lighten.java               # Increases brightness
│   ├── Negative.java              # Inverts pixel values
│   ├── Monochrome.java            # Greyscale conversion
│   ├── Gamma.java                 # Gamma correction
│   ├── Hue.java                   # Hue shift
│   ├── SwapColor.java             # Swaps R/G/B channels
│   ├── RemoveColor.java           # Zeroes out a colour channel
│   ├── Border.java                # Draws a border around the image
│   ├── Blur.java                  # Box blur
│   ├── ConvolutionFilter.java     # Sobel edge detection and convolution kernels
│   ├── VerticalReflectFilter.java # Flips the image vertically
│   ├── Pixelate3.java             # Pixelation effect
│   ├── BWThresold.java            # Black-and-white threshold
│   ├── ColorThresold.java         # Colour-range threshold
│   ├── Polychrome.java            # Multi-colour threshold
│   ├── BWKMeans.java              # K-means clustering on greyscale pixels
│   ├── KMeans.java                # K-means clustering on colour pixels
│   ├── BWCluster.java             # Cluster data class for BWKMeans
│   ├── Cluster.java               # Cluster data class for KMeans
│   ├── IO2.java                   # Interactive object detection (colour pick → threshold → K-means)
│   ├── Exercise1.java             # Exercise stubs
│   ├── Exercise2A.java
│   ├── Exercise2B.java
│   ├── Exercise3.java
│   └── Exercise4.java
├── lib/                           # Bundled JARs and Windows GStreamer DLLs
│   ├── core.jar                   # Processing core
│   ├── video.jar                  # Processing video (webcam + movie)
│   ├── gstreamer-java.jar         # GStreamer Java bindings
│   ├── jna.jar                    # JNA (native access)
│   ├── gluegen-rt.jar             # JOGL GluEGen runtime
│   ├── jogl-all.jar               # JOGL OpenGL bindings
│   └── *.dll                      # GStreamer 0.10 native libraries (Windows only)
└── pom.xml
```

## Writing a New Filter

Implement `PixelFilter`:

```java
public class MyFilter implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] bw = img.getBWPixelGrid();
        // manipulate bw pixels...
        img.setPixels(bw);
        return img;
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        // optional: draw shapes/text on top of the image
    }
}
```

Then press `F` while the app is running and type `MyFilter` at the prompt.
