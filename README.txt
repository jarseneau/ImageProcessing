Welcome to Jack and Jake's README.
Here is the structure of our project at the interface and class level with their descriptions:
------------------------------
Interface: ImageProcessingController
    the controller interface, promises a control method which drives the application

Class: ConsoleController
    An implementation of our controller interface to operate in the console
------------------------------
Interface: IImageAdjustor
    An interface that promises the adjust method for any type of adjustment

Class: BrightenAdjustor         -> handles brightening functionality
Class: FlipHorizontalAdjustor   -> handles horizontal flipping
Class: FlipVerticalAdjustor     -> handles vertical flipping
Class: GrayscaleAdjustor        -> handles gray-scaling by any component
------------------------------
Interface: ImageProcessingModel
    Overarching interface to represent any image model in this program

Abstract Class: AImageFromFileProcessing
    represents an image model that has been read in from a file
Class: PPMProcessingModel
    The representation of a ppm image model, supports loading PPMs

Class: EditorImageProcessingModel
    Represents any image model that is created by some operation on another image model.
------------------------------
Interface: IPixel
    Represents a pixel of any kind, with channels (or channel).
Class: RGBPixel
    Represents an RGB pixel, with red, green, and blue channels as well as value,
    intensity, and luma.
------------------------------
Interface: ImageProcessingView
    Represents any view of the application with the ability to save.
Class PPMImageView
    Represents the view of a PPM image, with the ability to save it.
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