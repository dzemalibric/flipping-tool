# Overview
Flipping Tool is a test project for a client and specs are defined here on this [Link](https://docs.google.com/document/d/1Ggu2jqIN6ZOQBv5c-T_o2I1JOueN5CD9ogsjmSjAxUs)

# Features
Base feature of this app is implementation of *FlippingToolLayout* that essentially holds all the logic of the app. 

1. When user opens Catalogue app loads all images provided in *Thumbnail* state (this was essential because each image should be preloaded before changing states for resize and resolution purposes).

2. Every visible image loads *Medium* state, and every image that is within 15 images from visible items loads *Preview* state. All other images load *Thumbnail* state.

3. After visible images have loaded *Medium* state, every image that neighbours visible images from left and right loads *Medium* state (this significantly improves quality because user will never see blurred image in preview state)

4. I added 2 High Res states, *HIGH and ULTRA*. If user zooms image to a factor of 1.3 to 2.0, image will be loaded to *High* resolution, and everything above will load *Ultra* resolution (in addition to this, we will not preload high res images to neighbouring images to improve responsiveness, and this means that image must be visible and zoomed to load high res image)

5. In addition, we can keep track of every image by tracking time user spent on it, or if it was ever zoomed.


# How to use
Using the app is simple. Once it has started you just click on the image thumbnail in the center of the screen and you can scroll and see Catalogue of images from JSON example in given document. 

[![flipp.png](https://i.postimg.cc/sgJqN5HG/flipp.png)](https://postimg.cc/BjXm8P0J)

As an extra feature I added *Report* button that, when clicked, shows data for each image. Data includes *Time Spent and Zoomed* flags that shows you how much time user spent on each image and if that image was zoomed while user was on the screen.
Report data resets on each new user session
