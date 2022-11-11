Welcome to Jack and Jake's README.
Here is the structure of our project at the interface and class level with their descriptions:
------------------------------
Interface: ImageProcessingController
    the controller interface, promises a control method which drives the application

Class: ConsoleController
    An implementation of our controller interface to operate in the console
------------------------------
VERSION 2.0:
// we modified the controller because our switch statement was growing quickly with each
// additional functionality that was added, so we refactored it to use command classes
// to apply the command to an image.

Interface: ImageCommand
    An interface to represent any command that the controller can run on an image

Abstract Class: AbstractImageCommand
    Represents an image command that can be run by the controller
    Subclass: HFlip     -> horizontally flips an image and maps result to new name
    Subclass: VFlip     -> vertically flips an image and maps result to new name
    Subclass: Brighten  -> brightens or darkens an image and maps result to new name
    Subclass: Load      -> loads an image and places it in the controllers name to image map
    Subclass: Save      -> saves an image to the filename specified
    Subclass: Component -> gray-scales an image by the component and maps result to new name
    Subclass: Luma      -> gray-scales an image by luma and maps result to new name
    Subclass: Blur      -> blurs an image and maps result to new name
    Subclass: Sharpen   -> sharpens an image and maps result to new name
    Subclass: Sepia     -> applies sepia filter to every pixel in image and maps result to new name
------------------------------
Interface: IImageAdjustor
    An interface that promises the adjust method for any type of adjustment

Class: BrightenAdjustor         -> handles brightening functionality
Class: FlipHorizontalAdjustor   -> handles horizontal flipping
Class: FlipVerticalAdjustor     -> handles vertical flipping
Class: GrayscaleAdjustor        -> handles gray-scaling by any component

VERSION 2:
Abstract Class: AFilteringAdjustor
    represents any adjustor that uses a filtering matrix to transform an image
    Subclass: BlurFilteringAdjustor     -> handles blurring functionality
    Subclass: SharpenFilteringAdjustor  -> handles sharpening functionality
Abstract Class: AColorTransformationAdjustor
    represents any adjustor that uses a color transformation matrix to transform an image
    Subclass: GrayscaleLumaTransformation     -> handles gray-scaling with color matrix
    Subclass: SepiaTransformation             -> handles toning an image with sepia
------------------------------
Interface: ImageProcessingModel
    Overarching interface to represent any image model in this program

Abstract Class: AImageFromFileProcessing
    represents an image model that has been read in from a file
Class: PPMProcessingModel
    The representation of a ppm image model, supports loading PPMs

Class: EditorImageProcessingModel
    Represents any image model that is created by some operation on another image model.

VERSION 2:
Class: ImageIOProcessingModel
    represents an image model downloaded from a file source that is not ppm
------------------------------
Interface: IPixel
    Represents a pixel of any kind, with channels (or channel).
Class: RGBPixel
    Represents an RGB pixel, with red, green, blue, and alpha
    channels with alpha always being 255 as well as value, intensity, and luma.

VERSION 2:
// We created a new pixel class because we realized pngs will have alpha channels
// and must be represented in some way
Class: AlphaPixel
    Represents an RGB pixel with an alpha channel that is significant in its
    representation.
------------------------------
Interface: ImageProcessingView
    Represents any view of the application with the ability to save.
Class PPMImageView
    Represents the view of a PPM image, with the ability to save it.

VERSION 2:
Class: ImageIOView
    Represents the view of an IO image, with the ability to save it
    to the specified format
------------------------------
Class: ImageProcessing
    The Main class for this program.

---------------------------------------------------------------------
INSTRUCTIONS TO RUN
Run ImageProcessing (the main class)
Input the following commands into System.in while running:

load res/9PixOriginal.ppm og
brighten -50 og dark
save darkened.ppm dark
vertical-flip dark vFlipDark
save vFlipDark.ppm vFlipDark
luma-component vFlipDark vFlipDark-luma
save vFlipDark-luma.ppm vFlipDark-luma
q

This will leave you with 3 new images:
1 just darkened, 1 vertically flipped and darkened,
and 1 gray-scaled by luma, darkened, and flipped.

CITATION:
The image used in the resources folder was created by Jack Arseneau,
and he authorizes its use in this assignment.